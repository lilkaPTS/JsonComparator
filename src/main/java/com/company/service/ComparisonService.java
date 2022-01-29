package com.company.service;

import com.company.model.ConfigFile;
import com.company.model.ResponseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ComparisonService {

    private static final String KEY_EXPRESSION = "---color:";
    private static final String DEFAULT = "NoN";
    private static final String ERROR = "red";
    private static final String WARNING = "yellow";
    private static final String NOTIFICATION = "blue";

    @Autowired
    private JsonService jsonService;

    public ResponseView execute(ConfigFile config1, ConfigFile config2) {
        ResponseView result = new ResponseView();
        List<List<String>> metadata = getMetadata(config1, config2);
        result.add("{");
        metadata.get(0).forEach(result::add1);
        metadata.get(1).forEach(result::add2);
        result.add("}");
        return result;
    }

    //если есть несовпадение, выдаёт не совпавшее поле из второго файла
    public List<List<String>> getMetadata(ConfigFile config1, ConfigFile config2) {
        List<List<String>> result = new ArrayList<>();
        List<String> metadata1 = new ArrayList<>(setColorNonEveryWhere(Arrays.asList(config1.getMetadata().toString().split("\n"))));
        List<String> metadata2 = new ArrayList<>(setColorNonEveryWhere(Arrays.asList(config2.getMetadata().toString().split("\n"))));
        List<Integer> inconsistenciesList = getMultipliersV2(config1.getMetadata().compareTo(config2.getMetadata()));
        if(inconsistenciesList.contains(2)) {
            setColor(metadata2, "version", ERROR);
        }
        if(inconsistenciesList.contains(3)) {
            setColor(metadata2, "name", ERROR);
        }
        result.add(metadata1);
        result.add(metadata2);
        return result;
    }

    public void setColor(List<String> list, String element, String color) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).matches("^\"" + element + "\" : .+$")) {
                list.set(i, list.get(i).replaceAll(KEY_EXPRESSION + DEFAULT, KEY_EXPRESSION + color));
            }
        }
    }

    public List<String> setColorNonEveryWhere(List<String> input) {
        for (int i = 0; i < input.size(); i++)
            input.set(i, input.get(i) + KEY_EXPRESSION + DEFAULT);
        return input;
    }

    public List<String> getInconsistenciesOfServices(ConfigFile config1, ConfigFile config2){
        List<String> result = new ArrayList<>();
        List<com.company.pojo.Service> s1 = config1.getServices();
        List<com.company.pojo.Service> s2 = config2.getServices();


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
