package com.test.autobusiness.entities.dto.currencydto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValutesDTO {

    @JsonProperty("BYN")
    private Valute ruble;

    @JsonProperty("EUR")
    private Valute euro;

    @JsonProperty("USD")
    private Valute dollar;
}