package com.test.autobusiness.services.impl;

import com.test.autobusiness.entities.dto.currency.CurrencyDTO;
import com.test.autobusiness.repositories.CurrencyRepository;
import com.test.autobusiness.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;
    private final MongoTemplate mongoTemplate;

    @Value("${currency.client.url}")
    private String uri;

    @Override
    @Transactional
    public CurrencyDTO getExchangeRates() {

        return getRatesViaTemplate();
    }

    private CurrencyDTO getRatesViaRepository() {

        CurrencyDTO currency;
        try {
            currency = restTemplate.getForObject(uri, CurrencyDTO.class);
            currencyRepository.deleteAll();
            currencyRepository.save(currency);

        } catch (Exception e) {
            currency = currencyRepository.findAll().get(0);
        }
        return currency;
    }

    private CurrencyDTO getRatesViaTemplate() {

        CurrencyDTO currency;
        try {
            currency = restTemplate.getForObject(uri, CurrencyDTO.class);
            Update update = new Update();
            update.set("date", currency.getDate());
            update.set("valutes", currency.getValutes());
            mongoTemplate.updateFirst(new Query(), update, CurrencyDTO.class);

        } catch (Exception e) {
            currency = mongoTemplate.findOne(new Query(), CurrencyDTO.class);
        }
        return currency;
    }


}
