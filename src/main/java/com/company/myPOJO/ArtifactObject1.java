package com.company.myPOJO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Objects;

public class ArtifactObject1 implements ArtifactObject{
    public ArrayList<Mvn> mvn;
    @JsonProperty(value = "target_repository")
    public String targetRepository;

    public ArtifactObject1() {
    }

    public ArtifactObject1(ArrayList<Mvn> mvn, String targetRepository) {
        this.mvn = mvn;
        this.targetRepository = targetRepository;
    }

    public ArrayList<Mvn> getMvn() {
        return mvn;
    }

    public void setMvn(ArrayList<Mvn> mvn) {
        this.mvn = mvn;
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
        ArtifactObject1 that = (ArtifactObject1) o;
        return Objects.equals(mvn, that.mvn) && Objects.equals(targetRepository, that.targetRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mvn, targetRepository);
    }

    @Override
    public String toString() {
        return "ArtifactObject1{" +
                "mvn=" + mvn +
                ", targetRepository='" + targetRepository + '\'' +
                '}';
    }
}
