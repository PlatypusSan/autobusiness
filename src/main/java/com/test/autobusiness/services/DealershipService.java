package com.test.autobusiness.services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.test.autobusiness.entities.DataObject;
import com.test.autobusiness.entities.Dealership;
import com.test.autobusiness.repositories.DealershipRepository;
import com.test.autobusiness.repositories.FileRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Slf4j
@RequiredArgsConstructor
public class DealershipService {

    private final DealershipRepository dealershipRepository;
    private final FileRepository fileRepository;
    private ConcurrentHashMap<Long, ProcessState> states = new ConcurrentHashMap<>();

    @Getter
    private static List<String> headers;

    ReentrantLock lock = new ReentrantLock();

    public ProcessState getProcessState(long id) {
        return states.getOrDefault(id, ProcessState.NOT_STARTED);
    }

    @Transactional
    @Async
    public CompletableFuture<List<Dealership>> saveDealerships(final MultipartFile file, long id) throws Exception {

        states.put(id, ProcessState.RUNNING);
        log.info("IN saveDealership - thread with id {} started", id);
        Thread.sleep(1000);
        log.info("IN saveDealership - thread with id {} slept 1000 ms", id);
        List<Dealership> dealershipList = parseCsvFileToDealership(file);
        log.info("IN saveDealership - thread with id {} completed parsing", id);
        dealershipList.forEach(dealership -> {
            dealership.getContacts().forEach(contact -> {
                contact.setDealership(dealership);
            });
        });
        dealershipRepository.saveAll(dealershipList);
        states.put(id, ProcessState.ENDED);
        log.info("IN saveDealership - thread with id {} ended", id);

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


    private List<Dealership> parseCsvFileToDealership(final MultipartFile file) {

        DataObject dataObject = fileRepository.findById(1L).get();
        MultipartFile multipartFile = new MockMultipartFile(dataObject.getName(), dataObject.getFile());
        List<Dealership> dealershipList = new ArrayList<>();

        try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            synchronized (this) {
                headers = Arrays.asList(bufferedReader.readLine().split(","));
            }

            CsvToBean<Dealership> cb = new CsvToBeanBuilder<Dealership>(bufferedReader)
                    .withType(Dealership.class)
                    .build();

            dealershipList.addAll(cb.parse());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dealershipList;
    }
}
