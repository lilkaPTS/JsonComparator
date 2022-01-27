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

    public List<String> getInconsistenciesOfServices(ConfigFile config1, ConfigFile config2){
        List<String> result = new ArrayList<>();
        List<String> additional1 = new ArrayList<>();
        List<String> additional2 = new ArrayList<>();

        return result;
    }

    public List<String> getInconsistenciesOfOptional(ConfigFile config1, ConfigFile config2) {
        List<String> result = new ArrayList<>();

        return result;
    }

    public String jsonStringSetter(String key, String value) {
        return "\"" + key + "\":" + "\"" + value + "\"";
    }

    public String jsonStringSetter(String key, int value) {
        return "\"" + key + "\":" + value;
    }

    public static List<Integer> getMultipliersV2(int inputNumber) {
        List<Integer> result = new ArrayList<>();
        for (int i = 2; inputNumber > 1; i++) {
            if(inputNumber%i==0){
                inputNumber/=i;
                result.add(i);
            }
        }
        return result;
    }

    public static List<Integer> getSimpleNumbers(int limit) {
        List<Integer> result = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            result.add(i);
        }
        for (int i = 2; i*i < limit; i++) {
            if(result.contains(i))
                for (int j = i*i; j <= limit; j+=i) {
                    result.remove(new Integer(j));
                }
        }
        return result;
    }

    public static List<Integer> getMultipliers(int inputNumber) {
        List<Integer> result = new ArrayList<>();
        List<Integer> simpleNumbers = getSimpleNumbers(100);
        for (int simpleNumber : simpleNumbers) {
            if(inputNumber%simpleNumber==0) {
                inputNumber /= simpleNumber;
                result.add(simpleNumber);
            }
            if(inputNumber==0) {
                break;
            }
        }
        return result;
    }
}
