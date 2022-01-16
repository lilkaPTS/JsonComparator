package com.company.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.imifou.jsonschema.module.addon.AddonModule;
import com.github.victools.jsonschema.generator.*;
import com.github.victools.jsonschema.module.jackson.JacksonModule;
import com.github.victools.jsonschema.module.jackson.JacksonOption;
import com.github.victools.jsonschema.module.javax.validation.JavaxValidationModule;
import com.networknt.schema.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Set;

@Service
public class SchemaService {

    public void schemaGenerate(Class<?> clazz) throws IOException {
        JacksonModule jacksonModule = new JacksonModule(JacksonOption.RESPECT_JSONPROPERTY_REQUIRED);
        JavaxValidationModule javaxValidationModule = new JavaxValidationModule();
        AddonModule addonModule= new AddonModule();
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2019_09, OptionPreset.PLAIN_JSON)
                .with(jacksonModule).with(javaxValidationModule).with(addonModule);
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
        JsonSchema schema = factory.getSchema(new FileInputStream("2.json"));
        JsonNode node = mapper.readTree(new File("22.json"));
        Set<ValidationMessage> errors = schema.validate(node);
        errors.forEach(System.out::println);
        System.out.println("\n");
    }
}
