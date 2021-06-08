package com.test.autobusiness.controllers;

import com.test.autobusiness.services.DealershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/dealership")
@RequiredArgsConstructor
public class DealershipController {

    private final DealershipService dealershipService;

    @PostMapping()
    public void uploadFile(@RequestParam("file") MultipartFile multipartFile) throws Exception {

        dealershipService.saveDealerships(multipartFile);
    }
}
