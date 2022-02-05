package com.company.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Script implements Comparable<Script>{
    @JsonProperty(value = "service-short-name")
    public String serviceShortName;
    @JsonProperty(value = "start-point")
    public String startPoint;
    @JsonProperty(value = "end-point")
    public String endPoint;
    @JsonProperty(value = "script_name", required = true)
    public String scriptName;
    @JsonProperty(value = "hashes", required = true)
    public Hashes hashes;
    @JsonProperty(value = "url", required = true)
    public String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Script script = (Script) o;
        return scriptName.equals(script.scriptName) && hashes.equals(script.hashes) && url.equals(script.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scriptName, hashes, url);
    }


    @Override
    public int compareTo(Script o) {
        int result = 0;
        if(this.serviceShortName!=null){
            result+=this.serviceShortName.equals(o.serviceShortName) ? 0 : 1;
        } else if(o.serviceShortName!=null) {
            ++result;
        }
        if(this.startPoint!=null){
            result+=this.startPoint.equals(o.startPoint) ? 0 : 1;
        } else if(o.startPoint!=null) {
            ++result;
        }
        if(this.endPoint!=null){
            result+=this.endPoint.equals(o.endPoint) ? 0 : 1;
        } else if(o.endPoint!=null) {
            ++result;
        }
        result+=this.scriptName.equals(o.scriptName) ? 0 : 50;
        result+=this.hashes.compareTo(o.hashes);
        result+=this.url.equals(o.url) ? 0 : 50;
        return result;
    }

    @Override
    public String toString() {
        return  "{\n" +
                "\"service-short-name\" : \"" + serviceShortName + "\",\n" +
                "\"start-point\" : \"" + startPoint + "\",\n" +
                "\"end-point\" : \"" + endPoint + "\",\n" +
                "\"script_name\" : \"" + scriptName + "\",\n" +
                hashes +
                "\"url\" : \"" + url + "\",\n" +
                "\n},";
    }
}
