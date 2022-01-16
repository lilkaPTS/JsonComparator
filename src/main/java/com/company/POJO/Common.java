package com.company.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Common{
    @JsonProperty("some-param")
    public String someParam;
    @JsonProperty("some-other-param") 
    public String someOtherParam;
}
