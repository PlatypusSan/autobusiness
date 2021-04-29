package com.test.autobusiness.entities.dto.currencydto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CurrencyDTO implements Serializable {

    @JsonProperty("Date")
    private String date;

    @JsonProperty("Valute")
    private ValutesDTO valutes;

}
