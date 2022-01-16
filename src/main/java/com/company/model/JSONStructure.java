package com.company.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JSONStructure {

    @JsonProperty(value = "metadata", required = true)
    private Metadata metadata;
    @JsonProperty(value = "services",required = true)
    private ArrayList<Service> services;
    @JsonProperty(value = "artifacts",required = true)
    private ArrayList<JsonNode> artifacts;
    @JsonProperty(value = "script",required = true)
    private ArrayList<Script> script;
    @JsonProperty(value = "rpm")
    private Rpm rpm;
    @JsonProperty(value = "parameters",required = true)
    private Parameter parameters;

}
