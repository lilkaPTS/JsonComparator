
package model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "service-short-name",
    "service_name",
    "artifact_type",
    "docker_image_name",
    "docker_tag",
    "force",
    "github_repository",
    "github_branch",
    "github_hash",
    "hashes",
    "docker_registry"
})
@Generated("jsonschema2pojo")
public class Service {

    @JsonProperty("service-short-name")
    private String serviceShortName;
    @JsonProperty("service_name")
    private String serviceName;
    @JsonProperty("artifact_type")
    private String artifactType;
    @JsonProperty("docker_image_name")
    private String dockerImageName;
    @JsonProperty("docker_tag")
    private String dockerTag;
    @JsonProperty("force")
    private Boolean force;
    @JsonProperty("github_repository")
    private String githubRepository;
    @JsonProperty("github_branch")
    private String githubBranch;
    @JsonProperty("github_hash")
    private String githubHash;
    @JsonProperty("hashes")
    private Hashes hashes;
    @JsonProperty("docker_registry")
    private String dockerRegistry;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("service-short-name")
    public String getServiceShortName() {
        return serviceShortName;
    }

    @JsonProperty("service-short-name")
    public void setServiceShortName(String serviceShortName) {
        this.serviceShortName = serviceShortName;
    }

    public Service withServiceShortName(String serviceShortName) {
        this.serviceShortName = serviceShortName;
        return this;
    }

    @JsonProperty("service_name")
    public String getServiceName() {
        return serviceName;
    }

    @JsonProperty("service_name")
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Service withServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    @JsonProperty("artifact_type")
    public String getArtifactType() {
        return artifactType;
    }

    @JsonProperty("artifact_type")
    public void setArtifactType(String artifactType) {
        this.artifactType = artifactType;
    }

    public Service withArtifactType(String artifactType) {
        this.artifactType = artifactType;
        return this;
    }

    @JsonProperty("docker_image_name")
    public String getDockerImageName() {
        return dockerImageName;
    }

    @JsonProperty("docker_image_name")
    public void setDockerImageName(String dockerImageName) {
        this.dockerImageName = dockerImageName;
    }

    public Service withDockerImageName(String dockerImageName) {
        this.dockerImageName = dockerImageName;
        return this;
    }

    @JsonProperty("docker_tag")
    public String getDockerTag() {
        return dockerTag;
    }

    @JsonProperty("docker_tag")
    public void setDockerTag(String dockerTag) {
        this.dockerTag = dockerTag;
    }

    public Service withDockerTag(String dockerTag) {
        this.dockerTag = dockerTag;
        return this;
    }

    @JsonProperty("force")
    public Boolean getForce() {
        return force;
    }

    @JsonProperty("force")
    public void setForce(Boolean force) {
        this.force = force;
    }

    public Service withForce(Boolean force) {
        this.force = force;
        return this;
    }

    @JsonProperty("github_repository")
    public String getGithubRepository() {
        return githubRepository;
    }

    @JsonProperty("github_repository")
    public void setGithubRepository(String githubRepository) {
        this.githubRepository = githubRepository;
    }

    public Service withGithubRepository(String githubRepository) {
        this.githubRepository = githubRepository;
        return this;
    }

    @JsonProperty("github_branch")
    public String getGithubBranch() {
        return githubBranch;
    }

    @JsonProperty("github_branch")
    public void setGithubBranch(String githubBranch) {
        this.githubBranch = githubBranch;
    }

    public Service withGithubBranch(String githubBranch) {
        this.githubBranch = githubBranch;
        return this;
    }

    @JsonProperty("github_hash")
    public String getGithubHash() {
        return githubHash;
    }

    @JsonProperty("github_hash")
    public void setGithubHash(String githubHash) {
        this.githubHash = githubHash;
    }

    public Service withGithubHash(String githubHash) {
        this.githubHash = githubHash;
        return this;
    }

    @JsonProperty("hashes")
    public Hashes getHashes() {
        return hashes;
    }

    @JsonProperty("hashes")
    public void setHashes(Hashes hashes) {
        this.hashes = hashes;
    }

    public Service withHashes(Hashes hashes) {
        this.hashes = hashes;
        return this;
    }

    @JsonProperty("docker_registry")
    public String getDockerRegistry() {
        return dockerRegistry;
    }

    @JsonProperty("docker_registry")
    public void setDockerRegistry(String dockerRegistry) {
        this.dockerRegistry = dockerRegistry;
    }

