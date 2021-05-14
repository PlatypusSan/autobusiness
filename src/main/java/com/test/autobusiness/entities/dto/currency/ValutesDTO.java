package com.test.autobusiness.entities.dto.currency;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValutesDTO {

    /*@JsonProperty("BYN")
    private Valute ruble;

    @JsonProperty("EUR")
    private Valute euro;

    @JsonProperty("USD")
    private Valute dollar;*/

    private Map<String, String> properties;

    @JsonAnySetter
    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    /*public Map<String, String> setProperties() {
        return properties;
    }*/
}