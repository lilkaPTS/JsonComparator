package com.company.model;

import com.company.pojo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Objects;

public class ConfigFile {

    private Metadata metadata;
    private ArrayList<Service> services;
    //ArtifactObject is marker interface because Jackson can not define ArtifactObject realisation without additional field in JSON
    private ArrayList<ArtifactObject> artifacts;
    private ArrayList<Script> script;
    private ArrayList<Rpm> rpm;
    private Parameter parameters;

    public ConfigFile(Metadata metadata, ArrayList<Service> services, ArrayList<ArtifactObject> artifacts, ArrayList<Script> script, ArrayList<Rpm> rpm, Parameter parameters) {
        this.metadata = metadata;
        this.services = services;
        this.artifacts = artifacts;
        this.script = script;
        this.rpm = rpm;
        this.parameters = parameters;
    }

    //POJO -> Model
    public ConfigFile(JsonStructure jsonStructure) {
        this.metadata = jsonStructure.getMetadata();
        this.services = jsonStructure.getServices();
        this.artifacts = ArtifactConverter(jsonStructure.getArtifacts());
        this.script = jsonStructure.getScript();
        this.rpm = jsonStructure.getRpm();
        this.parameters = jsonStructure.getParameters();
    }

    private ArrayList<ArtifactObject> ArtifactConverter(ArrayList<JsonNode> artifacts) {
        ArrayList<ArtifactObject> result = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            for(JsonNode artifact : artifacts) {
                if (artifact.has("mvn")) {
                    result.add(mapper.readValue(artifact.toString(), ArtifactObject1.class));
                } else {
                    result.add(mapper.readValue(artifact.toString(), ArtifactObject2.class));
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public ArrayList<ArtifactObject> getArtifacts() {
        return artifacts;
    }

    public ArrayList<Script> getScript() {
        return script;
    }

    public ArrayList<Rpm> getRpm() {
        return rpm;
    }

    public Parameter getParameters() {
        return parameters;
    }

    @Override
    public boolean equals(Object o) {
        //needs implementation
        return false;
    }

    @Override
    public int hashCode() {
        //rpm not required and can be empty...
        return Objects.hash(metadata, services, artifacts, script, rpm, parameters);
    }

    @Override
    public String toString() {
        return "ConfigFile{" +
                "metadata=" + metadata +
                ", services=" + services +
                ", artifacts=" + artifacts +
                ", script=" + script +
                ", rpm=" + rpm +
                ", parameters=" + parameters +
                '}';
    }
}
