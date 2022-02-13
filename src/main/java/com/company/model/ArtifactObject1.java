package com.company.model;

import com.company.pojo.Mvn;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtifactObject1 implements ArtifactObject {
    @JsonProperty(value = "mvn", required = true)
    @NotEmpty
    private ArrayList<Mvn> mvn;
    @JsonProperty(value = "target_repository", required = true)
    private String targetRepository;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtifactObject1 that = (ArtifactObject1) o;
        return mvn.equals(that.mvn) && targetRepository.equals(that.targetRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mvn, targetRepository);
    }

    @Override
    public String toString() {
        StringBuilder mvnString = new StringBuilder();
        mvn.forEach(s -> mvnString.append(s).append("\n"));
        return "{\n" +
                "\"mvn\" : [\n" +
                mvnString +
                "],\n" +
                "\"target_repository\" : \"" + targetRepository + "\"" +
                "\n},";
    }


    @Override
    public int compareTo(ArtifactObject o) {
        int result = 0;
        if(o instanceof ArtifactObject1) {
            for (Mvn m: this.mvn) {
                result-=((ArtifactObject1) o).mvn.contains(m) ? 1 : 0;
            }
            result+=this.targetRepository.equals(((ArtifactObject1) o).targetRepository) ? 0 : 50;
        }
        return result;
    }
}
