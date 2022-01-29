package com.company.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metadata implements Comparable<Metadata> {
    @JsonProperty(value = "description", required = true)
    private Description description;
    @JsonProperty(value = "application", required = true)
    private Application application;

    @Override
    public int compareTo(Metadata o) {
        int result = 1;
        result*=this.description.equals(o.description) ? 1 : 2;
        result*=this.application.equals(o.application) ? 1 : 3;
        return result==1 ? 0 : result;
    }

    @Override
    public String toString() {
        return "\"metadata\" : {\n" +
                 description + ",\n" +
                 application +
                "\n}";
    }
}
