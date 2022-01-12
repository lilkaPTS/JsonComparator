
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
    "service_name_1",
    "service_name_2",
    "service_name"
})
@Generated("jsonschema2pojo")
public class Services {

    @JsonProperty("service_name_1")
    private ServiceName1 serviceName1;
    @JsonProperty("service_name_2")
    private ServiceName2 serviceName2;
    @JsonProperty("service_name")
    private ServiceName serviceName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("service_name_1")
    public ServiceName1 getServiceName1() {
        return serviceName1;
    }

    @JsonProperty("service_name_1")
    public void setServiceName1(ServiceName1 serviceName1) {
        this.serviceName1 = serviceName1;
    }

    public Services withServiceName1(ServiceName1 serviceName1) {
        this.serviceName1 = serviceName1;
        return this;
    }

    @JsonProperty("service_name_2")
    public ServiceName2 getServiceName2() {
        return serviceName2;
    }

    @JsonProperty("service_name_2")
    public void setServiceName2(ServiceName2 serviceName2) {
        this.serviceName2 = serviceName2;
    }

    public Services withServiceName2(ServiceName2 serviceName2) {
        this.serviceName2 = serviceName2;
        return this;
    }

    @JsonProperty("service_name")
    public ServiceName getServiceName() {
        return serviceName;
    }

    @JsonProperty("service_name")
    public void setServiceName(ServiceName serviceName) {
        this.serviceName = serviceName;
    }

    public Services withServiceName(ServiceName serviceName) {
        this.serviceName = serviceName;
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

    public Services withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Services.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("serviceName1");
        sb.append('=');
        sb.append(((this.serviceName1 == null)?"<null>":this.serviceName1));
        sb.append(',');
        sb.append("serviceName2");
        sb.append('=');
        sb.append(((this.serviceName2 == null)?"<null>":this.serviceName2));
        sb.append(',');
        sb.append("serviceName");
        sb.append('=');
        sb.append(((this.serviceName == null)?"<null>":this.serviceName));
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.serviceName2 == null)? 0 :this.serviceName2 .hashCode()));
        result = ((result* 31)+((this.serviceName == null)? 0 :this.serviceName.hashCode()));
        result = ((result* 31)+((this.serviceName1 == null)? 0 :this.serviceName1 .hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Services) == false) {
            return false;
        }
        Services rhs = ((Services) other);
        return (((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.serviceName2 == rhs.serviceName2)||((this.serviceName2 != null)&&this.serviceName2 .equals(rhs.serviceName2))))&&((this.serviceName == rhs.serviceName)||((this.serviceName!= null)&&this.serviceName.equals(rhs.serviceName))))&&((this.serviceName1 == rhs.serviceName1)||((this.serviceName1 != null)&&this.serviceName1 .equals(rhs.serviceName1))));
    }

}
