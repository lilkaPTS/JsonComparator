package com.company.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.victools.jsonschema.generator.*;
import com.github.victools.jsonschema.module.jackson.JacksonModule;
import com.github.victools.jsonschema.module.jackson.JacksonOption;
import com.networknt.schema.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Set;

@Service
public class SchemaService {

    public void schemaGenerate(Class<?> clazz) throws IOException {
        JacksonModule jacksonModule = new JacksonModule(JacksonOption.RESPECT_JSONPROPERTY_REQUIRED);
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2019_09, OptionPreset.PLAIN_JSON)
                .with(jacksonModule);
        SchemaGeneratorConfig config = configBuilder.build();
        SchemaGenerator generator = new SchemaGenerator(config);
        JsonNode jsonSchema = generator.generateSchema(clazz);
        FileWriter writer = new FileWriter("1.json", false);
        writer.write(jsonSchema.toString());
        writer.flush();
        getSchemaErrors();
    }

    public void getSchemaErrors() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909);
        JsonSchema schema = factory.getSchema(new FileInputStream("1.json"));
        JsonNode node = mapper.readTree(new File("11.json"));
        Set<ValidationMessage> errors = schema.validate(node);
        errors.forEach(System.out::println);
    }
}
