package com.company.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.imifou.jsonschema.module.addon.AddonModule;
import com.github.victools.jsonschema.generator.*;
import com.github.victools.jsonschema.module.jackson.JacksonModule;
import com.github.victools.jsonschema.module.jackson.JacksonOption;
import com.github.victools.jsonschema.module.javax.validation.JavaxValidationModule;
import com.networknt.schema.*;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SchemaService {

    public JsonNode getJsonSchema(Class<?> clazz) {
        JacksonModule jacksonModule = new JacksonModule(JacksonOption.RESPECT_JSONPROPERTY_REQUIRED);
        JavaxValidationModule javaxValidationModule = new JavaxValidationModule();
        AddonModule addonModule= new AddonModule(); //2022-01-17 - dont use
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2019_09, OptionPreset.PLAIN_JSON)
                .with(jacksonModule).with(javaxValidationModule).with(addonModule);
        SchemaGeneratorConfig config = configBuilder.build();
        SchemaGenerator generator = new SchemaGenerator(config);
        return generator.generateSchema(clazz);
    }

    public Set<ValidationMessage> getSchemaErrors(JsonNode schema, JsonNode node) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909);
        return factory.getSchema(schema).validate(node);
    }
}