    public Service withDockerRegistry(String dockerRegistry) {
        this.dockerRegistry = dockerRegistry;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Service withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Service.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("serviceShortName");
        sb.append('=');
        sb.append(((this.serviceShortName == null)?"<null>":this.serviceShortName));
        sb.append(',');
        sb.append("serviceName");
        sb.append('=');
        sb.append(((this.serviceName == null)?"<null>":this.serviceName));
        sb.append(',');
        sb.append("artifactType");
        sb.append('=');
        sb.append(((this.artifactType == null)?"<null>":this.artifactType));
        sb.append(',');
        sb.append("dockerImageName");
        sb.append('=');
        sb.append(((this.dockerImageName == null)?"<null>":this.dockerImageName));
        sb.append(',');
        sb.append("dockerTag");
        sb.append('=');
        sb.append(((this.dockerTag == null)?"<null>":this.dockerTag));
        sb.append(',');
        sb.append("force");
        sb.append('=');
        sb.append(((this.force == null)?"<null>":this.force));
        sb.append(',');
        sb.append("githubRepository");
        sb.append('=');
        sb.append(((this.githubRepository == null)?"<null>":this.githubRepository));
        sb.append(',');
        sb.append("githubBranch");
        sb.append('=');
        sb.append(((this.githubBranch == null)?"<null>":this.githubBranch));
        sb.append(',');
        sb.append("githubHash");
        sb.append('=');
        sb.append(((this.githubHash == null)?"<null>":this.githubHash));
        sb.append(',');
        sb.append("hashes");
        sb.append('=');
        sb.append(((this.hashes == null)?"<null>":this.hashes));
        sb.append(',');
        sb.append("dockerRegistry");
        sb.append('=');
        sb.append(((this.dockerRegistry == null)?"<null>":this.dockerRegistry));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.artifactType == null)? 0 :this.artifactType.hashCode()));
        result = ((result* 31)+((this.serviceShortName == null)? 0 :this.serviceShortName.hashCode()));
        result = ((result* 31)+((this.serviceName == null)? 0 :this.serviceName.hashCode()));
        result = ((result* 31)+((this.githubHash == null)? 0 :this.githubHash.hashCode()));
        result = ((result* 31)+((this.githubRepository == null)? 0 :this.githubRepository.hashCode()));
        result = ((result* 31)+((this.dockerImageName == null)? 0 :this.dockerImageName.hashCode()));
        result = ((result* 31)+((this.hashes == null)? 0 :this.hashes.hashCode()));
        result = ((result* 31)+((this.dockerRegistry == null)? 0 :this.dockerRegistry.hashCode()));
        result = ((result* 31)+((this.force == null)? 0 :this.force.hashCode()));
        result = ((result* 31)+((this.dockerTag == null)? 0 :this.dockerTag.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.githubBranch == null)? 0 :this.githubBranch.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Service) == false) {
            return false;
        }
        Service rhs = ((Service) other);
        return (((((((((((((this.artifactType == rhs.artifactType)||((this.artifactType!= null)&&this.artifactType.equals(rhs.artifactType)))&&((this.serviceShortName == rhs.serviceShortName)||((this.serviceShortName!= null)&&this.serviceShortName.equals(rhs.serviceShortName))))&&((this.serviceName == rhs.serviceName)||((this.serviceName!= null)&&this.serviceName.equals(rhs.serviceName))))&&((this.githubHash == rhs.githubHash)||((this.githubHash!= null)&&this.githubHash.equals(rhs.githubHash))))&&((this.githubRepository == rhs.githubRepository)||((this.githubRepository!= null)&&this.githubRepository.equals(rhs.githubRepository))))&&((this.dockerImageName == rhs.dockerImageName)||((this.dockerImageName!= null)&&this.dockerImageName.equals(rhs.dockerImageName))))&&((this.hashes == rhs.hashes)||((this.hashes!= null)&&this.hashes.equals(rhs.hashes))))&&((this.dockerRegistry == rhs.dockerRegistry)||((this.dockerRegistry!= null)&&this.dockerRegistry.equals(rhs.dockerRegistry))))&&((this.force == rhs.force)||((this.force!= null)&&this.force.equals(rhs.force))))&&((this.dockerTag == rhs.dockerTag)||((this.dockerTag!= null)&&this.dockerTag.equals(rhs.dockerTag))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.githubBranch == rhs.githubBranch)||((this.githubBranch!= null)&&this.githubBranch.equals(rhs.githubBranch))));
    }

}
