package com.company.controller;

import com.company.model.JSONStructure;
import com.company.service.FileService;
import com.company.service.JSONService;
import com.company.service.SchemaService;
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
    @GetMapping("/")
    public String getStartPage() {
        schemaService.getSchemaErrors(schemaService.getJsonSchema(JSONStructure.class), jsonService.getJsonNode(fileService.getFileContent("22.json"))).forEach(System.out::println);
        return "start";
    }
    @PostMapping("/")
    public String getResponsePage(@RequestParam(name = "file1") MultipartFile multipartFile) {
        System.out.println(fileService.getFileContent(multipartFile));
        return "responsePage";
    }
}
