package com.company.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metadata {
    @JsonProperty(value = "description", required = true)
    private Description description;
    @JsonProperty(value = "application", required = true)
    private Application application;
}
