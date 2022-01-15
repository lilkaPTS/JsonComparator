package com.company.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

@Data
public class Services {
    @JsonProperty("service-short-name")
    private String serviceShortName;
    @JsonProperty("service_name")
    @NonNull
    private String serviceName;
    @JsonProperty("artifact_type")
    @NonNull
    private String artifactType;
    @JsonProperty("docker_registry")
    @NonNull
    private String dockerRegistry;
    @JsonProperty("docker_image_name")
    @NonNull
    private String dockerImageName;
    @JsonProperty("docker_tag")
    @NonNull
    private String dockerTag;
    @JsonProperty("force")
    private boolean force;
    @JsonProperty("github_repository")
    private String gitRepository;
    @JsonProperty("github_branch")
    private String githubBranch;
    @JsonProperty("github_hash")
    private String githubBash;
    @JsonProperty("hashes")
    @NonNull
    private Hashes hashes;
}
