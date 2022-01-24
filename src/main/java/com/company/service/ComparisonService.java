package com.company.service;

import com.company.model.ConfigFile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComparisonService {
    //если есть несовпадение, выдаёт не совпавшее поле из второго файла
    public List<String> getInconsistenciesOfMetadata(ConfigFile config1, ConfigFile config2) {
        List<String> result = new ArrayList<>();
        if(config1.getMetadata().getDescription().getVersion() !=
                config2.getMetadata().getDescription().getVersion()) {
            result.add(jsonStringSetter("version", config2.getMetadata().getDescription().getVersion()));
        }
        if(!config1.getMetadata().getApplication().getName()
                .equals(config2.getMetadata().getApplication().getName())) {
            result.add(jsonStringSetter("name", config2.getMetadata().getApplication().getName()));
        }
        return result;
    }

    public List<String> getInconsistenciesOfOptional(ConfigFile config1, ConfigFile config2) {
        List<String> result = new ArrayList<>();

        return result;
    }

    public String jsonStringSetter(String key, String value) {
        return "\"" + key + "\": " + "\"" + value + "\"";
    }

    public String jsonStringSetter(String key, int value) {
        return "\"" + key + "\": " + value;
    }
}
