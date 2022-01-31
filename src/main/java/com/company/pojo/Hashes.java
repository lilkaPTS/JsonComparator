package com.company.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hashes implements Comparable<Hashes> {
    @JsonProperty(value = "sha1",required = true)
    private String sha1;
    @JsonProperty(value = "sha256",required = true)
    private String sha256;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hashes hashes = (Hashes) o;
        return sha1.equals(hashes.sha1) && sha256.equals(hashes.sha256);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sha1, sha256);
    }

    @Override
    public int compareTo(Hashes o) {
        int result = 1;
        result*=this.sha1.equals(o.sha1) ? 1 : 2;
        result*=this.sha256.equals(o.sha256) ? 1 : 3;
        return result==1 ? 0 : result;
    }

    @Override
    public String toString() {
        return "\"hashes\" : {\n" +
                "\"sha1\" : \"" + sha1 + "\",\n" +
                "\"sha256\" : \"" + sha256 + "\"\n" +
                "}";
    }
}
