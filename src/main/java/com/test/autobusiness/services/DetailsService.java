package com.test.autobusiness.services;

import com.test.autobusiness.entities.Details;
import com.test.autobusiness.entities.dto.detailsdto.DetailsResponse;
import com.test.autobusiness.entities.mappers.DetailsMapper;
import com.test.autobusiness.repositories.DetailsRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DetailsService {

    private final DetailsRepository detailsRepository;

    private final DetailsMapper detailsMapper;

    public DetailsService(DetailsRepository detailsRepository,
                          DetailsMapper detailsMapper) {
        this.detailsRepository = detailsRepository;
        this.detailsMapper = detailsMapper;
    }

    public Set<DetailsResponse> getAllDetails() {
        return detailsMapper.detailsToDetailsResponseSet(detailsRepository.findAll());
    }


}
