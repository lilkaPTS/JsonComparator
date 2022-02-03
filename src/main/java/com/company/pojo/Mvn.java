package com.company.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mvn implements Comparable<Mvn>{
    @JsonProperty(value = "groupId", required = true)
    private String groupId;
    @JsonProperty(value = "artifactId", required = true)
    private String artifactId;
    @JsonProperty(value = "version", required = true)
    private String version;
    @JsonProperty(value = "service_name")
    private String serviceName;
    @JsonProperty(value = "classifier")
    private String classifier;
    @JsonProperty(value = "mvn_type", required = true)
    private String mvnType;
    @JsonProperty(value = "mvn_repository", required = true)
    private String mvnRepository;
    @JsonProperty(value = "hashes", required = true)
    private Hashes hashes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mvn mvn = (Mvn) o;
        return groupId.equals(mvn.groupId) && artifactId.equals(mvn.artifactId) && version.equals(mvn.version) && mvnType.equals(mvn.mvnType) && mvnRepository.equals(mvn.mvnRepository) && hashes.equals(mvn.hashes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, artifactId, version, mvnType, mvnRepository, hashes);
    }

    @Override
    public int compareTo(Mvn o) {
        int result = 0;
        result+=this.groupId.equals(o.groupId) ? 0 : 50;
        result+=this.artifactId.equals(o.artifactId) ? 0 : 50;
        result+=this.version.equals(o.version) ? 0 : 50;
        if(this.serviceName!=null){
            result+=this.serviceName.equals(o.serviceName) ? 0 : 1;
        } else if(o.serviceName!=null) {
            ++result;
        }
        if(this.classifier!=null){
            result+=this.classifier.equals(o.classifier) ? 0 : 1;
        } else if(o.classifier!=null) {
            ++result;
        }
        result+=this.mvnType.equals(o.mvnType) ? 0 : 50;
        result+=this.mvnRepository.equals(o.mvnRepository) ? 0 : 50;
        result+=this.hashes.equals(o.hashes) ? 0 : 50;
        return result;
    }

    @Override
    public String toString() {
        return  "{\n" +
                "\"groupId\" : \"" + groupId + "\",\n" +
                "\"artifactId\" : \"" + artifactId + "\",\n" +
                "\"version\" : \"" + version + "\",\n" +
                "\"service_name\" : \"" + serviceName + "\",\n" +
                "\"classifier\" : \"" + classifier + "\",\n" +
                "\"mvn_type\" : \"" + mvnType + "\",\n" +
                "\"mvn_repository\" : \"" + mvnRepository + "\",\n" +
                hashes +
                "\n},";
    }
}
