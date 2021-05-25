package com.test.autobusiness.services.impl;

import com.test.autobusiness.entities.dto.currency.CurrencyDTO;
import com.test.autobusiness.repositories.CurrencyRepository;
import com.test.autobusiness.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Primary
@Service("currencyServiceImpl")
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;

    @Value("${currency.client.url}")
    private String uri;

    @Override
    public CurrencyDTO getExchangeRates() {

        CurrencyDTO currencyDTO;
        try {
            currencyDTO = restTemplate.getForObject(uri, CurrencyDTO.class);
            currencyRepository.deleteAll();
            currencyRepository.save(currencyDTO);
        } catch (Exception e) {
            currencyDTO = currencyRepository.findAll().get(0);
        }
        return currencyDTO;
    }


}
