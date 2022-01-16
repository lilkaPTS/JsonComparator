package com.company.POJO;

public class Mvn{
    public String groupId;
    public String artifactId;
    public String version;
    public String service_name;
    public String classifier;
    public String mvn_type;
    public String mvn_repository;
    public Hashes hashes;

    public Mvn() {
    }

    public Mvn(String groupId, String artifactId, String version, String service_name, String classifier, String mvn_type, String mvn_repository, Hashes hashes) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.service_name = service_name;
        this.classifier = classifier;
        this.mvn_type = mvn_type;
        this.mvn_repository = mvn_repository;
        this.hashes = hashes;
    }
}
