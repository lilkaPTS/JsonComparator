package com.company.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rpm {
    @JsonProperty(value = "url",required = true)
    public String url;
    @JsonProperty(value = "rpm_repository_name",required = true)
    public String rpmRepositoryName;
    @JsonProperty(value = "hashes",required = true)
    public Hashes hashes;
    @JsonProperty(value = "service-short-name")
    public String serviceShortName;
}
