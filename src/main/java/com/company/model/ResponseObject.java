package com.company.model;

import com.company.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ResponseObject {

    @Autowired
    private JsonService jsonService;

    private List<String> errors;
    private String configFile1;
    private String configFile2;
    private List<String> metadata;

    public ResponseObject() {}

    public ResponseObject(JsonService jsonService, List<String> errors, ConfigFile configFile1, ConfigFile configFile2, List<String> metadata) {
        this.jsonService = jsonService;
        this.errors = errors;
        this.configFile1 = jsonService.getJsonPrettyString(configFile1);
        this.configFile2 = jsonService.getJsonPrettyString(configFile2);
        this.metadata = metadata;
    }

    public JsonService getJsonService() {
        return jsonService;
    }

    public void setJsonService(JsonService jsonService) {
        this.jsonService = jsonService;
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

    @Override
    public String toString() {
        return "ResponseObject{" +
                "jsonService=" + jsonService +
                ", errors=" + errors +
                ", configFile1='" + configFile1 + '\'' +
                ", configFile2='" + configFile2 + '\'' +
                ", metadata=" + metadata +
                '}';
    }
}
