package com.company.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class JSONService {

    public JsonNode getJsonNode(String content) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(content);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return mapper.nullNode();
    }
}
