
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
    "some-third-param-5",
    "some-third-param-2"
})
@Generated("jsonschema2pojo")
public class ServiceName2 {

    @JsonProperty("some-third-param-5")
    private String someThirdParam5;
    @JsonProperty("some-third-param-2")
    private String someThirdParam2;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("some-third-param-5")
    public String getSomeThirdParam5() {
        return someThirdParam5;
    }

    @JsonProperty("some-third-param-5")
    public void setSomeThirdParam5(String someThirdParam5) {
        this.someThirdParam5 = someThirdParam5;
    }

    public ServiceName2 withSomeThirdParam5(String someThirdParam5) {
        this.someThirdParam5 = someThirdParam5;
        return this;
    }

    @JsonProperty("some-third-param-2")
    public String getSomeThirdParam2() {
        return someThirdParam2;
    }

    @JsonProperty("some-third-param-2")
    public void setSomeThirdParam2(String someThirdParam2) {
        this.someThirdParam2 = someThirdParam2;
    }

    public ServiceName2 withSomeThirdParam2(String someThirdParam2) {
        this.someThirdParam2 = someThirdParam2;
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

    public ServiceName2 withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ServiceName2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("someThirdParam5");
        sb.append('=');
        sb.append(((this.someThirdParam5 == null)?"<null>":this.someThirdParam5));
        sb.append(',');
        sb.append("someThirdParam2");
        sb.append('=');
        sb.append(((this.someThirdParam2 == null)?"<null>":this.someThirdParam2));
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
        result = ((result* 31)+((this.someThirdParam5 == null)? 0 :this.someThirdParam5 .hashCode()));
        result = ((result* 31)+((this.someThirdParam2 == null)? 0 :this.someThirdParam2 .hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ServiceName2) == false) {
            return false;
        }
        ServiceName2 rhs = ((ServiceName2) other);
        return ((((this.someThirdParam5 == rhs.someThirdParam5)||((this.someThirdParam5 != null)&&this.someThirdParam5 .equals(rhs.someThirdParam5)))&&((this.someThirdParam2 == rhs.someThirdParam2)||((this.someThirdParam2 != null)&&this.someThirdParam2 .equals(rhs.someThirdParam2))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
