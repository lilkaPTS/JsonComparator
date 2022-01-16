package com.company.myPOJO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Mvn {
    @JsonProperty(value = "groupId", required = true)
    public String groupId;
    @JsonProperty(value = "artifactId", required = true)
    public String artifactId;
    @JsonProperty(value = "version", required = true)
    public String version;
    @JsonProperty(value = "service_name")
    public String serviceName;
    @JsonProperty(value = "classifier")
    public String classifier;
    @JsonProperty(value = "mvn_type", required = true)
    public String mvnType;
    @JsonProperty(value = "mvn_repository", required = true)
    public String mvnRepository;
    @JsonProperty(value = "hashes", required = true)
    public Hashes hashes;

    public Mvn() {
    }

    public Mvn(String groupId, String artifactId, String version, String serviceName, String classifier, String mvnType, String mvnRepository, Hashes hashes) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.serviceName = serviceName;
        this.classifier = classifier;
        this.mvnType = mvnType;
        this.mvnRepository = mvnRepository;
        this.hashes = hashes;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getClassifier() {
        return classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    public String getMvnType() {
        return mvnType;
    }

    public void setMvnType(String mvnType) {
        this.mvnType = mvnType;
    }

    public String getMvnRepository() {
        return mvnRepository;
    }

    public void setMvnRepository(String mvnRepository) {
        this.mvnRepository = mvnRepository;
    }

    public Hashes getHashes() {
        return hashes;
    }

    public void setHashes(Hashes hashes) {
        this.hashes = hashes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mvn mvn = (Mvn) o;
        return Objects.equals(groupId, mvn.groupId) && Objects.equals(artifactId, mvn.artifactId) && Objects.equals(version, mvn.version) && Objects.equals(serviceName, mvn.serviceName) && Objects.equals(classifier, mvn.classifier) && Objects.equals(mvnType, mvn.mvnType) && Objects.equals(mvnRepository, mvn.mvnRepository) && Objects.equals(hashes, mvn.hashes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, artifactId, version, serviceName, classifier, mvnType, mvnRepository, hashes);
    }

    @Override
    public String toString() {
        return "Mvn{" +
                "groupId='" + groupId + '\'' +
                ", artifactId='" + artifactId + '\'' +
                ", version='" + version + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", classifier='" + classifier + '\'' +
                ", mvnType='" + mvnType + '\'' +
                ", mvnRepository='" + mvnRepository + '\'' +
                ", hashes=" + hashes +
                '}';
    }
}
