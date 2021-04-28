package com.test.autobusiness.entities.dto.currencydto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Valute {

    @JsonProperty("CharCode")
    private String code;

    @JsonProperty("Value")
    private double value;

    @JsonProperty("Nominal")
    private int nominal;
}