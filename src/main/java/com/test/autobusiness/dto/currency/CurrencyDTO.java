package com.test.autobusiness.dto.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "currencies")
public class CurrencyDTO implements Serializable {

    @JsonProperty("Date")
    private String date;

    @JsonProperty("Valute")
    private ValutesDTO valutes;

}
