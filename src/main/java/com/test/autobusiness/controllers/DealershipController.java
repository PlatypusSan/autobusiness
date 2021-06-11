package com.test.autobusiness.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.test.autobusiness.entities.dto.dealership.DealershipResponse;
import com.test.autobusiness.entities.mappers.DealershipMapper;
import com.test.autobusiness.services.DealershipService;
import com.test.autobusiness.services.FileService;
import com.test.autobusiness.services.JobState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/dealership")
@RequiredArgsConstructor
@Slf4j
public class DealershipController {

    private final DealershipService dealershipService;
    private final DealershipMapper dealershipMapper;
    private final FileService fileService;

    @PostMapping(path = "/import")
    public String importFile(@RequestParam("file") final MultipartFile multipartFile) throws Exception {

        ObjectNode objectNode = (new ObjectMapper()).createObjectNode();
        objectNode.put("importJobId", dealershipService.incrementJobId());
        long fileId = fileService.saveImportFile(multipartFile);
        dealershipService.saveDealerships(fileId);
        return objectNode.toPrettyString();
    }

    @GetMapping(path = "/import-status/{id}")
    public String getImportStatus(@PathVariable long id) {

        return dealershipService.getJobState(id).name();
    }

    @GetMapping(path = "/export")
    public String exportFile() {

        ObjectNode objectNode = (new ObjectMapper()).createObjectNode();
        objectNode.put("importJobId", dealershipService.incrementJobId());
        dealershipService.writeCsvFileFromDealership();
        return objectNode.toPrettyString();
    }

    @GetMapping(path = "/export-status/{id}")
    public ResponseEntity<JobState> getExportStatus(@PathVariable long id) throws IOException {

        JobState jobState = dealershipService.getJobState(id);
        if (jobState == JobState.ENDED) {
            return ResponseEntity
                    .status(HttpStatus.SEE_OTHER)
                    .location(URI.create("http://localhost:9090/api/v1/dealership/export/" + id))
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(jobState);
        }
    }

    @GetMapping(path = "/export/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable long id) throws FileNotFoundException {

        long fileId = dealershipService.getJobState(id).getFileId();
        Resource resource = dealershipService.loadFileAsResource(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping
    public List<DealershipResponse> getAllDealerships() {

        return dealershipMapper.dealershipListToDealershipResponseList(dealershipService.getAllDealerships());
    }
}
