package com.test.autobusiness.dto.currency;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValutesDTO {

    private Map<String, Valute> properties = new HashMap<>();

    @JsonAnySetter
    public void add(String key, Valute value) {
        properties.put(key, value);
    }
}