package com.test.autobusiness.services;

import com.test.autobusiness.entities.dto.currencydto.CurrencyDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {

    private final RestTemplate restTemplate;

    public CurrencyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CurrencyDTO getExchangeRates() {

        final String uri = "https://www.cbr-xml-daily.ru/daily_json.js";

        return restTemplate.getForObject(uri, CurrencyDTO.class);
    }


}
