package com.test.autobusiness.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.test.autobusiness.entities.enums.Currency;

import java.io.IOException;

public class CurrencyDeserializer extends JsonDeserializer<Currency> {

    @Override
    public Currency deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        return Currency.fromString(parser.getValueAsString());
    }
}
