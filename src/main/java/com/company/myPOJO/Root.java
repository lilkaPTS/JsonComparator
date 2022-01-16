package com.company.myPOJO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.Objects;

//@JsonDeserialize(as = ArtifactObject.class)
public class Root {
    public ArrayList<ArtifactObject> artifacts;

    public Root() {
    }

    public Root(ArrayList<ArtifactObject> artifacts) {
        this.artifacts = artifacts;
    }

    public ArrayList<ArtifactObject> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(ArrayList<ArtifactObject> artifacts) {
        this.artifacts = artifacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Root root = (Root) o;
        return Objects.equals(artifacts, root.artifacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artifacts);
    }

    @Override
    public String toString() {
        return "Root{" +
                "artifacts=" + artifacts +
                '}';
    }
}
