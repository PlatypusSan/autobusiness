package com.test.autobusiness.services.impl;

import com.test.autobusiness.dto.currency.CurrencyDTO;
import com.test.autobusiness.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {

    private final RestTemplate restTemplate;

    @Value("${currency.client.url}")
    private String uri;

    @Override
    @Transactional
    @Cacheable(cacheNames = "currencies", cacheResolver = "customCacheResolver")
    public CurrencyDTO getExchangeRates() {

        log.info("IN getExchangeRates - currency received without using cache");
        return restTemplate.getForObject(uri, CurrencyDTO.class);
    }
}
