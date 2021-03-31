package com.test.autobusiness.services;

import com.test.autobusiness.entities.Details;
import com.test.autobusiness.repositories.DetailsRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DetailsService {

    private final DetailsRepository detailsRepository;

    public DetailsService(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }

    public Set<Details> getAllDetails() {
        return detailsRepository.findAll();
    }


}
