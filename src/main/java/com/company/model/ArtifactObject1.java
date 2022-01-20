package com.company.model;

import com.company.pojo.Mvn;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtifactObject1 implements ArtifactObject {
    @JsonProperty(value = "mvn", required = true)
    @NotEmpty
    private ArrayList<Mvn> mvn;
    @JsonProperty(value = "target_repository", required = true)
    private String targetRepository;
}
