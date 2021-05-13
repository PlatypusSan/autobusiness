package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.dto.detailsdto.DetailsResponse;
import com.test.autobusiness.services.DetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/v1/details")
public class DetailsController {

    private final DetailsService detailsService;

    public DetailsController(DetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @GetMapping()
    public Set<DetailsResponse> getDetails() {
        return detailsService.getAllDetails();
    }
}
