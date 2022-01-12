package com.company.controller;

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
    @PostMapping("/")
    public List<String> uploadFiles(@RequestParam("files") MultipartFile[] files) throws IOException {
        ArrayList<String> jsons = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            jsons.add(new String(files[i].getBytes()));
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode1 = mapper.readTree(jsons.get(0));
        JsonNode jsonNode2 = mapper.readTree(jsons.get(1));
        convertJsonToJavaClass(jsonNode1.toString(), new File("C://Users//jajak//Desktop//Netcracker//Java//JsonParser//src//main//java//com//company"), "model", "fff");
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
