package com.company.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.Data;

@Data
public class Hashes {
    @JsonProperty("sha1")
    @NonNull
    private String sha1;
    @JsonProperty("sha256")
    @NonNull
    private String sha256;
}
