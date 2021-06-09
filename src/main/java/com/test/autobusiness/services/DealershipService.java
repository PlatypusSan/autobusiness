package com.test.autobusiness.services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.test.autobusiness.entities.Dealership;
import com.test.autobusiness.repositories.DealershipRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

@Service
@Slf4j
@RequiredArgsConstructor
public class DealershipService {

    private final DealershipRepository dealershipRepository;

    @Getter
    private static List<String> headers;

    @Transactional
    public CompletableFuture<List<Dealership>> saveDealerships(final MultipartFile file) throws Exception {

        List<Dealership> dealershipList = parseCsvFileToDealership(file);
        dealershipList.forEach(dealership -> {
            dealership.getContacts().forEach(contact -> {
                contact.setDealership(dealership);
            });
        });
        dealershipRepository.saveAll(dealershipList);

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

    private List<Dealership> parseCsvFileToDealership(MultipartFile file) throws IOException {

        List<Dealership> dealershipList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        headers = Arrays.asList(bufferedReader.readLine().split(","));
        CsvToBean<Dealership> cb = new CsvToBeanBuilder<Dealership>(bufferedReader)
                .withType(Dealership.class)
                .build();

        dealershipList.addAll(cb.parse());
        bufferedReader.close();

        return dealershipList;
    }


}
