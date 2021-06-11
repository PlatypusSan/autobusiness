package com.test.autobusiness.services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.test.autobusiness.entities.DataObject;
import com.test.autobusiness.entities.Dealership;
import com.test.autobusiness.repositories.DealershipRepository;
import com.test.autobusiness.repositories.FileRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class DealershipService {

    private final DealershipRepository dealershipRepository;
    private final FileRepository fileRepository;
    private final FileService fileService;
    private ConcurrentHashMap<Long, JobState> jobStates = new ConcurrentHashMap<>();
    private long jobId;

    @Getter
    private static List<String> headers;

    public JobState getJobState(long id) {
        return jobStates.getOrDefault(id, new JobState(State.NOT_STARTED));
    }

    public synchronized long incrementJobId() {
        return ++jobId;
    }

    @Transactional
    @Async
    public synchronized CompletableFuture<List<Dealership>> saveDealerships(long fileId) throws Exception {

        jobStates.put(jobId, new JobState(State.RUNNING));
        log.info("IN saveDealership - thread with id {} started", jobId);
        Thread.sleep(5000);
        List<Dealership> dealershipList = parseCsvFileToDealership(fileId);
        dealershipList.forEach(dealership -> {
            dealership.getContacts().forEach(contact -> {
                contact.setDealership(dealership);
            });
        });
        dealershipRepository.saveAll(dealershipList);
        jobStates.put(jobId, new JobState(State.ENDED));
        log.info("IN saveDealership - thread with id {} ended", jobId);

        return CompletableFuture.completedFuture(dealershipList);
    }

    @Transactional
    public List<Dealership> getAllDealerships() {

        return dealershipRepository.findAll();
    }

    @Transactional
    public Dealership getDealership(long id) {

        return dealershipRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "There is no dealership with id: " + id));
    }


    private synchronized List<Dealership> parseCsvFileToDealership(long fileId) {

        DataObject dataObject = fileRepository.findById(fileId).get();
        MultipartFile multipartFile = new MockMultipartFile(dataObject.getName(), dataObject.getFile());
        List<Dealership> dealershipList = new ArrayList<>();

        try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            headers = Arrays.asList(bufferedReader.readLine().split(","));
            CsvToBean<Dealership> cb = new CsvToBeanBuilder<Dealership>(bufferedReader)
                    .withType(Dealership.class)
                    .build();

            dealershipList.addAll(cb.parse());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dealershipList;
    }

    @Transactional
    @Async
    public synchronized void writeCsvFileFromDealership() {


        jobStates.put(jobId, new JobState(State.RUNNING));
        log.info("IN saveDealership - thread with id {} started", jobId);
        List<Dealership> dealershipList = dealershipRepository.findAll();

        try {
            Thread.sleep(5000);
            Path path = Paths.get("yourfile.csv");
            Writer writer = new FileWriter(path.toString());

            StatefulBeanToCsv<Dealership> beanToCsv = new StatefulBeanToCsvBuilder<Dealership>(writer).build();
            beanToCsv.write(dealershipList);
            writer.close();

            MultipartFile multipartFile = new MockMultipartFile(path.toString(), FileUtils.readFileToByteArray(path.toFile()));
            long fileId = fileService.saveImportFile(multipartFile);
            JobState jobState = new JobState(State.ENDED, fileId);
            jobStates.put(jobId, jobState);
            log.info("IN saveDealership - thread with id {} ended", jobId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Resource loadFileAsResource(Long fileId) throws FileNotFoundException {

        try {
            String fileName = fileRepository.findById(fileId)
                    .orElseThrow(() -> new FileNotFoundException("File not found: " + fileId))
                    .getName();
            return new UrlResource(Paths.get(fileName).toUri());
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("File not found: " + fileId);
        }
    }
}
