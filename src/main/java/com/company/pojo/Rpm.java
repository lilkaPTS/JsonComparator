package com.company.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rpm implements Comparable<Rpm>{
    @JsonProperty(value = "url",required = true)
    public String url;
    @JsonProperty(value = "rpm_repository_name",required = true)
    public String rpmRepositoryName;
    @JsonProperty(value = "hashes",required = true)
    public Hashes hashes;
    @JsonProperty(value = "service-short-name")
    public String serviceShortName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rpm rpm = (Rpm) o;
        return url.equals(rpm.url) && rpmRepositoryName.equals(rpm.rpmRepositoryName) && hashes.equals(rpm.hashes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, rpmRepositoryName, hashes);
    }

    @Override
    public int compareTo(Rpm o) {
        int result = 0;
        result+=this.url.equals(o.url) ? 0 : 50;
        result+=this.rpmRepositoryName.equals(o.rpmRepositoryName) ? 0 : 50;
        result+=this.hashes.compareTo(o.hashes);
        if(this.serviceShortName!=null){
            result+=this.serviceShortName.equals(o.serviceShortName) ? 0 : 1;
        } else if(o.serviceShortName!=null) {
            ++result;
        }
        return result;
    }

    @Override
    public String toString() {
        return  "{\n" +
                "\"url\" : \"" + url + "\",\n" +
                "\"rpm_repository_name\" : \"" + rpmRepositoryName + "\",\n" +
                hashes +
                "\"service-short-name\" : \"" + serviceShortName + "\",\n" +
                "\n},";
    }
}
