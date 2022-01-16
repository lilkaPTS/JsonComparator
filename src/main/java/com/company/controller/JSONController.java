package com.company.controller;



import com.company.model.JSONStructure;
import com.company.service.SchemaService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class JSONController {
    @Autowired
    private SchemaService schemaService;

    @GetMapping("/")
    public String getStartPage() throws IOException {
        schemaService.schemaGenerate(JSONStructure.class, "1.json");
        schemaService.getSchemaErrors("2.json", "22.json").forEach(System.out::println);

        ObjectMapper mapper = new ObjectMapper();
        //JSONStructure obj = mapper.readValue(j, JSONStructure.class);
        //System.out.println(obj.getArtifacts().get(0).toPrettyString());
        return "start";
    }
}
