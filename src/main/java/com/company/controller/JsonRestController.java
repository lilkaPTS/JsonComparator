package com.company.controller;

import com.company.model.J1;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.codemodel.JCodeModel;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;
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
    public List<String> uploadFiles(@RequestParam("files") MultipartFile[] files) throws IOException {
        ArrayList<String> jsons = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            jsons.add(new String(files[i].getBytes()));
        }
        ObjectMapper mapper = new ObjectMapper();
        convertJsonToJavaClass(jsons.get(0), new File("./src//main//java"), "com.company.model", "J1");
        convertJsonToJavaClass(jsons.get(1), new File("./src//main//java"), "com.company.model", "J2");
        J1 j1 = mapper.readValue(jsons.get(0), J1.class);
        System.out.println(j1);
        return jsons;
    }

    public void convertJsonToJavaClass(String inputJsonUrl, File outputJavaClassDirectory, String packageName, String javaClassName)
            throws IOException {
        JCodeModel jcodeModel = new JCodeModel();

        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public boolean isGenerateBuilders() {
                return true;
            }

            @Override
            public SourceType getSourceType() {
                return SourceType.JSON;
            }
        };

        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
        mapper.generate(jcodeModel, javaClassName, packageName, inputJsonUrl);

        jcodeModel.build(outputJavaClassDirectory);
    }
}
