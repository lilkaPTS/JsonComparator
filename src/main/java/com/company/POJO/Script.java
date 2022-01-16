package com.company.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Script{
    @JsonProperty("service-short-name")
    public String serviceShortName;
    @JsonProperty("start-point") 
    public String startPoint;
    @JsonProperty("end-point") 
    public String endPoint;
    public String script_name;
    public Hashes hashes;
    public String url;
}
