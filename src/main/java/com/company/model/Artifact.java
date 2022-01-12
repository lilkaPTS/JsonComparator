
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "mvn",
    "service-short-name",
    "service_name",
    "hashes",
    "file",
    "target_repository"
})
@Generated("jsonschema2pojo")
public class Artifact {

    @JsonProperty("mvn")
    private List<Mvn> mvn = new ArrayList<Mvn>();
    @JsonProperty("service-short-name")
    private String serviceShortName;
    @JsonProperty("service_name")
    private String serviceName;
    @JsonProperty("hashes")
    private Hashes__2 hashes;
    @JsonProperty("file")
    private List<String> file = new ArrayList<String>();
    @JsonProperty("target_repository")
    private String targetRepository;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("mvn")
    public List<Mvn> getMvn() {
        return mvn;
    }

    @JsonProperty("mvn")
    public void setMvn(List<Mvn> mvn) {
        this.mvn = mvn;
    }

    public Artifact withMvn(List<Mvn> mvn) {
        this.mvn = mvn;
        return this;
    }

    @JsonProperty("service-short-name")
    public String getServiceShortName() {
        return serviceShortName;
    }

    @JsonProperty("service-short-name")
    public void setServiceShortName(String serviceShortName) {
        this.serviceShortName = serviceShortName;
    }

    public Artifact withServiceShortName(String serviceShortName) {
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

    public Artifact withServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    @JsonProperty("hashes")
    public Hashes__2 getHashes() {
        return hashes;
    }

    @JsonProperty("hashes")
    public void setHashes(Hashes__2 hashes) {
        this.hashes = hashes;
    }

    public Artifact withHashes(Hashes__2 hashes) {
        this.hashes = hashes;
        return this;
    }

    @JsonProperty("file")
    public List<String> getFile() {
        return file;
    }

    @JsonProperty("file")
    public void setFile(List<String> file) {
        this.file = file;
    }

    public Artifact withFile(List<String> file) {
        this.file = file;
        return this;
    }

    @JsonProperty("target_repository")
    public String getTargetRepository() {
        return targetRepository;
    }

    @JsonProperty("target_repository")
    public void setTargetRepository(String targetRepository) {
        this.targetRepository = targetRepository;
    }

    public Artifact withTargetRepository(String targetRepository) {
        this.targetRepository = targetRepository;
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

    public Artifact withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Artifact.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("mvn");
        sb.append('=');
        sb.append(((this.mvn == null)?"<null>":this.mvn));
        sb.append(',');
        sb.append("serviceShortName");
        sb.append('=');
        sb.append(((this.serviceShortName == null)?"<null>":this.serviceShortName));
        sb.append(',');
        sb.append("serviceName");
        sb.append('=');
        sb.append(((this.serviceName == null)?"<null>":this.serviceName));
        sb.append(',');
        sb.append("hashes");
        sb.append('=');
        sb.append(((this.hashes == null)?"<null>":this.hashes));
        sb.append(',');
        sb.append("file");
        sb.append('=');
        sb.append(((this.file == null)?"<null>":this.file));
        sb.append(',');
        sb.append("targetRepository");
        sb.append('=');
        sb.append(((this.targetRepository == null)?"<null>":this.targetRepository));
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
        result = ((result* 31)+((this.file == null)? 0 :this.file.hashCode()));
        result = ((result* 31)+((this.mvn == null)? 0 :this.mvn.hashCode()));
        result = ((result* 31)+((this.serviceShortName == null)? 0 :this.serviceShortName.hashCode()));
        result = ((result* 31)+((this.hashes == null)? 0 :this.hashes.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.serviceName == null)? 0 :this.serviceName.hashCode()));
        result = ((result* 31)+((this.targetRepository == null)? 0 :this.targetRepository.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Artifact) == false) {
            return false;
        }
        Artifact rhs = ((Artifact) other);
        return ((((((((this.file == rhs.file)||((this.file!= null)&&this.file.equals(rhs.file)))&&((this.mvn == rhs.mvn)||((this.mvn!= null)&&this.mvn.equals(rhs.mvn))))&&((this.serviceShortName == rhs.serviceShortName)||((this.serviceShortName!= null)&&this.serviceShortName.equals(rhs.serviceShortName))))&&((this.hashes == rhs.hashes)||((this.hashes!= null)&&this.hashes.equals(rhs.hashes))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.serviceName == rhs.serviceName)||((this.serviceName!= null)&&this.serviceName.equals(rhs.serviceName))))&&((this.targetRepository == rhs.targetRepository)||((this.targetRepository!= null)&&this.targetRepository.equals(rhs.targetRepository))));
    }

}
