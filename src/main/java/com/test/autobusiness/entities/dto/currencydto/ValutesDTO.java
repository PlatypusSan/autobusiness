package com.test.autobusiness.entities.dto.currencydto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ValutesDTO {

    @JsonProperty("BYN")
    private Valute ruble;

    @JsonProperty("EUR")
    private Valute euro;

    @JsonProperty("USD")
    private Valute dollar;
}