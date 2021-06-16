package com.test.autobusiness.services;

import com.test.autobusiness.entities.Dealership;
import com.test.autobusiness.services.states.JobState;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public interface DealershipService {

    JobState getJobState(UUID id);

    UUID generateJobId();

    @Transactional
    @Async
    CompletableFuture<List<Dealership>> saveDealerships(long fileId) throws Exception;

    @Transactional
    List<Dealership> getAllDealerships();

    @Transactional
    Dealership getDealership(long id);

    @Transactional
    @Async
    void writeCsvFileFromDealership();

    Resource loadFileAsResource(Long fileId) throws FileNotFoundException;
}
