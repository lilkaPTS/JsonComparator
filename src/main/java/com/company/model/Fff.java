
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
    "metadata",
    "services",
    "artifacts",
    "script",
    "rpm",
    "parameters"
})
@Generated("jsonschema2pojo")
public class Fff {

    @JsonProperty("metadata")
    private Metadata metadata;
    @JsonProperty("services")
    private List<Service> services = new ArrayList<Service>();
    @JsonProperty("artifacts")
    private List<Artifact> artifacts = new ArrayList<Artifact>();
    @JsonProperty("script")
    private List<Script> script = new ArrayList<Script>();
    @JsonProperty("rpm")
    private List<Rpm> rpm = new ArrayList<Rpm>();
    @JsonProperty("parameters")
    private Parameters parameters;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("metadata")
    public Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Fff withMetadata(Metadata metadata) {
        this.metadata = metadata;
        return this;
    }

    @JsonProperty("services")
    public List<Service> getServices() {
        return services;
    }

    @JsonProperty("services")
    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Fff withServices(List<Service> services) {
        this.services = services;
        return this;
    }

    @JsonProperty("artifacts")
    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    @JsonProperty("artifacts")
    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public Fff withArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
        return this;
    }

    @JsonProperty("script")
    public List<Script> getScript() {
        return script;
    }

    @JsonProperty("script")
    public void setScript(List<Script> script) {
        this.script = script;
    }

    public Fff withScript(List<Script> script) {
        this.script = script;
        return this;
    }

    @JsonProperty("rpm")
    public List<Rpm> getRpm() {
        return rpm;
    }

    @JsonProperty("rpm")
    public void setRpm(List<Rpm> rpm) {
        this.rpm = rpm;
    }

    public Fff withRpm(List<Rpm> rpm) {
        this.rpm = rpm;
        return this;
    }

    @JsonProperty("parameters")
    public Parameters getParameters() {
        return parameters;
    }

    @JsonProperty("parameters")
    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public Fff withParameters(Parameters parameters) {
        this.parameters = parameters;
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

    public Fff withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Fff.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("metadata");
        sb.append('=');
        sb.append(((this.metadata == null)?"<null>":this.metadata));
        sb.append(',');
        sb.append("services");
        sb.append('=');
        sb.append(((this.services == null)?"<null>":this.services));
        sb.append(',');
        sb.append("artifacts");
        sb.append('=');
        sb.append(((this.artifacts == null)?"<null>":this.artifacts));
        sb.append(',');
        sb.append("script");
        sb.append('=');
        sb.append(((this.script == null)?"<null>":this.script));
        sb.append(',');
        sb.append("rpm");
        sb.append('=');
        sb.append(((this.rpm == null)?"<null>":this.rpm));
        sb.append(',');
        sb.append("parameters");
        sb.append('=');
        sb.append(((this.parameters == null)?"<null>":this.parameters));
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
        result = ((result* 31)+((this.metadata == null)? 0 :this.metadata.hashCode()));
        result = ((result* 31)+((this.services == null)? 0 :this.services.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.parameters == null)? 0 :this.parameters.hashCode()));
        result = ((result* 31)+((this.script == null)? 0 :this.script.hashCode()));
        result = ((result* 31)+((this.rpm == null)? 0 :this.rpm.hashCode()));
        result = ((result* 31)+((this.artifacts == null)? 0 :this.artifacts.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Fff) == false) {
            return false;
        }
        Fff rhs = ((Fff) other);
        return ((((((((this.metadata == rhs.metadata)||((this.metadata!= null)&&this.metadata.equals(rhs.metadata)))&&((this.services == rhs.services)||((this.services!= null)&&this.services.equals(rhs.services))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.parameters == rhs.parameters)||((this.parameters!= null)&&this.parameters.equals(rhs.parameters))))&&((this.script == rhs.script)||((this.script!= null)&&this.script.equals(rhs.script))))&&((this.rpm == rhs.rpm)||((this.rpm!= null)&&this.rpm.equals(rhs.rpm))))&&((this.artifacts == rhs.artifacts)||((this.artifacts!= null)&&this.artifacts.equals(rhs.artifacts))));
    }

}
