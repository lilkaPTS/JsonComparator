package com.company.controller;

import com.company.pojo.JSONStructure;
import com.company.service.FileService;
import com.company.service.JSONService;
import com.company.service.SchemaService;
import com.company.service.ValidationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class JSONController {
    @Autowired
    private SchemaService schemaService;
    @Autowired
    private FileService fileService;
    @Autowired
    private JSONService jsonService;
    @Autowired
    private ValidationService validationService;

    @GetMapping("/")
    public String getStartPage() throws JsonProcessingException {
        schemaService.getSchemaErrors(schemaService.getJsonSchema(JSONStructure.class), jsonService.getJsonNode(fileService.getFileContent("./src/main/resources/v2_json_sample.json"))).forEach(System.out::println);
        fileService.setFileWithContent("JSONSchema.json", schemaService.getJsonSchema(JSONStructure.class).toString());
        ObjectMapper mapper = new ObjectMapper();
        JSONStructure jsonStructure = mapper.readValue(fileService.getFileContent("./src/main/resources/v2_json_sample.json"), JSONStructure.class);
        System.out.println(jsonStructure.getParameters().getServices());
        String str = mapper.writeValueAsString(jsonStructure);
        System.out.println(str);
        return "start";
    }
    @PostMapping("/")
    public String getResponsePage(@RequestParam(name = "file1") MultipartFile multipartFile) {
        System.out.println(fileService.getFileContent(multipartFile));
        return "responsePage";
    }
}
