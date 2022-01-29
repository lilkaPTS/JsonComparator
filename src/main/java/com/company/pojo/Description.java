package com.company.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Description {
    @JsonProperty(value = "version", required = true)
    private int version;

    @Override
    public String toString() {
        return "\"description\" : {\n" +
                "\"version\" : " + version + "\n" +
                "}";
    }
}
