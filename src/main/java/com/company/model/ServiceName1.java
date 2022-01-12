
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
    "some-third-param",
    "some-third-param-3"
})
@Generated("jsonschema2pojo")
public class ServiceName1 {

    @JsonProperty("some-third-param")
    private String someThirdParam;
    @JsonProperty("some-third-param-3")
    private String someThirdParam3;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("some-third-param")
    public String getSomeThirdParam() {
        return someThirdParam;
    }

    @JsonProperty("some-third-param")
    public void setSomeThirdParam(String someThirdParam) {
        this.someThirdParam = someThirdParam;
    }

    public ServiceName1 withSomeThirdParam(String someThirdParam) {
        this.someThirdParam = someThirdParam;
        return this;
    }

    @JsonProperty("some-third-param-3")
    public String getSomeThirdParam3() {
        return someThirdParam3;
    }

    @JsonProperty("some-third-param-3")
    public void setSomeThirdParam3(String someThirdParam3) {
        this.someThirdParam3 = someThirdParam3;
    }

    public ServiceName1 withSomeThirdParam3(String someThirdParam3) {
        this.someThirdParam3 = someThirdParam3;
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

    public ServiceName1 withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ServiceName1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("someThirdParam");
        sb.append('=');
        sb.append(((this.someThirdParam == null)?"<null>":this.someThirdParam));
        sb.append(',');
        sb.append("someThirdParam3");
        sb.append('=');
        sb.append(((this.someThirdParam3 == null)?"<null>":this.someThirdParam3));
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
        result = ((result* 31)+((this.someThirdParam == null)? 0 :this.someThirdParam.hashCode()));
        result = ((result* 31)+((this.someThirdParam3 == null)? 0 :this.someThirdParam3 .hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ServiceName1) == false) {
            return false;
        }
        ServiceName1 rhs = ((ServiceName1) other);
        return ((((this.someThirdParam == rhs.someThirdParam)||((this.someThirdParam!= null)&&this.someThirdParam.equals(rhs.someThirdParam)))&&((this.someThirdParam3 == rhs.someThirdParam3)||((this.someThirdParam3 != null)&&this.someThirdParam3 .equals(rhs.someThirdParam3))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
