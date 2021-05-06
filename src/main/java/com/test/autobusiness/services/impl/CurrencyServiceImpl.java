package com.test.autobusiness.services.impl;

import com.test.autobusiness.entities.dto.currencydto.CurrencyDTO;
import com.test.autobusiness.services.CurrencyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
