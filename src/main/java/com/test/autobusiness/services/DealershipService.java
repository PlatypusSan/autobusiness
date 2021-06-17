package com.test.autobusiness.services;

import com.test.autobusiness.dto.dealership.DealershipResponse;
import com.test.autobusiness.entities.Dealership;
import com.test.autobusiness.services.states.JobState;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public interface DealershipService {

    JobState getJobState(UUID id);

    UUID processImportJob(MultipartFile multipartFile) throws Exception;

    UUID processExportJob();

    @Transactional
    @Async
    CompletableFuture<List<Dealership>> saveDealerships(long fileId) throws Exception;

    @Transactional
    List<Dealership> getAllDealerships();

    @Transactional
    Dealership getDealership(long id);

    DealershipResponse getDealershipResponse(long id);

    List<DealershipResponse> getDealershipResponses();

    @Transactional
    @Async
    void writeCsvFileFromDealership();

    Resource loadFileAsResource(UUID jobId) throws FileNotFoundException;
}
