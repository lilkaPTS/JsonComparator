package com.company.controller;

import com.company.pojo.JsonStructure;
import com.company.service.FileService;
import com.company.service.JsonService;
import com.company.service.SchemaService;
import com.company.service.ValidationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class JsonController {
    @Autowired
    private SchemaService schemaService;
    @Autowired
    private FileService fileService;
    @Autowired
    private JsonService jsonService;
    @Autowired
    private ValidationService validationService;

    @GetMapping("/")
    public String getStartPage() {
        schemaService.getSchemaErrors(schemaService.getJsonSchema(JsonStructure.class), jsonService.getJsonNode(fileService.getFileContent("./src/main/resources/v2_json_sample_2.json"))).forEach(System.out::println);
        fileService.setFileWithContent("JsonSchema.json", schemaService.getJsonSchema(JsonStructure.class).toString());
        ObjectMapper mapper = new ObjectMapper();
        //JsonStructure jsonStructure = mapper.readValue(fileService.getFileContent("./src/main/resources/v2_json_sample.json"), JsonStructure.class);
        return "start";
    }
    @PostMapping("/")
    public String getResponsePage(@RequestParam(name = "file1") MultipartFile multipartFile) {
        validationService.getErrors(multipartFile);
        return "responsePage";
    }
}
