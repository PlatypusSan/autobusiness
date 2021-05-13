package com.test.autobusiness.entities.dto.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDTO implements Serializable {

    @JsonProperty("Date")
    private String date;

    @JsonProperty("Valute")
    private ValutesDTO valutes;

}
