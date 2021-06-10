package com.test.autobusiness.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.test.autobusiness.entities.dto.dealership.DealershipResponse;
import com.test.autobusiness.entities.mappers.DealershipMapper;
import com.test.autobusiness.services.DealershipService;
import com.test.autobusiness.services.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String uploadFile(@RequestParam("file") final MultipartFile multipartFile) throws Exception {

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

    @GetMapping
    public List<DealershipResponse> getAllDealerships() {

        return dealershipMapper.dealershipListToDealershipResponseList(dealershipService.getAllDealerships());
    }
}
