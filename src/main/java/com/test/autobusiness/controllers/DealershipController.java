package com.test.autobusiness.controllers;

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
    public void uploadFile(@RequestParam("file") final MultipartFile multipartFile) throws Exception {

        long id = 1;

        long fileId = fileService.saveImportFile(multipartFile);
        dealershipService.saveDealerships(fileId, id);
    }

    @GetMapping(path = "/import-status")
    public void getImportStatus() {
        log.info("IN getImportStatus - {}", dealershipService.getProcessState(1).name());
    }

    @GetMapping
    public List<DealershipResponse> getAllDealerships() {

        List<DealershipResponse> dealershipResponses =
                dealershipMapper.dealershipListToDealershipResponseList(dealershipService.getAllDealerships());
        return dealershipResponses;
    }
}
