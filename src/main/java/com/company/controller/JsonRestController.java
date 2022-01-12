package com.company.controller;

import com.company.model.J1;
import com.company.service.ConvertJsonToJavaClassService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.codemodel.JCodeModel;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
public class JsonRestController {

    @Autowired
    private ConvertJsonToJavaClassService convertService;
    /*@PostMapping("/")
    public Map<String,Object> uploadFiles(@RequestParam("files") MultipartFile[] files) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> first = mapper.readValue(files[0].getBytes(), new TypeReference<HashMap<String, Object>>() {});
        Map<String,Object> second = mapper.readValue(files[1].getBytes(), new TypeReference<HashMap<String, Object>>() {});
        JsonObject jsonObject = mapper.readValue(files[0].getBytes(), JsonObject.class);
        System.out.println(jsonObject);
        return first;
    }*/

    @PostMapping("/")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files) throws IOException {
        convertService.execute(files);
        ObjectMapper mapper = new ObjectMapper();
        J1 j1 = mapper.readValue(new String(files[0].getBytes()), J1.class);
        return j1.toString();
    }
}
