package com.company.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

@Data
public class Services {
    @JsonProperty(value = "service-short-name")
    private String serviceShortName;
    @JsonProperty(value = "service_name",required = true)
    private String serviceName;
    @JsonProperty(value ="artifact_type",required = true)
    private String artifactType;
    @JsonProperty(value ="docker_registry",required = true)
    private String dockerRegistry;
    @JsonProperty(value ="docker_image_name",required = true)
    private String dockerImageName;
    @JsonProperty(value ="docker_tag",required = true)
    private String dockerTag;
    @JsonProperty(value ="force")
    private boolean force;
    @JsonProperty(value ="github_repository")
    private String gitRepository;
    @JsonProperty(value ="github_branch")
    private String githubBranch;
    @JsonProperty(value ="github_hash")
    private String githubBash;
    @JsonProperty(value ="hashes",required = true)
    private Hashes hashes;
}
