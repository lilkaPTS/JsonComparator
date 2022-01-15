package com.company.controller;

import com.company.model.JsonStructure;
import com.company.service.SchemaService;
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
        schemaService.schemaGenerate(JsonStructure.class);
        return "start";
    }
}
