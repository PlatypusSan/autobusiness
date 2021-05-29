package com.test.autobusiness.services.impl;

import com.test.autobusiness.entities.dto.currency.CurrencyDTO;
import com.test.autobusiness.repositories.CurrencyRepository;
import com.test.autobusiness.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {

    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;
    private final MongoTemplate mongoTemplate;

    @Value("${currency.client.url}")
    private String uri;

    @Override
    @Transactional
    @Cacheable("currencies")
    public CurrencyDTO getExchangeRates() {

        log.info("IN getExchangeRates - currency received without using cache");
        return getRatesViaTemplate();
    }

    private CurrencyDTO getRatesViaRepository() {

        CurrencyDTO currency;
        try {
            currency = currencyRepository.findAll().get(0);
            log.info("IN getRatesViaRepository - Currency was received from database");
        } catch (Exception e) {
            currency = restTemplate.getForObject(uri, CurrencyDTO.class);
            currencyRepository.deleteAll();
            currencyRepository.save(currency);
            log.info("IN getRatesViaRepository - Currency was received from API");
        }
        return currency;
    }

    private CurrencyDTO getRatesViaTemplate() {

        CurrencyDTO currency;
        try {
            currency = mongoTemplate.findOne(new Query(), CurrencyDTO.class);

            if (currency == null) throw new NullPointerException();
            log.info("IN getRatesViaTemplate - Currency was received from database");

        } catch (Exception e) {
            currency = restTemplate.getForObject(uri, CurrencyDTO.class);
            mongoTemplate.save(currency);
            log.info("IN getRatesViaTemplate - Currency was received from API");
        }
        return currency;
    }
}
