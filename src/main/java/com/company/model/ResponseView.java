package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ResponseView {
    private List<String> configFile1 = new ArrayList<>();
    private List<String> configFile2 = new ArrayList<>();
    public void add(String str) {
        this.add1(str);
        this.add2(str);
    }
    public void add1(String str) {
        configFile1.add(str);
    }
    public void add2(String str) {
        configFile2.add(str);
    }
    public void add1(String[] strings) {
        for (String s: strings) {
            add1(s);
        }
    }
    public void add2(String[] strings) {
        for (String s: strings) {
            add2(s);
        }
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("ResponseView{\n");
        result.append("\tconfigFile1{\n");
        configFile1.forEach(s->result.append("\t\t").append(s).append("\n"));
        result.append("\t}\n");
        result.append("\tconfigFile2{\n");
        configFile2.forEach(s->result.append("\t\t").append(s).append("\n"));
        result.append("\t}\n");
        result.append("}");
        return result.toString();
    }
}
