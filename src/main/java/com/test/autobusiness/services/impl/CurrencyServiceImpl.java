package com.test.autobusiness.services.impl;

import com.test.autobusiness.entities.dto.currency.CurrencyDTO;
import com.test.autobusiness.services.CurrencyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Primary
@Service("currencyServiceImpl")
public class CurrencyServiceImpl implements CurrencyService {

    private final RestTemplate restTemplate;

    @Value("${currency.client.url}")
    private String uri;

    public CurrencyServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CurrencyDTO getExchangeRates() {

        return restTemplate.getForObject(uri, CurrencyDTO.class);
    }


}
