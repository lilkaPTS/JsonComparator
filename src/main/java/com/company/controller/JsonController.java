package com.company.controller;

import com.company.model.ArtifactObject1;
import com.company.model.ArtifactObject2;
import com.company.pojo.Artifacts;
import com.company.pojo.JsonStructure;
import com.company.service.FileService;
import com.company.service.JsonService;
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
        return "start";
    }

    @PostMapping("/")
    public String getResponsePage(@RequestParam(name = "file1") MultipartFile multipartFile) throws JsonProcessingException {
        System.out.println(validationService.getErrors(multipartFile));
        return "responsePage";
    }
}
