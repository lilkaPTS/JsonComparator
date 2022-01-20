package com.company.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Common {
    @JsonProperty(value = "some-param", required = true)
    private String someParam;
    @JsonProperty(value = "some-other-param")
    private String someOtherParam;
    @JsonProperty(value = "some-else-param")
    private String someElseParam;
}
