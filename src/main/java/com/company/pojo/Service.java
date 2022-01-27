package com.company.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Comparator;

@Data
public class Service implements Comparable<Service> {
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
    
    @Override
    public int compareTo(Service o) {
        int result = 1;
        result*=this.serviceName.equals(o.serviceName) ? 1 : 2;
        result*=this.artifactType.equals(o.artifactType) ? 1 : 3;
        result*=this.dockerRegistry.equals(o.dockerRegistry) ? 1 : 5;
        result*=this.dockerImageName.equals(o.dockerImageName) ? 1 : 7;
        result*=this.dockerTag.equals(o.dockerTag) ? 1 : 11;
        result*=this.hashes.equals(o.hashes) ? 1 : 13;
        return result==1 ? 0 : result;
    }
}
