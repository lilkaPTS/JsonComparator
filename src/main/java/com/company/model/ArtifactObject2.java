package com.company.model;

import com.company.pojo.Hashes;
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
public class ArtifactObject2 implements ArtifactObject {

    @JsonProperty(value = "service-short-name")
    private String serviceShortName;
    @JsonProperty(value = "service_name")
    private String serviceName;
    @JsonProperty(value = "hashes", required = true)
    private Hashes hashes;
    @JsonProperty(value = "file", required = true)
    @NotEmpty
    private ArrayList<String> file;
    @JsonProperty(value = "target_repository", required = true)
    private String targetRepository;

    public void addFile(String str) {
        this.file.add(str);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtifactObject2 that = (ArtifactObject2) o;
        return hashes.equals(that.hashes) && targetRepository.equals(that.targetRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashes, targetRepository);
    }

    @Override
    public String toString() {
        StringBuilder fileString = new StringBuilder();
        file.forEach(s -> fileString.append(s).append("\n"));
        return "{\n" +
                "\"service-short-name\" : \"" + serviceShortName + "\",\n" +
                "\"service_name\" : \"" + serviceName + "\",\n" +
                hashes +
                "\n\"file\" : [\n" +
                fileString +
                "],\n" +
                "\"target_repository\" : \"" + targetRepository + "\"" +
                "\n},";
    }

    @Override
    public int compareTo(ArtifactObject o) {
        int result = 0;
        if(o instanceof ArtifactObject2) {
            if(this.serviceShortName!=null){
                result+=this.serviceShortName.equals(((ArtifactObject2) o).serviceShortName) ? 0 : 1;
            } else if(((ArtifactObject2) o).serviceShortName!=null) {
                ++result;
            }
            if(this.serviceName!=null){
                result+=this.serviceName.equals(((ArtifactObject2) o).serviceName) ? 0 : 1;
            } else if(((ArtifactObject2) o).serviceName!=null) {
                ++result;
            }
            result+=this.hashes.compareTo(((ArtifactObject2) o).hashes);
            for (String str: this.file) {
                result-=((ArtifactObject2) o).file.contains(str) ? 1 : 0;
            }
            result+=this.targetRepository.equals(((ArtifactObject2) o).targetRepository) ? 0 : 50;
        }
        return result;
    }
}
