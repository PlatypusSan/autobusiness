package com.test.autobusiness.services;

import com.test.autobusiness.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "currencyClient", url = "${currency.client.url}", configuration = FeignClientConfig.class)
public interface CurrencyFeignClient {

    @GetMapping(value = "", consumes = "application/json;charset=utf-8")
    String getCurrencyDTO();
}
