package com.test.autobusiness.services.impl;

import com.test.autobusiness.dto.details.DetailsResponse;
import com.test.autobusiness.mappers.DetailsMapper;
import com.test.autobusiness.repositories.DetailsRepository;
import com.test.autobusiness.services.DetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class DetailsServiceImpl implements DetailsService {

    private final DetailsRepository detailsRepository;
    private final DetailsMapper detailsMapper;

    public Set<DetailsResponse> getAllDetails() {
        return detailsMapper.detailsToDetailsResponseSet(detailsRepository.findAll());
    }
}