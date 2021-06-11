package com.test.autobusiness.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

@Component
public class ResponseHelper {

    public String getResponse(String key, String value) {

        ObjectNode objectNode = (new ObjectMapper()).createObjectNode();
        objectNode.put(key, value);
        return objectNode.toPrettyString();
    }

    public String getResponse(String key, long value) {

        ObjectNode objectNode = (new ObjectMapper()).createObjectNode();
        objectNode.put(key, value);
        return objectNode.toPrettyString();
    }
}
