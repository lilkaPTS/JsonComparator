package com.company.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Hashes {
    @JsonProperty(value = "sha1",required = true)
    private String sha1;
    @JsonProperty(value = "sha256",required = true)
    private String sha256;
}
