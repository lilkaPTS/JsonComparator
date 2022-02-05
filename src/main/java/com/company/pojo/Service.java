package com.company.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return serviceName.equals(service.serviceName) && artifactType.equals(service.artifactType) && dockerRegistry.equals(service.dockerRegistry) && dockerImageName.equals(service.dockerImageName) && dockerTag.equals(service.dockerTag) && hashes.equals(service.hashes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceName, artifactType, dockerRegistry, dockerImageName, dockerTag, hashes);
    }

    @Override
    public int compareTo(Service o) {
        int result = 0;
        if(this.serviceShortName!=null){
            result+=this.serviceShortName.equals(o.serviceShortName) ? 0 : 1;
        } else if(o.serviceShortName!=null) {
            ++result;
        }
        result+=this.serviceName.equals(o.serviceName) ? 0 : 50;
        result+=this.artifactType.equals(o.artifactType) ? 0 : 50;
        result+=this.dockerRegistry.equals(o.dockerRegistry) ? 0 : 50;
        result+=this.dockerImageName.equals(o.dockerImageName) ? 0 : 50;
        result+=this.dockerTag.equals(o.dockerTag) ? 0 : 50;
        result+=this.force == o.force ? 0 : 1;
        if(this.gitRepository!=null){
            result+=this.gitRepository.equals(o.gitRepository) ? 0 : 1;
        } else if(o.gitRepository!=null) {
            ++result;
        }
        if(this.githubBranch!=null){
            result+=this.githubBranch.equals(o.githubBranch) ? 0 : 1;
        } else if(o.githubBranch!=null) {
            ++result;
        }
        if(this.githubBash!=null){
            result+=this.githubBash.equals(o.githubBash) ? 0 : 1;
        } else if(o.githubBash!=null) {
            ++result;
        }
        result+=this.hashes.compareTo(o.hashes);
        return result;
    }

    @Override
    public String toString() {
        return  "{\n" +
                "\"service-short-name\" : \"" + serviceShortName + "\",\n" +
                "\"service_name\" : \"" + serviceName + "\",\n" +
                "\"artifact_type\" : \"" + artifactType + "\",\n" +
                "\"docker_registry\" : \"" + dockerRegistry + "\",\n" +
                "\"docker_image_name\" : \"" + dockerImageName + "\",\n" +
                "\"docker_tag\" : \"" + dockerTag + "\",\n" +
                "\"force\" : " + force + ",\n" +
                "\"github_repository\" : \"" + gitRepository + "\",\n" +
                "\"github_branch\" : \"" + githubBranch + "\",\n" +
                "\"github_hash\" : \"" + githubBash + "\",\n" +
                hashes +
                "\n},";
    }
}
