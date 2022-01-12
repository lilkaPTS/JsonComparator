
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
    "url",
    "rpm_repository_name",
    "hashes",
    "service-short-name"
})
@Generated("jsonschema2pojo")
public class Rpm {

    @JsonProperty("url")
    private String url;
    @JsonProperty("rpm_repository_name")
    private String rpmRepositoryName;
    @JsonProperty("hashes")
    private Hashes__4 hashes;
    @JsonProperty("service-short-name")
    private String serviceShortName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public Rpm withUrl(String url) {
        this.url = url;
        return this;
    }

    @JsonProperty("rpm_repository_name")
    public String getRpmRepositoryName() {
        return rpmRepositoryName;
    }

    @JsonProperty("rpm_repository_name")
    public void setRpmRepositoryName(String rpmRepositoryName) {
        this.rpmRepositoryName = rpmRepositoryName;
    }

    public Rpm withRpmRepositoryName(String rpmRepositoryName) {
        this.rpmRepositoryName = rpmRepositoryName;
        return this;
    }

    @JsonProperty("hashes")
    public Hashes__4 getHashes() {
        return hashes;
    }

    @JsonProperty("hashes")
    public void setHashes(Hashes__4 hashes) {
        this.hashes = hashes;
    }

    public Rpm withHashes(Hashes__4 hashes) {
        this.hashes = hashes;
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

    public Rpm withServiceShortName(String serviceShortName) {
        this.serviceShortName = serviceShortName;
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

    public Rpm withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Rpm.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
        sb.append(',');
        sb.append("rpmRepositoryName");
        sb.append('=');
        sb.append(((this.rpmRepositoryName == null)?"<null>":this.rpmRepositoryName));
        sb.append(',');
        sb.append("hashes");
        sb.append('=');
        sb.append(((this.hashes == null)?"<null>":this.hashes));
        sb.append(',');
        sb.append("serviceShortName");
        sb.append('=');
        sb.append(((this.serviceShortName == null)?"<null>":this.serviceShortName));
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
        result = ((result* 31)+((this.hashes == null)? 0 :this.hashes.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
        result = ((result* 31)+((this.rpmRepositoryName == null)? 0 :this.rpmRepositoryName.hashCode()));
        result = ((result* 31)+((this.serviceShortName == null)? 0 :this.serviceShortName.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Rpm) == false) {
            return false;
        }
        Rpm rhs = ((Rpm) other);
        return ((((((this.hashes == rhs.hashes)||((this.hashes!= null)&&this.hashes.equals(rhs.hashes)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.rpmRepositoryName == rhs.rpmRepositoryName)||((this.rpmRepositoryName!= null)&&this.rpmRepositoryName.equals(rhs.rpmRepositoryName))))&&((this.serviceShortName == rhs.serviceShortName)||((this.serviceShortName!= null)&&this.serviceShortName.equals(rhs.serviceShortName))));
    }

}
