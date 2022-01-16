package com.company.myPOJO;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Objects;

public class ArtifactObject2 implements ArtifactObject{
    @JsonProperty(value = "service-short-name")
    public String serviceShortName;
    @JsonProperty(value = "service_name")
    public String serviceName;
    @JsonProperty(value = "hashes", required = true)
    public Hashes hashes;
    @JsonProperty(value = "file", required = true)
    //@NotEmpty
    public ArrayList<String> file;
    @JsonProperty(value = "target_repository", required = true)
    public String targetRepository;

    public ArtifactObject2() {
    }

    public ArtifactObject2(String serviceShortName, String serviceName, Hashes hashes, ArrayList<String> file, String targetRepository) {
        this.serviceShortName = serviceShortName;
        this.serviceName = serviceName;
        this.hashes = hashes;
        this.file = file;
        this.targetRepository = targetRepository;
    }

    public String getServiceShortName() {
        return serviceShortName;
    }

    public void setServiceShortName(String serviceShortName) {
        this.serviceShortName = serviceShortName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Hashes getHashes() {
        return hashes;
    }

    public void setHashes(Hashes hashes) {
        this.hashes = hashes;
    }

    public ArrayList<String> getFile() {
        return file;
    }

    public void setFile(ArrayList<String> file) {
        this.file = file;
    }

    public String getTargetRepository() {
        return targetRepository;
    }

    public void setTargetRepository(String targetRepository) {
        this.targetRepository = targetRepository;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtifactObject2 that = (ArtifactObject2) o;
        return Objects.equals(serviceShortName, that.serviceShortName) && Objects.equals(serviceName, that.serviceName) && Objects.equals(hashes, that.hashes) && Objects.equals(file, that.file) && Objects.equals(targetRepository, that.targetRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceShortName, serviceName, hashes, file, targetRepository);
    }

    @Override
    public String toString() {
        return "ArtifactObject2{" +
                "serviceShortName='" + serviceShortName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", hashes=" + hashes +
                ", file=" + file +
                ", targetRepository='" + targetRepository + '\'' +
                '}';
    }
}
