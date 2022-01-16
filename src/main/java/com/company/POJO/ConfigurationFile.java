package com.company.POJO;

import com.company.POJO.*;

import java.util.ArrayList;

public class ConfigurationFile {
    //public Metadata metadata;
   public ArrayList<Service> services;
//public ArrayList<Artifact> artifacts;
//    public ArrayList<Script> scripts;
//    public Rpm rpm;
//    public Parameters parameters;


    public ConfigurationFile() {
    }

    public ConfigurationFile(ArrayList<Service> services) {
        this.services = services;
    }
}
