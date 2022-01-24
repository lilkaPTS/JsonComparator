package com.company.controller;

import com.company.model.ConfigFile;
import com.company.pojo.JsonStructure;
import com.company.service.*;
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
    @Autowired
    private ComparisonService comparisonService;

    @GetMapping("/")
    public String getStartPage() {
        return "start";
    }

    @PostMapping("/")
    public String getResponsePage(@RequestParam(name = "file1") MultipartFile multipartFile1, @RequestParam(name = "file2") MultipartFile multipartFile2)
            throws JsonProcessingException {
       /* ObjectMapper mapper = new ObjectMapper();
        String errors1 = validationService.getErrors(multipartFile1);
        String errors2 = validationService.getErrors(multipartFile2);
        System.out.println(errors1);
        System.out.println(errors2);
        if("".equals(errors1) && "".equals(errors2)) {
            ConfigFile configFile1 = new ConfigFile(mapper.readValue(fileService.getFileContent(multipartFile1), JsonStructure.class));
            ConfigFile configFile2 = new ConfigFile(mapper.readValue(fileService.getFileContent(multipartFile2), JsonStructure.class));
            System.out.println("Files created successfully");
            System.out.println(jsonService.getJsonNode(fileService.getFileContent(multipartFile1)).at("/artifacts/0/mvn/0/groupId"));


            comparisonService.getInconsistenciesOfMetadata(configFile1, configFile2).forEach(System.out::println);
        }
        return "responsePage";*/
        return "test";
    }

}
