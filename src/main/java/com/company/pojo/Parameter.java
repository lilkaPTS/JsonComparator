package com.company.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parameter {
    @JsonProperty(value = "common")
    private Map<String, String> common;
    @JsonProperty(value = "services")
    private Map<String, Map<String,String>> services;
}
