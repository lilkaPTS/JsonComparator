package com.company.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Services {
    @JsonProperty(value = "service_name",required = true)
    private Map<String, String> serviceName;
    @JsonProperty(value = "service_name_1",required = true)
    private Map<String, String> serviceName1;
    @JsonProperty(value = "service_name_2",required = true)
    private Map<String, String> serviceName2;

}
