package com.test.autobusiness.entities.dto.currency;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Valute {

    @JsonProperty("CharCode")
    private String code;//TODO: replace with enum

    @JsonProperty("Value")
    private double value;

    @JsonProperty("Nominal")
    private int nominal;
}