package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ValidationService {
    @Autowired
    private SchemaService schemaService;
    @Autowired
    private FileService fileService;
    @Autowired
    private JsonService jsonService;

    public String getErrors(MultipartFile inputFile) {
        //check ArtifactObject1-2 and Parameter.Services.ServiceName maps
        return "";
    }


}
