package com.test.autobusiness.util.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.opencsv.bean.AbstractBeanField;
import com.test.autobusiness.services.impl.DealershipServiceImpl;

public class PropertyConverter extends AbstractBeanField<String, String> {

    @Override
    protected Object convert(String s) {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put(DealershipServiceImpl.getHeaders().get(4), s);
        return objectNode.toString();
    }
}
