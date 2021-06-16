package com.test.autobusiness.services;

import com.test.autobusiness.dto.details.DetailsResponse;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface DetailsService {

    Set<DetailsResponse> getAllDetails();
}
