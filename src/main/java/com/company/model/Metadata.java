package com.company.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metadata {
    @JsonProperty(value = "description", required = true)
    @NonNull
    private Description description;
    @JsonProperty(value = "application", required = true)
    @NonNull
    private Application application;
}
