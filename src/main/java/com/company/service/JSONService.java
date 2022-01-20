package com.company.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class JSONService {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public JsonNode getJsonNode(String content) {
        try {
            return MAPPER.readTree(content);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return MAPPER.nullNode();
    }

    public String getJSONString(Object value) {
        String result = "";
        try {
            result = MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
