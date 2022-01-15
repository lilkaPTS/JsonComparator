package com.company.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.victools.jsonschema.generator.*;
import com.github.victools.jsonschema.module.jackson.JacksonModule;
import com.github.victools.jsonschema.module.jackson.JacksonOption;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class SchemaService {

    public void schemaGenerate(Class<?> clazz) throws IOException {
        JacksonModule jacksonModule = new JacksonModule(JacksonOption.RESPECT_JSONPROPERTY_REQUIRED);
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_7, OptionPreset.PLAIN_JSON)
                .with(jacksonModule);
        SchemaGeneratorConfig config = configBuilder.build();
        SchemaGenerator generator = new SchemaGenerator(config);
        JsonNode jsonSchema = generator.generateSchema(clazz);
        FileWriter writer = new FileWriter("1.json", false);
        writer.write(jsonSchema.toString());
        writer.flush();
        givenInvalidInput_whenValidating_thenInvalid();
    }

    public void givenInvalidInput_whenValidating_thenInvalid() throws IOException {
        JSONObject jsonSchema = new JSONObject(new JSONTokener(new FileInputStream("1.json")));
        JSONObject jsonObject = new JSONObject(new JSONTokener(new FileInputStream("11.json")));
        Schema schemaValidator = SchemaLoader.load(jsonSchema);
        schemaValidator.validate(jsonObject);
    }
}
