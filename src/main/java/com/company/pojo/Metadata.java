package com.company.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metadata {
    @JsonProperty(value = "description", required = true)
    private Description description;
    @JsonProperty(value = "application", required = true)
    private Application application;
}
