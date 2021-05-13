package com.test.autobusiness.services.impl;

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

        CurrencyDTO currencyDTO = currencyFeignClient.getCurrency();

        return currencyDTO;
    }
}
