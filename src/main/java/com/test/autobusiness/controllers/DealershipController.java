package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.dto.dealership.DealershipResponse;
import com.test.autobusiness.entities.mappers.DealershipMapper;
import com.test.autobusiness.services.DealershipService;
import com.test.autobusiness.services.FileService;
import com.test.autobusiness.services.states.JobState;
import com.test.autobusiness.services.states.State;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    public ResponseEntity<String> importFile(@RequestParam("file") final MultipartFile multipartFile) throws Exception {

        long jobId = dealershipService.incrementJobId();
        dealershipService.saveDealerships(fileService.saveImportFile(multipartFile));
        return ResponseEntity
                .ok()
                .header("Import-Job", WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(DealershipController.class)
                                .getImportStatus(jobId))
                        .withSelfRel()
                        .getHref())
                .build();
    }

    @GetMapping(path = "/import-status/{id}")
    public ResponseEntity<String> getImportStatus(@PathVariable long id) {

        return ResponseEntity
                .ok()
                .header("Import-Status", dealershipService.getJobState(id)
                        .getState()
                        .name())
                .build();
    }

    @GetMapping(path = "/export")
    public ResponseEntity<String> exportFile() throws IOException {

        long jobId = dealershipService.incrementJobId();
        dealershipService.writeCsvFileFromDealership();
        return ResponseEntity.ok()
                .header("Import-Job", WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(DealershipController.class)
                                .getExportStatus(jobId))
                        .withSelfRel()
                        .getHref())
                .build();
    }

    @GetMapping(path = "/export-status/{id}")
    public ResponseEntity<String> getExportStatus(@PathVariable long id) throws IOException {

        JobState jobState = dealershipService.getJobState(id);
        if (jobState.getState() == State.ENDED) {
            return ResponseEntity
                    .status(HttpStatus.SEE_OTHER)
                    .location(URI.create("http://localhost:9090/api/v1/dealership/export/" + id))
                    .build();
        } else {
            return ResponseEntity.ok()
                    .header("Import-Status", dealershipService.getJobState(id)
                            .getState()
                            .name())
                    .build();
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

    @GetMapping(path = "/{id}")
    public DealershipResponse getDealership(@PathVariable long id) {

        return dealershipMapper.dealershipToDealershipResponse(dealershipService.getDealership(id));
    }

    @GetMapping
    public List<DealershipResponse> getAllDealerships() {

        return dealershipMapper.dealershipListToDealershipResponseList(dealershipService.getAllDealerships());
    }
}
