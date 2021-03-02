package com.test.autobusiness.services;

import com.test.autobusiness.entities.Details;
import com.test.autobusiness.repositories.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DetailsService {

    @Autowired
    DetailsRepository detailsRepository;

    public Set<Details> getAllDetails() {
        return detailsRepository.findAll();
    }


}
