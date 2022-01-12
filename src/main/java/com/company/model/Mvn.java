
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
    "groupId",
    "artifactId",
    "version",
    "service_name",
    "classifier",
    "mvn_type",
    "mvn_repository",
    "hashes"
})
@Generated("jsonschema2pojo")
public class Mvn {

    @JsonProperty("groupId")
    private String groupId;
    @JsonProperty("artifactId")
    private String artifactId;
    @JsonProperty("version")
    private String version;
    @JsonProperty("service_name")
    private String serviceName;
    @JsonProperty("classifier")
    private String classifier;
    @JsonProperty("mvn_type")
    private String mvnType;
    @JsonProperty("mvn_repository")
    private String mvnRepository;
    @JsonProperty("hashes")
    private Hashes__1 hashes;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("groupId")
    public String getGroupId() {
        return groupId;
    }

    @JsonProperty("groupId")
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Mvn withGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    @JsonProperty("artifactId")
    public String getArtifactId() {
        return artifactId;
    }

    @JsonProperty("artifactId")
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public Mvn withArtifactId(String artifactId) {
        this.artifactId = artifactId;
        return this;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    public Mvn withVersion(String version) {
        this.version = version;
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

    public Mvn withServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    @JsonProperty("classifier")
    public String getClassifier() {
        return classifier;
    }

    @JsonProperty("classifier")
    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    public Mvn withClassifier(String classifier) {
        this.classifier = classifier;
        return this;
    }

    @JsonProperty("mvn_type")
    public String getMvnType() {
        return mvnType;
    }

    @JsonProperty("mvn_type")
    public void setMvnType(String mvnType) {
        this.mvnType = mvnType;
    }

    public Mvn withMvnType(String mvnType) {
        this.mvnType = mvnType;
        return this;
    }

    @JsonProperty("mvn_repository")
    public String getMvnRepository() {
        return mvnRepository;
    }

    @JsonProperty("mvn_repository")
    public void setMvnRepository(String mvnRepository) {
        this.mvnRepository = mvnRepository;
    }

    public Mvn withMvnRepository(String mvnRepository) {
        this.mvnRepository = mvnRepository;
        return this;
    }

    @JsonProperty("hashes")
    public Hashes__1 getHashes() {
        return hashes;
    }

    @JsonProperty("hashes")
    public void setHashes(Hashes__1 hashes) {
        this.hashes = hashes;
    }

    public Mvn withHashes(Hashes__1 hashes) {
        this.hashes = hashes;
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

    public Mvn withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Mvn.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("groupId");
        sb.append('=');
        sb.append(((this.groupId == null)?"<null>":this.groupId));
        sb.append(',');
        sb.append("artifactId");
        sb.append('=');
        sb.append(((this.artifactId == null)?"<null>":this.artifactId));
        sb.append(',');
        sb.append("version");
        sb.append('=');
        sb.append(((this.version == null)?"<null>":this.version));
        sb.append(',');
        sb.append("serviceName");
        sb.append('=');
        sb.append(((this.serviceName == null)?"<null>":this.serviceName));
        sb.append(',');
        sb.append("classifier");
        sb.append('=');
        sb.append(((this.classifier == null)?"<null>":this.classifier));
        sb.append(',');
        sb.append("mvnType");
        sb.append('=');
        sb.append(((this.mvnType == null)?"<null>":this.mvnType));
        sb.append(',');
        sb.append("mvnRepository");
        sb.append('=');
        sb.append(((this.mvnRepository == null)?"<null>":this.mvnRepository));
        sb.append(',');
        sb.append("hashes");
        sb.append('=');
        sb.append(((this.hashes == null)?"<null>":this.hashes));
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
        result = ((result* 31)+((this.mvnType == null)? 0 :this.mvnType.hashCode()));
        result = ((result* 31)+((this.mvnRepository == null)? 0 :this.mvnRepository.hashCode()));
        result = ((result* 31)+((this.groupId == null)? 0 :this.groupId.hashCode()));
        result = ((result* 31)+((this.classifier == null)? 0 :this.classifier.hashCode()));
        result = ((result* 31)+((this.hashes == null)? 0 :this.hashes.hashCode()));
        result = ((result* 31)+((this.artifactId == null)? 0 :this.artifactId.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.serviceName == null)? 0 :this.serviceName.hashCode()));
        result = ((result* 31)+((this.version == null)? 0 :this.version.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Mvn) == false) {
            return false;
        }
        Mvn rhs = ((Mvn) other);
        return ((((((((((this.mvnType == rhs.mvnType)||((this.mvnType!= null)&&this.mvnType.equals(rhs.mvnType)))&&((this.mvnRepository == rhs.mvnRepository)||((this.mvnRepository!= null)&&this.mvnRepository.equals(rhs.mvnRepository))))&&((this.groupId == rhs.groupId)||((this.groupId!= null)&&this.groupId.equals(rhs.groupId))))&&((this.classifier == rhs.classifier)||((this.classifier!= null)&&this.classifier.equals(rhs.classifier))))&&((this.hashes == rhs.hashes)||((this.hashes!= null)&&this.hashes.equals(rhs.hashes))))&&((this.artifactId == rhs.artifactId)||((this.artifactId!= null)&&this.artifactId.equals(rhs.artifactId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.serviceName == rhs.serviceName)||((this.serviceName!= null)&&this.serviceName.equals(rhs.serviceName))))&&((this.version == rhs.version)||((this.version!= null)&&this.version.equals(rhs.version))));
    }

}
