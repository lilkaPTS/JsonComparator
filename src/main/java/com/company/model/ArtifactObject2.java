package com.company.model;

import com.company.pojo.Hashes;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtifactObject2 implements ArtifactObject {

    @JsonProperty(value = "service-short-name")
    private String serviceShortName;
    @JsonProperty(value = "service_name")
    private String serviceName;
    @JsonProperty(value = "hashes", required = true)
    private Hashes hashes;
    @JsonProperty(value = "file", required = true)
    @NotEmpty
    private ArrayList<String> file;
    @JsonProperty(value = "target_repository", required = true)
    private String targetRepository;
}
