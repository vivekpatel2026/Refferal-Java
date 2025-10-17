package com.refrralApp.refrral.utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

import java.util.Iterator;
@Component
public class Helper {

    public JsonNode merge(JsonNode original, JsonNode updates) {
        Iterator<String> fieldNames = updates.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            ((ObjectNode) original).replace(fieldName, updates.get(fieldName));
        }
        return original;
    }
}
