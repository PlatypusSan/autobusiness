package com.test.autobusiness.dto.currency;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.test.autobusiness.config.CurrencyDeserializer;
import com.test.autobusiness.entities.enums.Currency;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Valute {

    @JsonProperty(value = "CharCode")
    @JsonDeserialize(using = CurrencyDeserializer.class)
    private Currency code;

    @JsonProperty("Value")
    private double value;

    @JsonProperty("Nominal")
    private int nominal;
}