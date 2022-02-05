package com.company.model;

import com.company.pojo.Hashes;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
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

    public boolean isFileComparable(List<String> list1, List<String> list2) {
        for(String element: list1) {
            if(list2.contains(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtifactObject2 that = (ArtifactObject2) o;
        return hashes.equals(that.hashes) && file.equals(that.file) && targetRepository.equals(that.targetRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashes, file, targetRepository);
    }

    @Override
    public String toString() {
        StringBuilder fileString = new StringBuilder();
        file.forEach(s -> fileString.append(s).append("\n"));
        return "{\n" +
                "\"service-short-name\" : \"" + targetRepository + "\",\n" +
                "\"service_name\" : \"" + targetRepository + "\",\n" +
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
            result+=this.file.size() == ((ArtifactObject2) o).file.size()
                    && ((ArtifactObject2) o).file.containsAll(this.file) ? 0 : 50;
            result+=this.targetRepository.equals(((ArtifactObject2) o).targetRepository) ? 0 : 50;
        }
        return result;
    }
}
