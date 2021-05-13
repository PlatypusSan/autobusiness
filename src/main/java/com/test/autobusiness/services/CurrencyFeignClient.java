package com.test.autobusiness.services;

import com.test.autobusiness.entities.dto.currency.CurrencyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "currencyClient", url = "${currency.client.url}")
public interface CurrencyFeignClient {

    @GetMapping(consumes = "application/javascript;charset=utf-8")
    CurrencyDTO getCurrency();
}
