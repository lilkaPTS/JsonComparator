package com.company.model;


import com.fasterxml.jackson.annotation.JsonProperty;
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
    private ArrayList<Services> services;

}
