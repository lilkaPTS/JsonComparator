package com.company.service;

import com.company.model.ArtifactObject1;
import com.company.model.ArtifactObject2;
import com.company.pojo.Artifacts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Service
public class ValidationService {
    @Autowired
    private SchemaService schemaService;
    @Autowired
    private FileService fileService;
    @Autowired
    private JsonService jsonService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public List<String> getErrors(MultipartFile inputFile) {
        List<String> result = new ArrayList<>();
        String fileTypeError = checkFileType(inputFile);
        if(!"".equals(fileTypeError)) {
            result.add(fileTypeError);
            return result;
        }
        String inputFileContent = fileService.getFileContent(inputFile);
        JsonNode inputFileJsonNode = jsonService.getJsonNode(inputFileContent);
        JsonNode jsonMainSchema = jsonService.getJsonNode(fileService.getFileContent("JsonSchema.json"));
        schemaService.getSchemaErrors(jsonMainSchema, inputFileJsonNode).forEach(err -> result.add(err.toString()));
        if(!(result.contains("$: null found, object expected") ||
                result.contains("$.artifacts: is missing but it is required"))) {
            try {
                ArrayList<JsonNode> artifacts = MAPPER.readValue(inputFileContent, Artifacts.class).getArtifacts();
                for (JsonNode artifact : artifacts) {
                    if (!schemaService.getSchemaErrors(schemaService.getJsonSchema(ArtifactObject1.class), artifact).isEmpty()) { //save by get(0)
                        if (new ArrayList<>(schemaService.getSchemaErrors(schemaService.getJsonSchema(ArtifactObject1.class), artifact))
                                .get(0).toString()
                                .equals("$.mvn: is missing but it is required")
                        ) {
                            schemaService.getSchemaErrors(schemaService.getJsonSchema(ArtifactObject2.class), artifact)
                                    .forEach(err -> result.add("$.artifacts." + artifacts.indexOf(artifact) + "." + err.toString().substring(2)));
                        } else {
                            schemaService.getSchemaErrors(schemaService.getJsonSchema(ArtifactObject1.class), artifact)
                                    .forEach(err -> result.add("$.artifacts." + artifacts.indexOf(artifact) + "." + err.toString().substring(2)));
                        }
                    }
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if(result.contains("$: null found, object expected")) {
            for (int i = 0; i < result.size(); i++) {
                if ("$: null found, object expected".equals(result.get(i))) {
                    result.set(i, "$.Error type, file must be of type .json");
                }
            }
        }
        return result;
    }


    private String checkFileType(MultipartFile inputFile) {
        StringBuilder result = new StringBuilder();
        if("application/json".equals(inputFile.getContentType())){
            return "";
        } else {
            result.append("$.Error type, file must be of type .json");
        }
        return result.toString();
    }
}
