package com.company.model;

import java.util.List;

public class ResponseObject {

    private List<String> errors;
    private String configFile1;
    private String configFile2;
    private List<String> metadata;

    public ResponseObject() {}

    public ResponseObject(List<String> errors, String configFile1, String configFile2, List<String> metadata) {
        this.errors = errors;
        this.configFile1 = configFile1;
        this.configFile2 = configFile2;
        this.metadata = metadata;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getConfigFile1() {
        return configFile1;
    }

    public void setConfigFile1(String configFile1) {
        this.configFile1 = configFile1;
    }

    public String getConfigFile2() {
        return configFile2;
    }

    public void setConfigFile2(String configFile2) {
        this.configFile2 = configFile2;
    }

    public List<String> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<String> metadata) {
        this.metadata = metadata;
    }
}
