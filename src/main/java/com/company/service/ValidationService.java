package com.company.service;

import com.company.model.ArtifactObject1;
import com.company.model.ArtifactObject2;
import com.company.pojo.Artifacts;

import com.company.pojo.Parameter;
import com.company.pojo.Services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;


@Service
public class ValidationService {
    @Autowired
    private SchemaService schemaService;
    @Autowired
    private FileService fileService;
    @Autowired
    private JsonService jsonService;

    private static final ObjectMapper MAPPER = new ObjectMapper();


    /**
     * At first, let's take errors by JsonSchema.json, then checked
     * founded errors to contains "null found" or "artifact is missing",
     * if this errors not found, we will set ArrayList of JsonNode based
     * Artifacts.class which is helper POJO to parse ArtifactObject1-2,
     * then we will check each JsonNode to errors and added errors in result.
     * @param inputFile file from upload form
     * @return errors in input file schema
     */
    public String getErrors(MultipartFile inputFile) throws JsonProcessingException {
        StringBuilder result = new StringBuilder();
        String inputFileContent = fileService.getFileContent(inputFile);
        JsonNode inputFileJsonNode = jsonService.getJsonNode(inputFileContent);
        schemaService.getSchemaErrors(jsonService.getJsonNode(fileService.getFileContent("JsonSchema.json")),inputFileJsonNode).forEach(err -> result.append(err).append("\n"));
        if(!(result.toString().matches("(?s).*null found, object expected(?s).*") ||
                result.toString().matches("(?s).*artifacts: is missing but it is required(?s).*"))) {  //check ArtifactObject1-2
            ArrayList<JsonNode> artifacts = MAPPER.readValue(inputFileContent, Artifacts.class).getArtifacts();
            for (JsonNode artifact : artifacts) {
                if (new ArrayList<>(schemaService.getSchemaErrors(schemaService.getJsonSchema(ArtifactObject1.class), artifact))
                        .get(0).toString()
                        .equals("$.mvn: is missing but it is required")
                ) {
                    schemaService.getSchemaErrors(schemaService.getJsonSchema(ArtifactObject2.class), artifact)
                            .forEach(err ->
                                    result.append("$.services.")
                                            .append(artifacts.indexOf(artifact))
                                            .append(".")
                                            .append(err.toString().substring(2))
                                            .append("\n")
                            );
                } else {
                    schemaService.getSchemaErrors(schemaService.getJsonSchema(ArtifactObject1.class), artifact)
                            .forEach(err ->
                                    result.append("$.services.")
                                            .append(artifacts.indexOf(artifact))
                                            .append(".")
                                            .append(err.toString().substring(2))
                                            .append("\n")
                            );
                }
            }
        }
        return result.toString();
    }
}
