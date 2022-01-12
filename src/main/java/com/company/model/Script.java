
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
    "start-point",
    "end-point",
    "script_name",
    "hashes",
    "url"
})
@Generated("jsonschema2pojo")
public class Script {

    @JsonProperty("service-short-name")
    private String serviceShortName;
    @JsonProperty("start-point")
    private String startPoint;
    @JsonProperty("end-point")
    private String endPoint;
    @JsonProperty("script_name")
    private String scriptName;
    @JsonProperty("hashes")
    private Hashes__3 hashes;
    @JsonProperty("url")
    private String url;
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

    public Script withServiceShortName(String serviceShortName) {
        this.serviceShortName = serviceShortName;
        return this;
    }

    @JsonProperty("start-point")
    public String getStartPoint() {
        return startPoint;
    }

    @JsonProperty("start-point")
    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public Script withStartPoint(String startPoint) {
        this.startPoint = startPoint;
        return this;
    }

    @JsonProperty("end-point")
    public String getEndPoint() {
        return endPoint;
    }

    @JsonProperty("end-point")
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public Script withEndPoint(String endPoint) {
        this.endPoint = endPoint;
        return this;
    }

    @JsonProperty("script_name")
    public String getScriptName() {
        return scriptName;
    }

    @JsonProperty("script_name")
    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public Script withScriptName(String scriptName) {
        this.scriptName = scriptName;
        return this;
    }

    @JsonProperty("hashes")
    public Hashes__3 getHashes() {
        return hashes;
    }

    @JsonProperty("hashes")
    public void setHashes(Hashes__3 hashes) {
        this.hashes = hashes;
    }

    public Script withHashes(Hashes__3 hashes) {
        this.hashes = hashes;
        return this;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public Script withUrl(String url) {
        this.url = url;
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

    public Script withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Script.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("serviceShortName");
        sb.append('=');
        sb.append(((this.serviceShortName == null)?"<null>":this.serviceShortName));
        sb.append(',');
        sb.append("startPoint");
        sb.append('=');
        sb.append(((this.startPoint == null)?"<null>":this.startPoint));
        sb.append(',');
        sb.append("endPoint");
        sb.append('=');
        sb.append(((this.endPoint == null)?"<null>":this.endPoint));
        sb.append(',');
        sb.append("scriptName");
        sb.append('=');
        sb.append(((this.scriptName == null)?"<null>":this.scriptName));
        sb.append(',');
        sb.append("hashes");
        sb.append('=');
        sb.append(((this.hashes == null)?"<null>":this.hashes));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
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
        result = ((result* 31)+((this.endPoint == null)? 0 :this.endPoint.hashCode()));
        result = ((result* 31)+((this.serviceShortName == null)? 0 :this.serviceShortName.hashCode()));
        result = ((result* 31)+((this.startPoint == null)? 0 :this.startPoint.hashCode()));
        result = ((result* 31)+((this.hashes == null)? 0 :this.hashes.hashCode()));
        result = ((result* 31)+((this.scriptName == null)? 0 :this.scriptName.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Script) == false) {
            return false;
        }
        Script rhs = ((Script) other);
        return ((((((((this.endPoint == rhs.endPoint)||((this.endPoint!= null)&&this.endPoint.equals(rhs.endPoint)))&&((this.serviceShortName == rhs.serviceShortName)||((this.serviceShortName!= null)&&this.serviceShortName.equals(rhs.serviceShortName))))&&((this.startPoint == rhs.startPoint)||((this.startPoint!= null)&&this.startPoint.equals(rhs.startPoint))))&&((this.hashes == rhs.hashes)||((this.hashes!= null)&&this.hashes.equals(rhs.hashes))))&&((this.scriptName == rhs.scriptName)||((this.scriptName!= null)&&this.scriptName.equals(rhs.scriptName))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))));
    }

}
