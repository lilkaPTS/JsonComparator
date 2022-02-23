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
        List<List<String>> metadata = getMetadata(config1, config2);
        List<List<String>> services = getArray(config1.getServices(), config2.getServices());
        List<List<String>> artifacts = getArtifacts(config1.getArtifacts(), config2.getArtifacts());
        List<List<String>> script = getArray(config1.getScript(), config2.getScript());
        List<List<String>> rpm = getArray(config1.getRpm(), config2.getRpm());
        List<List<String>> parameters = getParameter(config1, config2);


        result.add("{"+KEY_EXPRESSION+DEFAULT);

        metadata.get(0).forEach(result::add1);
        metadata.get(1).forEach(result::add2);

        result.add("\"services\": ["+KEY_EXPRESSION+DEFAULT);
        services.get(0).forEach(result::add1);
        services.get(1).forEach(result::add2);
        result.add("],"+KEY_EXPRESSION+DEFAULT);


        result.add("\"artifacts\": ["+KEY_EXPRESSION+DEFAULT);
        artifacts.get(0).forEach(result::add1);
        artifacts.get(1).forEach(result::add2);
        result.add("],"+KEY_EXPRESSION+DEFAULT);

        result.add("\"script\": ["+KEY_EXPRESSION+DEFAULT);
        script.get(0).forEach(result::add1);
        script.get(1).forEach(result::add2);
        result.add("],"+KEY_EXPRESSION+DEFAULT);

        result.add("\"rpm\": ["+KEY_EXPRESSION+DEFAULT);
        rpm.get(0).forEach(result::add1);
        rpm.get(1).forEach(result::add2);
        result.add("],"+KEY_EXPRESSION+DEFAULT);

        result.add("\"parameters\": {"+KEY_EXPRESSION+DEFAULT);
        parameters.get(0).forEach(result::add1);
        parameters.get(1).forEach(result::add2);
        result.add("}"+KEY_EXPRESSION+DEFAULT);
        result.add("}"+KEY_EXPRESSION+DEFAULT);
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

    private List<List<String>> getArtifacts(List<ArtifactObject> list1, List<ArtifactObject> list2) {
        List<List<String>> result = new ArrayList<>();
        List<ArtifactObject> artifactObject1List1 = new ArrayList<>();
        List<ArtifactObject> artifactObject2List1 = new ArrayList<>();
        List<ArtifactObject> artifactObject1List2 = new ArrayList<>();
        List<ArtifactObject> artifactObject2List2 = new ArrayList<>();
        int mvnMaxSize = 0;
        int fileMaxSize = 0;
        for(ArtifactObject element : list1) {
            if("ArtifactObject1".equals(element.getClass().getSimpleName())) {
                mvnMaxSize = Math.max(((ArtifactObject1) element).getMvn().size(), mvnMaxSize);
                artifactObject1List1.add(element);
            } else {
                fileMaxSize = Math.max(((ArtifactObject2) element).getFile().size(), fileMaxSize);
                artifactObject2List1.add(element);
            }
        }
        for(ArtifactObject element : list2) {
            if("ArtifactObject1".equals(element.getClass().getSimpleName())) {
                mvnMaxSize = Math.max(((ArtifactObject1) element).getMvn().size(), mvnMaxSize);
                artifactObject1List2.add(element);
            } else {
                fileMaxSize = Math.max(((ArtifactObject2) element).getFile().size(), fileMaxSize);
                artifactObject2List2.add(element);
            }
        }
        for (ArtifactObject element : artifactObject2List1) {
            for (int i = 0; i < fileMaxSize - ((ArtifactObject2)element).getFile().size(); i++) {
                ((ArtifactObject2)element).addFile(" ");
            }
        }
        for (ArtifactObject element : artifactObject2List2) {
            for (int i = 0; i < fileMaxSize - ((ArtifactObject2)element).getFile().size(); i++) {
                ((ArtifactObject2)element).addFile(" ");
            }
        }
        List<List<String>> artifactObject1Result = getArray(artifactObject1List1, artifactObject1List2, mvnMaxSize, fileMaxSize);
        List<List<String>> artifactObject2Result = getArray(artifactObject2List1, artifactObject2List2, mvnMaxSize, fileMaxSize);
        List<String> result1 = new ArrayList<>(artifactObject1Result.get(0));
        List<String> result2 = new ArrayList<>(artifactObject1Result.get(1));
        if(!result1.isEmpty()) {
            result1.set(searchLastNeedElementIgnoreColor(result1, "}"), "},");
        }
        if(!result2.isEmpty()) {
            result2.set(searchLastNeedElementIgnoreColor(result2, "}"), "},");
        }
        result1.addAll(artifactObject2Result.get(0));
        result2.addAll(artifactObject2Result.get(1));

        result.add(result1);
        result.add(result2);
        return result;
    }

    private List<List<String>> getParameter(ConfigFile config1, ConfigFile config2) {
        List<List<String>> result = new ArrayList<>();
        List<List<String>> common = mapPrinter(getMapDependencies(config1.getParameters().getCommon(), config2.getParameters().getCommon()), "common");
        List<String> parameters1 = common.get(0);
        List<String> parameters2 = common.get(1);
        addToLists(parameters1, parameters2, "\""+"services"+"\" : {"+KEY_EXPRESSION+DEFAULT);
        Dependencies<String,Map<String, String>> dependencies = getMapDependencies(config1.getParameters().getServices(), config2.getParameters().getServices());
        List<String> compared = dependencies.getCompared();
        List<String> additional1 = dependencies.getAdditional1();
        List<String> additional2 = dependencies.getAdditional2();
        for(String key:compared) {
            List<List<String>> interimResult = mapPrinter(getMapDependencies(dependencies.getMap1().get(key), dependencies.getMap2().get(key)), key);
            parameters1.addAll(interimResult.get(0));
            parameters2.addAll(interimResult.get(1));
        }
        for(String key:additional1) {
            List<List<String>> interimResult = additionalMapPrinter(dependencies.getMap1().get(key), key);
            parameters1.addAll(interimResult.get(0));
            parameters2.addAll(interimResult.get(1));
        }
        for(String key:additional2) {
            List<List<String>> interimResult = additionalMapPrinter(dependencies.getMap2().get(key), key);
            parameters1.addAll(interimResult.get(1));
            parameters2.addAll(interimResult.get(0));
        }
        addToLists(parameters1, parameters2, "}"+KEY_EXPRESSION+DEFAULT);
        deleteLastComma(parameters1);
        deleteLastComma(parameters2);
        result.add(parameters1);
        result.add(parameters2);
        return result;
    }

    private <T extends Comparable<T>> List<List<String>> getArray(List<T> objectList1, List<T> objectList2, int ... collectionSizesInOrder) {
        List<List<String>> result = new ArrayList<>();
        List<T> listMinSize = objectList1.size() <= objectList2.size() ? objectList1 : objectList2;
        List<T> listMaxSize = listMinSize.equals(objectList1) ? objectList2 : objectList1;
        List<List<Integer>> gradesMin = getGrades(listMinSize, listMaxSize, collectionSizesInOrder);
        List<String> result1 = new ArrayList<>(); // MinSize
        List<String> result2 = new ArrayList<>(); // MaxSize
        //key-minSize, value-maxSize
        if(!listMaxSize.isEmpty()) {
            //min to max
            Map<Integer, Integer> comparedObject = new HashMap<>(getComparedArrayElements(gradesMin, getWorstGradeIgnoreAdditional(listMaxSize.get(0), collectionSizesInOrder)));
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
            Map<Integer, Integer> comparedObjectRL = new HashMap<>();
            Map<Integer, Integer> additionalFromLeftList;
            Map<Integer, Integer> additionalFromRightList;
            if(objectList1.size() > objectList2.size()) {
                for(Integer key: comparedObject.keySet()) {
                    comparedObjectRL.put(comparedObject.get(key), key);
                }
                additionalFromLeftList = additionalFromListMaxSize;
                additionalFromRightList = additionalFromListMinSize;
            } else {
                comparedObjectRL = comparedObject;
                additionalFromLeftList = additionalFromListMinSize;
                additionalFromRightList = additionalFromListMaxSize;
            }
            if(!listMinSize.isEmpty()){
                for(Integer key: comparedObjectRL.keySet()) {
                    T currentObject1 = objectList1.get(key);
                    T currentObject2 = objectList2.get(comparedObjectRL.get(key));
                    if(currentObject1 instanceof ArtifactObject1) {
                        List<List<String>> interimResult = getArray(((ArtifactObject1) currentObject1).getMvn(), ((ArtifactObject1) currentObject2).getMvn());
                        addToLists(result1, result2, "{");
                        addToLists(result1, result2, "\"mvn\" : [");
                        result1.addAll(interimResult.get(0));
                        result2.addAll(interimResult.get(1));
                        addToLists(result1, result2, "],");
                        addToLists(result1, result2, "\"target_repository\" : \"" + ((ArtifactObject1) currentObject1).getTargetRepository() + "\"");
                        addToLists(result1, result2, "},");
                    } else {
                        List<List<String>> interimResult = colorDeterminer(currentObject1, currentObject2);
                        result1.addAll(interimResult.get(0));
                        result2.addAll(interimResult.get(1));
                    }
                }
            }
            result1.addAll(getAdditionalForPrint(additionalFromLeftList.keySet(), objectList1).get(0));
            result2.addAll(getAdditionalForPrint(additionalFromLeftList.keySet(), objectList1).get(1));
            result1.addAll(getAdditionalForPrint(additionalFromRightList.keySet(), objectList2).get(1));
            result2.addAll(getAdditionalForPrint(additionalFromRightList.keySet(), objectList2).get(0));
        }
        int index;
        if (!objectList1.isEmpty()) {
            index = searchLastNeedElementIgnoreColor(result1, "},");
            result1.set(index, result1.get(index).replaceAll("},", "}"));
        }
        if(!objectList2.isEmpty()) {
            index = searchLastNeedElementIgnoreColor(result2, "},");
            result2.set(index, result2.get(index).replaceAll("},", "}"));
        }
        result.add(result1);
        result.add(result2);
        return result;
    }

    private Map<Integer, Integer> getComparedArrayElements(List<List<Integer>> gradesMin, int gradesWorst) {
        Map<Integer, Integer> result =  new HashMap<>();
        List<Integer> skipI = new ArrayList<>();
        List<Integer> skipJ = new ArrayList<>();
        int bestGrade = gradesWorst;
        Map.Entry<Integer, Integer> bestGradeCoordinate = new AbstractMap.SimpleEntry<>(0,0);
        for (int counter = 0; counter < gradesMin.size(); counter++) {
            for (int i = 0; i < gradesMin.size(); i++) {
                if(skipI.contains(i)){
                    continue;
                }
                for (int j = 0; j < gradesMin.get(i).size(); j++) {
                    if(skipJ.contains(j)) {
                        continue;
                    }
                    if (bestGrade > gradesMin.get(i).get(j)) {
                        bestGrade = gradesMin.get(i).get(j);
                        bestGradeCoordinate = new AbstractMap.SimpleEntry<>(i, j);
                    }
                }
            }
            if(bestGrade < gradesWorst) {
                int key = bestGradeCoordinate.getKey();
                int value = bestGradeCoordinate.getValue();

                List<Integer> listJ = new ArrayList<>();
                for (int j = 0; j < gradesMin.get(key).size(); j++) {
                    if(gradesMin.get(key).get(j)==bestGrade && !result.containsValue(j)) {
                        listJ.add(j);
                    }
                }
                if(listJ.size() == 1) {
                    result.put(key, value);
                    skipI.add(key);
                    skipJ.add(value);
                } else {
                    List<List<Integer>> choiceList = new ArrayList<>();
                    for (int i = 0; i < listJ.size(); i++) {
                        List<Integer> list = new ArrayList<>();
                        for (int j = 0; j < gradesMin.size(); j++) {
                            if((j==key || result.containsKey(j))) {
                                continue;
                            }
                            for (int k = 0; k < gradesMin.get(j).size(); k++) {
                                if(k == listJ.get(i)) {
                                    list.add(gradesMin.get(j).get(k));
                                }
                            }
                        }
                        choiceList.add(list);
                    }
                    List<Integer> minElement = new ArrayList<>();
                    while (!choiceList.get(0).isEmpty()){
                        List<Integer> currentMinElement = new ArrayList<>();
                        for (int i = 0; i < choiceList.size(); i++) {
                            currentMinElement.add(Collections.min(choiceList.get(i)));
                        }
                        if(currentMinElement.size() == (int) currentMinElement.stream().filter(e-> e.equals(currentMinElement.get(0))).count()) {
                            for (int i = 0; i < choiceList.size(); i++) {
                                for (int j = 0; j < choiceList.get(i).size(); j++) {
                                    choiceList.get(i).remove(currentMinElement.get(0));
                                }
                            }
                            minElement = currentMinElement;
                        } else {
                            minElement = currentMinElement;
                            break;
                        }
                    }
                    if(minElement.isEmpty()) {
                        result.put(key, value);
                        skipI.add(key);
                        skipJ.add(value);
                    } else {
                        result.put(key, listJ.get(minElement.indexOf(Collections.max(minElement))));
                        skipI.add(key);
                        skipJ.add(listJ.get(minElement.indexOf(Collections.max(minElement))));
                    }
                }
                bestGrade = gradesWorst;
            } else {
                break;
            }
        }
        return result;
    }

    private <K,V> Dependencies<K,V> getMapDependencies(Map<K, V> map1, Map<K, V> map2) {
        Dependencies<K,V> result = new Dependencies<>();
        List<K> compared = new ArrayList<>();
        List<K> additional1 = new ArrayList<>();
        List<K> additional2 = new ArrayList<>();
        List<K> keys1 = new ArrayList<>();
        List<K> keys2 = new ArrayList<>();
        if(map1 != null) {
            keys1.addAll(map1.keySet());
        }
        if(map2 != null) {
            keys2.addAll(map2.keySet());
        }
        if(!keys1.isEmpty()) {
            if(!keys2.isEmpty()) {
                for (K key : keys1) {
                    if (keys2.contains(key)) {
                        compared.add(key);
                    }
                }
            }
        }
        keys1.forEach(key -> {
            if(!compared.contains(key)) {
                additional1.add(key);
            }
        });
        keys2.forEach(key -> {
            if(!compared.contains(key)) {
                additional2.add(key);
            }
        });
        result.setCompared(compared);
        result.setAdditional1(additional1);
        result.setAdditional2(additional2);
        result.setMap1(map1);
        result.setMap2(map2);
        return result;
    }

    private <K, V> List<List<String>> mapPrinter(Dependencies<K,V> dependencies, String mapNameJson) {
        List<List<String>> result = new ArrayList<>();
        List<String> result1 = new ArrayList<>();
        List<String> result2 = new ArrayList<>();
        Map<K,V> map1 = dependencies.getMap1();
        Map<K,V> map2 = dependencies.getMap2();
        addToLists(result1, result2, "\""+mapNameJson+"\" : {"+KEY_EXPRESSION+DEFAULT);
        for (K key: dependencies.getCompared()) {
            if(map1.get(key).equals(map2.get(key))) {
                addToLists(result1, result2, "\"" + key + "\" : \"" + map1.get(key) + "\","+KEY_EXPRESSION+DEFAULT);
            } else {
                result1.add("\"" + key + "\" : \"" + map1.get(key) + "\","+KEY_EXPRESSION+DEFAULT);
                result2.add("\"" + key + "\" : \"" + map2.get(key) + "\","+KEY_EXPRESSION+WARNING);
            }
        }
        for (K key: dependencies.getAdditional1()) {
            result1.add("\"" + key + "\" : \"" + map1.get(key) + "\","+KEY_EXPRESSION+NOTIFICATION);
            result2.add(" "+KEY_EXPRESSION+DEFAULT);
        }
        for (K key: dependencies.getAdditional2()) {
            result1.add(" "+KEY_EXPRESSION+DEFAULT);
            result2.add("\"" + key + "\" : \"" + map2.get(key) + "\","+KEY_EXPRESSION+NOTIFICATION);
        }
        deleteLastComma(result1);
        deleteLastComma(result2);
        addToLists(result1, result2, "},"+KEY_EXPRESSION+DEFAULT);
        result.add(result1);
        result.add(result2);
        return result;
    }

    private <K,V> List<List<String>> additionalMapPrinter(Map<K, V> map1, String mapNameJson) {
        List<List<String>> result = new ArrayList<>();
        List<String> result1 = new ArrayList<>();
        List<String> result2 = new ArrayList<>();
        result1.add("\""+mapNameJson+"\" : {"+KEY_EXPRESSION+NOTIFICATION);
        result2.add(" "+KEY_EXPRESSION+DEFAULT);
        for (K key: map1.keySet()) {
            result1.add("\"" + key + "\" : \"" + map1.get(key) + "\","+KEY_EXPRESSION+NOTIFICATION);
            result2.add(" "+KEY_EXPRESSION+DEFAULT);
        }
        deleteLastComma(result1);
        result1.add("},"+KEY_EXPRESSION+NOTIFICATION);
        result2.add(" "+KEY_EXPRESSION+DEFAULT);
        result.add(result1);
        result.add(result2);
        return result;
    }

    private int searchLastNeedElementIgnoreColor(List<String> list, String searchString) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).matches("^" + searchString + ".*$")) {
                indexes.add(i);
            }
        }
        if(!indexes.isEmpty()) {
            return Collections.max(indexes);
        } else {
            return -1;
        }
    }

    private void deleteLastComma(List<String> list) {
        int replaceIndex = searchCommaAtEndWordIgnoreColor(list);
        if(replaceIndex!=-1) {
            String[] valueAndColor = list.get(replaceIndex).split(KEY_EXPRESSION);
            list.set(replaceIndex, valueAndColor[0].substring(0, valueAndColor[0].length()-1)+KEY_EXPRESSION+valueAndColor[1]);
        }
    }

    private int searchCommaAtEndWordIgnoreColor(List<String> list) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).matches("^.*,"+KEY_EXPRESSION+".*$")) {
                indexes.add(i);
            }
        }
        if(!indexes.isEmpty()) {
            return Collections.max(indexes);
        } else {
            return -1;
        }
    }

    private <T> void addToLists(List<T> list1, List<T> list2, T obj) {
        list1.add(obj);
        list2.add(obj);
    }

    private <T> int getWorstGradeIgnoreAdditional(T obj, int ... collectionSizesInOrder) {
        int result = 0;
        for (Field field: obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            JsonProperty jsonProperty = field.getDeclaredAnnotation(JsonProperty.class);
            try {
                if(field.get(obj) instanceof  Hashes) {
                    result+= getWorstGradeIgnoreAdditional(field.get(obj));
                } else if(field.get(obj) instanceof List) {
                    if(obj instanceof ArtifactObject1) {
                      //  result+=collectionSizesInOrder[0]*50;
                    } else if(obj instanceof ArtifactObject2) {
                       // result+=collectionSizesInOrder[1]*50;
                    }
                } else {
                    result+=jsonProperty.required() ? 50:0;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private void colorArray(List<String> list, String element, String color) {
        int startIndex = -1;
        int endIndex = -1;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).matches("^\""+element+"\" : \\[.*")) {
                startIndex = i;
                break;
            }
        }
        if(startIndex==-1) {
            return;
        }
        for (int i = startIndex; i < list.size(); i++) {
            if(list.get(i).matches("^],.*")) {
                endIndex = i;
            }
        }
        if(endIndex!=-1) {
            for (int i = startIndex; i <= endIndex; i++) {
                list.set(i, list.get(i).split(KEY_EXPRESSION)[0]+KEY_EXPRESSION+color);
            }
        }
    }

    private <T> List<List<String>> colorDeterminer(T obj1, T obj2) {
        List<List<String>> result = new ArrayList<>();
        result.add(setColorEveryWhere(Arrays.asList(obj1.toString().split("\n")), DEFAULT));
        result.add(setColorEveryWhere(Arrays.asList(obj2.toString().split("\n")), DEFAULT));
        for (Field field: obj2.getClass().getDeclaredFields()) {
            JsonProperty jsonProperty = field.getDeclaredAnnotation(JsonProperty.class);
            if("hashes".equals(field.getName())) {
                try {
                    field.setAccessible(true);
                    if(field.get(obj2) instanceof Hashes) {
                        for (Field field1: Hashes.class.getDeclaredFields()) {
                            JsonProperty jsonProperty1 = field1.getDeclaredAnnotation(JsonProperty.class);
                            equalsAndSetColor(field.get(obj1), field.get(obj2), field1.getName(), result.get(1), jsonProperty1.value(), ERROR);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if("file".equals(field.getName())) {
                try {
                    field.setAccessible(true);
                    if(!field.get(obj1).equals(field.get(obj2))) {
                        colorArray(result.get(1), "file", WARNING);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if(jsonProperty.required()) {
                equalsAndSetColor(obj1, obj2, field.getName(), result.get(1), jsonProperty.value(), ERROR);
            } else {
                equalsAndSetColor(obj1, obj2, field.getName(), result.get(1), jsonProperty.value(), WARNING);
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

    private <T extends Comparable<T>> List<List<Integer>> getGrades(List<T> list1, List<T> list2, int ... collectionSizesInOrder) {
        List<List<Integer>> result = new ArrayList<>();
        for (T obj1 : list1) {
            List<Integer> currentGrades = new ArrayList<>();
            for (T obj2 : list2) {
                if(obj2 instanceof ArtifactObject1) {
                    currentGrades.add(obj1.compareTo(obj2)+collectionSizesInOrder[0]);
                } else if(obj2 instanceof ArtifactObject2) {
                    currentGrades.add(obj1.compareTo(obj2)+collectionSizesInOrder[1]);
                }
                else {
                    currentGrades.add(obj1.compareTo(obj2));
                }
            }
            result.add(currentGrades);
        }
        return result;
    }

    private void setColor(List<String> list, String element, String color) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).matches("^\\s*\"" + element + "\" : .+$")) {
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
