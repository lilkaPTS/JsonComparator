package com.company.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Script {
    @JsonProperty(value = "service-short-name")
    public String serviceShortName;
    @JsonProperty(value = "start-point")
    public String startPoint;
    @JsonProperty(value = "end-point")
    public String endPoint;
    @JsonProperty(value = "script_name", required = true)
    public String scriptName;
    @JsonProperty(value = "hashes", required = true)
    public Hashes hashes;
    @JsonProperty(value = "url", required = true)
    public String url;
}
