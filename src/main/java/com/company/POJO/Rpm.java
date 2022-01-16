package com.company.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rpm{
    public String url;
    public String rpm_repository_name;
    public Hashes hashes;
    @JsonProperty("service-short-name")
    public String serviceShortName;
}
