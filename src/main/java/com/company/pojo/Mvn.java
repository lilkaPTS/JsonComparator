package com.company.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mvn {
    @JsonProperty(value = "groupId", required = true)
    private String groupId;
    @JsonProperty(value = "artifactId", required = true)
    private String artifactId;
    @JsonProperty(value = "version", required = true)
    private String version;
    @JsonProperty(value = "service_name")
    private String serviceName;
    @JsonProperty(value = "classifier")
    private String classifier;
    @JsonProperty(value = "mvn_type", required = true)
    private String mvnType;
    @JsonProperty(value = "mvn_repository", required = true)
    private String mvnRepository;
    @JsonProperty(value = "hashes", required = true)
    private Hashes hashes;
}
