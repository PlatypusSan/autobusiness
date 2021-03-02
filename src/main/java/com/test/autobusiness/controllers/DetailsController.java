package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.Details;
import com.test.autobusiness.services.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class DetailsController {

    @Autowired
    DetailsService detailsService;

    @GetMapping(path = "/details")
    public Set<Details> getDetails(){
        return detailsService.getAllDetails();
    }
}
