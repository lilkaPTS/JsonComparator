package com.company.POJO;

import com.company.model.Hashes;
import com.company.model.Mvn;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Artifact{
    public ArrayList<Mvn> mvn;
    public String target_repository;
    @JsonProperty("service-short-name")
    public String serviceShortName;
    public String service_name;
    public Hashes hashes;
    public ArrayList<String> file;

    public Artifact() {
    }

    public Artifact(ArrayList<Mvn> mvn, String target_repository, String serviceShortName, String service_name, Hashes hashes, ArrayList<String> file) {
        this.mvn = mvn;
        this.target_repository = target_repository;
        this.serviceShortName = serviceShortName;
        this.service_name = service_name;
        this.hashes = hashes;
        this.file = file;
    }
}
