package com.test.autobusiness.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.autobusiness.entities.dto.currencydto.CurrencyDTO;
import com.test.autobusiness.services.CurrencyFeignClient;
import com.test.autobusiness.services.CurrencyService;
import org.springframework.stereotype.Service;

@Service("feignCurrencyServiceImpl")
public class FeignCurrencyServiceImpl implements CurrencyService {

    private final CurrencyFeignClient currencyFeignClient;

    public FeignCurrencyServiceImpl(CurrencyFeignClient currencyFeignClient) {
        this.currencyFeignClient = currencyFeignClient;
    }

    @Override
    public CurrencyDTO getExchangeRates() {


        String result = currencyFeignClient.getCurrencyDTO();

        ObjectMapper objectMapper = new ObjectMapper();
        CurrencyDTO currencyDTO = new CurrencyDTO();
        try {
            currencyDTO = objectMapper.readValue(result, CurrencyDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return currencyDTO;
    }
}
