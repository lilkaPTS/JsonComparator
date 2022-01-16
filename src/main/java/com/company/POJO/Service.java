package com.company.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Service{
    @JsonProperty("service-short-name")
    public String serviceShortName;
    public String service_name;
    public String artifact_type;
    public String docker_registry;
    public String docker_image_name;
    public String docker_tag;
    public boolean force;
    public String github_repository;
    public String github_branch;
    public String github_hash;
    public Hashes hashes;
}
