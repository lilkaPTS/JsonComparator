package com.company.service;

import com.company.model.*;
import com.company.pojo.Hashes;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class ComparisonService {

    private static final String KEY_EXPRESSION = "---color:";
    private static final String DEFAULT = "NoN";
    private static final String ERROR = "red";
    private static final String WARNING = "yellow";
    private static final String NOTIFICATION = "blue";

    public ResponseView execute(ConfigFile config1, ConfigFile config2) {
        ResponseView result = new ResponseView();
//        List<List<String>> metadata = getMetadata(config1, config2);
//        List<List<String>> services = getArray(config1.getServices(), config2.getServices());
        List<List<String>> artifacts = getArtifacts(config1.getArtifacts(), config2.getArtifacts());
//        List<List<String>> script = getArray(config1.getScript(), config2.getScript());
//        List<List<String>> rpm = getArray(config1.getRpm(), config2.getRpm());
        //config1.getServices().forEach(System.out::println);
        //config1.getArtifacts().forEach(System.out::println);
        result.add("{");

//        metadata.get(0).forEach(result::add1);
//        metadata.get(1).forEach(result::add2);
//
//        result.add("\"services\": [");
//        services.get(0).forEach(result::add1);
//        services.get(1).forEach(result::add2);
//        result.add("],");

        result.add("\"artifacts\": [");
        artifacts.get(0).forEach(result::add1);
        artifacts.get(1).forEach(result::add2);
        result.add("],");

//        result.add("\"script\": [");
//        script.get(0).forEach(result::add1);
//        script.get(1).forEach(result::add2);
//        result.add("],");
//
//        result.add("\"rpm\": [");
//        rpm.get(0).forEach(result::add1);
//        rpm.get(1).forEach(result::add2);
//        result.add("],");

        result.add("}");
        return result;
    }

    private List<List<String>> getArtifacts(List<ArtifactObject> list1, List<ArtifactObject> list2) {
        List<List<String>> result = new ArrayList<>();
        List<ArtifactObject> artifactObject1List1 = new ArrayList<>();
        List<ArtifactObject> artifactObject2List1 = new ArrayList<>();
        List<ArtifactObject> artifactObject1List2 = new ArrayList<>();
        List<ArtifactObject> artifactObject2List2 = new ArrayList<>();
        list1.forEach(element -> {
            if("ArtifactObject1".equals(element.getClass().getSimpleName())) {
                artifactObject1List1.add(element);
            } else {
                artifactObject2List1.add(element);
            }
        });
        list2.forEach(element -> {
            if("ArtifactObject1".equals(element.getClass().getSimpleName())) {
                artifactObject1List2.add(element);
            } else {
                artifactObject2List2.add(element);
            }
        });
        List<List<String>> artifactObject1Result = getArray(artifactObject1List1, artifactObject1List2);
        List<List<String>> artifactObject2Result = getArray(artifactObject2List1, artifactObject2List2);
        List<String> result1 = artifactObject2Result.get(0);
        List<String> result2 = artifactObject2Result.get(1);
        result1.addAll(artifactObject1Result.get(0));
        result2.addAll(artifactObject1Result.get(1));
        result.add(result1);
        result.add(result2);
        return result;
    }

    private List<List<String>> getMetadata(ConfigFile config1, ConfigFile config2) {
        List<List<String>> result = new ArrayList<>();
        List<String> metadata1 = new ArrayList<>(setColorEveryWhere(Arrays.asList(config1.getMetadata().toString().split("\n")), DEFAULT));
        List<String> metadata2 = new ArrayList<>(setColorEveryWhere(Arrays.asList(config2.getMetadata().toString().split("\n")), DEFAULT));
        equalsAndSetColor(config1.getMetadata().getDescription(), config2.getMetadata().getDescription(), "version", metadata2, "version", ERROR);
        equalsAndSetColor(config1.getMetadata().getApplication(), config2.getMetadata().getApplication(), "name", metadata2, "name", ERROR);
        result.add(metadata1);
        result.add(metadata2);
        return result;
    }

    private <T extends Comparable<T>> List<List<String>> getArray(List<T> objectList1, List<T> objectList2) {
        List<List<String>> result = new ArrayList<>();
        List<T> listMinSize = objectList1.size() <= objectList2.size() ? objectList1 : objectList2;
        List<T> listMaxSize = listMinSize.equals(objectList1) ? objectList2 : objectList1;
        List<List<Integer>> gradesMin = getGrades(listMinSize, listMaxSize);
        List<List<Integer>> gradesMax = getGrades(listMaxSize, listMinSize);
        //gradesMin.forEach(System.out::println);
        //gradesMax.forEach(System.out::println);
        List<String> result1 = new ArrayList<>(); // MinSize
        List<String> result2 = new ArrayList<>(); // MaxSize
        //key-minSize, value-maxSize
        if(!listMaxSize.isEmpty()) {
            Map<Integer, Integer> comparedObject = new HashMap<>(elementMatcher(gradesMin, gradesMax, getWorstGrade(listMaxSize.get(0), false)));
            Map<Integer, Integer> additionalFromListMinSize = new HashMap<>();
            Map<Integer, Integer> additionalFromListMaxSize = new HashMap<>();
            List<Integer> skipFromListMinSize = new ArrayList<>(comparedObject.keySet());
            List<Integer> skipFromListMaxSize = new ArrayList<>(comparedObject.values());
            for (int i = 0; i < listMinSize.size(); i++) {
                if (!skipFromListMinSize.contains(i)) {
                    additionalFromListMinSize.put(i, -1);
                }
            }
            for (int i = 0; i < listMaxSize.size(); i++) {
                if (!skipFromListMaxSize.contains(i)) {
                    additionalFromListMaxSize.put(i, -1);
                }
            }
            if(!listMinSize.isEmpty()){
                comparedObject.keySet().forEach(key -> {
                    T currentObject1 = listMinSize.get(key);
                    T currentObject2 = listMaxSize.get(comparedObject.get(key));
                    result1.addAll(setColorEveryWhere(Arrays.asList(currentObject1.toString().split("\n")), DEFAULT));
                    result2.addAll(fieldEnumerator(currentObject1, currentObject2));
                });
            }
            result1.addAll(getAdditionalForPrint(additionalFromListMinSize.keySet(), listMinSize).get(0));
            result2.addAll(getAdditionalForPrint(additionalFromListMinSize.keySet(), listMinSize).get(1));
            result1.addAll(getAdditionalForPrint(additionalFromListMaxSize.keySet(), listMaxSize).get(1));
            result2.addAll(getAdditionalForPrint(additionalFromListMaxSize.keySet(), listMaxSize).get(0));
            int index;
            if (!listMinSize.isEmpty()) {
                index = searchLastNeedElementIgnoreColor(result1, "},");
                result1.set(index, result1.get(index).replaceAll("},", "}"));
            }
            index = searchLastNeedElementIgnoreColor(result2, "},");
            result2.set(index, result2.get(index).replaceAll("},", "}"));
        }
        if(objectList1.equals(listMinSize)) {
            result.add(result1);
            result.add(result2);
        } else {
            result.add(result2);
            result.add(result1);
        }
        return result;
    }

    private <T> int getWorstGrade(T obj, boolean withAdditional) {
        int result = 0;
        for (Field field: obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            JsonProperty jsonProperty = field.getDeclaredAnnotation(JsonProperty.class);
            try {
                if(field.get(obj) instanceof  Hashes) {
                    result+=getWorstGrade(field.get(obj), false);
                } else if(withAdditional) {
                    result+=jsonProperty.required() ? 50:1;
                } else {
                    result+=jsonProperty.required() ? 50:0;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private <T> List<String> fieldEnumerator(T obj1, T obj2) {
        List<String> result = new ArrayList<>(setColorEveryWhere(Arrays.asList(obj2.toString().split("\n")), DEFAULT));
        for (Field field: obj2.getClass().getDeclaredFields()) {
            JsonProperty jsonProperty = field.getDeclaredAnnotation(JsonProperty.class);
            if("hashes".equals(field.getName())) {
                try {
                    field.setAccessible(true);
                    if(field.get(obj2) instanceof Hashes) {
                        for (Field field1: Hashes.class.getDeclaredFields()) {
                            JsonProperty jsonProperty1 = field1.getDeclaredAnnotation(JsonProperty.class);
                            equalsAndSetColor(field.get(obj1), field.get(obj2), field1.getName(), result, jsonProperty1.value(), ERROR);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if(jsonProperty.required()) {
                equalsAndSetColor(obj1, obj2, field.getName(), result, jsonProperty.value(), ERROR);
            } else {
                equalsAndSetColor(obj1, obj2, field.getName(), result, jsonProperty.value(), WARNING);
            }
        }
        return result;
    }

    private void equalsAndSetColor(Object currentObject1, Object currentObject2, String elementNamePojo, List<String> list, String elementNameJson, String color) {
        try {
            Field field = currentObject1.getClass().getDeclaredField(elementNamePojo);
            field.setAccessible(true);
            if(field.get(currentObject1) != null) {
                if(!field.get(currentObject1).equals(field.get(currentObject2))) {
                    setColor(list, elementNameJson, color);
                }
            } else if(field.get(currentObject2) != null) {
                setColor(list, elementNameJson, color);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Map<Integer, Integer> elementMatcher(List<List<Integer>> gradesMin, List<List<Integer>> gradesMax, int gradesWorst) {
        Map<Integer, Integer> result =  new HashMap<>();
        for (int i = 0; i < gradesMin.size(); i++) {
            List<Integer> immutableList = new ArrayList<>(gradesMin.get(i));
            List<Integer> currentGrades = new ArrayList<>(gradesMin.get(i));
            int bestGrade = -1;
            for (int j = 0; j < immutableList.size(); j++) {
                bestGrade = Collections.min(currentGrades);
                if(bestGrade >= gradesWorst) {
                    break;
                }
                if(Collections.min(gradesMax.get(immutableList.indexOf(bestGrade))) == bestGrade && !result.containsKey(immutableList.indexOf(bestGrade))) {
                    result.put(i, immutableList.indexOf(bestGrade));
                    break;
                }
                currentGrades.remove(new Integer(bestGrade));
            }
            if(bestGrade == -1) {
                result.put(i, -1);
            }
        }
        return result;
    }

    private List<List<String>> getAdditionalForPrint(Collection<Integer> keys, List<?> list) {
        List<List<String>> result = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        List<String> spaceList = new ArrayList<>();
        keys.forEach(key -> {
            for (int i = 0; i < list.get(key).toString().split("\n").length; i++) {
                spaceList.add(" "+KEY_EXPRESSION+DEFAULT);
            }
            stringList.addAll(setColorEveryWhere(Arrays.asList(list.get(key).toString().split("\n")), NOTIFICATION));
        });
        result.add(stringList);
        result.add(spaceList);
        return result;
    }

    private int searchLastNeedElementIgnoreColor(List<String> list, String searchString) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).matches("^" + searchString + ".*$")) {
                indexes.add(i);
            }
        }
        return Collections.max(indexes);
    }

    private <T extends Comparable<T>> List<List<Integer>> getGrades(List<T> list1, List<T> list2) {
        List<List<Integer>> result = new ArrayList<>();
        for (T obj1 : list1) {
            List<Integer> currentGrades = new ArrayList<>();
            for (T obj2 : list2) {
                currentGrades.add(obj1.compareTo(obj2));
            }
            result.add(currentGrades);
        }
        return result;
    }

    private void setColor(List<String> list, String element, String color) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).matches("^\"" + element + "\" : .+$")) {
                list.set(i, list.get(i).replaceAll(KEY_EXPRESSION + DEFAULT, KEY_EXPRESSION + color));
            }
        }
    }

    private List<String> setColorEveryWhere(List<String> input, String color) {
        for (int i = 0; i < input.size(); i++)
            input.set(i, input.get(i) + KEY_EXPRESSION + color);
        return input;
    }
}
