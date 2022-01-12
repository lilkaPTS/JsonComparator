
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
    "some-param",
    "some-other-param",
    "some-else-param"
})
@Generated("jsonschema2pojo")
public class Common {

    @JsonProperty("some-param")
    private String someParam;
    @JsonProperty("some-other-param")
    private String someOtherParam;
    @JsonProperty("some-else-param")
    private String someElseParam;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("some-param")
    public String getSomeParam() {
        return someParam;
    }

    @JsonProperty("some-param")
    public void setSomeParam(String someParam) {
        this.someParam = someParam;
    }

    public Common withSomeParam(String someParam) {
        this.someParam = someParam;
        return this;
    }

    @JsonProperty("some-other-param")
    public String getSomeOtherParam() {
        return someOtherParam;
    }

    @JsonProperty("some-other-param")
    public void setSomeOtherParam(String someOtherParam) {
        this.someOtherParam = someOtherParam;
    }

    public Common withSomeOtherParam(String someOtherParam) {
        this.someOtherParam = someOtherParam;
        return this;
    }

    @JsonProperty("some-else-param")
    public String getSomeElseParam() {
        return someElseParam;
    }

    @JsonProperty("some-else-param")
    public void setSomeElseParam(String someElseParam) {
        this.someElseParam = someElseParam;
    }

    public Common withSomeElseParam(String someElseParam) {
        this.someElseParam = someElseParam;
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

    public Common withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Common.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("someParam");
        sb.append('=');
        sb.append(((this.someParam == null)?"<null>":this.someParam));
        sb.append(',');
        sb.append("someOtherParam");
        sb.append('=');
        sb.append(((this.someOtherParam == null)?"<null>":this.someOtherParam));
        sb.append(',');
        sb.append("someElseParam");
        sb.append('=');
        sb.append(((this.someElseParam == null)?"<null>":this.someElseParam));
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
        result = ((result* 31)+((this.someElseParam == null)? 0 :this.someElseParam.hashCode()));
        result = ((result* 31)+((this.someOtherParam == null)? 0 :this.someOtherParam.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.someParam == null)? 0 :this.someParam.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Common) == false) {
            return false;
        }
        Common rhs = ((Common) other);
        return (((((this.someElseParam == rhs.someElseParam)||((this.someElseParam!= null)&&this.someElseParam.equals(rhs.someElseParam)))&&((this.someOtherParam == rhs.someOtherParam)||((this.someOtherParam!= null)&&this.someOtherParam.equals(rhs.someOtherParam))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.someParam == rhs.someParam)||((this.someParam!= null)&&this.someParam.equals(rhs.someParam))));
    }

}
