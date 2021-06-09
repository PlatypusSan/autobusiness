package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.dto.dealership.DealershipResponse;
import com.test.autobusiness.entities.mappers.DealershipMapper;
import com.test.autobusiness.services.DealershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/dealership")
@RequiredArgsConstructor
public class DealershipController {

    private final DealershipService dealershipService;
    private final DealershipMapper dealershipMapper;

    @PostMapping
    public void uploadFile(@RequestParam("file") MultipartFile multipartFile) throws Exception {

        dealershipService.saveDealerships(multipartFile);
    }

    @GetMapping
    public List<DealershipResponse> getAllDealers() {

        List<DealershipResponse> dealershipResponses =
                dealershipMapper.dealershipListToDealershipResponseList(dealershipService.getAllDealerships());
        return dealershipResponses;
    }
}
