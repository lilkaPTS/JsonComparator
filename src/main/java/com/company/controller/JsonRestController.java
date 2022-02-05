package com.company.controller;

import com.company.model.ConfigFile;
import com.company.model.ResponseObject;
import com.company.model.ResponseView;
import com.company.pojo.JsonStructure;
import com.company.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JsonRestController {

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

    @PostMapping("/test")
    public ResponseObject testPost(@RequestParam(name = "file1") MultipartFile multipartFile1, @RequestParam(name = "file2") MultipartFile multipartFile2)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ResponseObject result = new ResponseObject();
        List<String> errors = new ArrayList<>();
        errors.addAll(validationService.getErrors(multipartFile1));
        errors.addAll(validationService.getErrors(multipartFile2));
        result.setErrors(errors);
        errors.forEach(System.out::println);
        if(errors.isEmpty()) {
            ConfigFile configFile1 = new ConfigFile(mapper.readValue(fileService.getFileContent(multipartFile1), JsonStructure.class));
            ConfigFile configFile2 = new ConfigFile(mapper.readValue(fileService.getFileContent(multipartFile2), JsonStructure.class));
           // System.out.println(jsonService.getJsonPrettyString(configFile1).split("\"artifacts\" : \\[ \\{")[0].split("\n")[0].replace("\r", " ") + "213");
            result.setResponseView(comparisonService.execute(configFile1, configFile2));
        }
        return result;
    }
}
