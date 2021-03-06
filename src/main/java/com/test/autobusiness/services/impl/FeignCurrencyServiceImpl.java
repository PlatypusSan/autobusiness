package com.test.autobusiness.services.impl;

import com.test.autobusiness.clients.CurrencyFeignClient;
import com.test.autobusiness.dto.currency.CurrencyDTO;
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

        return currencyFeignClient.getCurrency();
    }
}
