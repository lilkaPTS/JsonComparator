package com.company.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hashes {
    @JsonProperty(value = "sha1",required = true)
    private String sha1;
    @JsonProperty(value = "sha256",required = true)
    private String sha256;
}
