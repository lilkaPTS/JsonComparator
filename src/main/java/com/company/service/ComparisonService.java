package com.company.service;

import com.company.model.ConfigFile;
import com.company.model.ResponseView;
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

        result.add("{");

        metadata.get(0).forEach(result::add1);
        metadata.get(1).forEach(result::add2);

        services.get(0).forEach(result::add1);
        services.get(1).forEach(result::add2);

        result.add("}");
        return result;
    }

    public List<List<String>> getMetadata(ConfigFile config1, ConfigFile config2) {
        List<List<String>> result = new ArrayList<>();
        List<String> metadata1 = new ArrayList<>(setColorEveryWhere(Arrays.asList(config1.getMetadata().toString().split("\n")), DEFAULT));
        List<String> metadata2 = new ArrayList<>(setColorEveryWhere(Arrays.asList(config2.getMetadata().toString().split("\n")), DEFAULT));
        equalsAndSetColor(config1.getMetadata().getDescription(), config2.getMetadata().getDescription(), "version", metadata2, "version", ERROR);
        equalsAndSetColor(config1.getMetadata().getApplication(), config2.getMetadata().getApplication(), "name", metadata2, "name", ERROR);
        result.add(metadata1);
        result.add(metadata2);
        return result;
    }

    public <T extends Comparable<T>> int getWorstGrade(T obj, boolean withAdditional) {
        int result = 0;
        for (Field field: obj.getClass().getDeclaredFields()) {
            JsonProperty jsonProperty = field.getDeclaredAnnotation(JsonProperty.class);
            if(withAdditional) {
                result+=jsonProperty.required() ? 50:1;
            } else {
                result+=jsonProperty.required() ? 50:0;
            }
        }
        return result;
    }

    public <T extends Comparable<T>> List<List<String>> getArray(List<T> objectList1, List<T> objectList2) {
        List<List<String>> result = new ArrayList<>();
        List<T> listMinSize = objectList1.size() <= objectList2.size() ? objectList1 : objectList2;
        List<T> listMaxSize = listMinSize.equals(objectList1) ? objectList2 : objectList1;
        List<List<Integer>> gradesMin = getGrades(listMinSize, listMaxSize);
        List<List<Integer>> gradesMax = getGrades(listMaxSize, listMinSize);
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
                    result2.addAll(test1(currentObject1, currentObject2));
                });
            }
            result1.addAll(getAdditionalForPrint(additionalFromListMinSize.keySet(), listMinSize).get(0));
            result2.addAll(getAdditionalForPrint(additionalFromListMinSize.keySet(), listMinSize).get(1));
            result1.addAll(getAdditionalForPrint(additionalFromListMaxSize.keySet(), listMaxSize).get(1));
            result2.addAll(getAdditionalForPrint(additionalFromListMaxSize.keySet(), listMaxSize).get(0));
            if (!listMinSize.isEmpty())
                result1.set(searchLastNeedElementIgnoreColor(result1, "},"), "}");
            result2.set(searchLastNeedElementIgnoreColor(result2, "},"), "}");
        }
        result.add(result1);
        result.add(result2);
        return result;
    }

    public <T extends Comparable<T>> List<String> test1(T obj1, T obj2) {
        List<String> result = new ArrayList<>(setColorEveryWhere(Arrays.asList(obj2.toString().split("\n")), DEFAULT));
        List<String> elementNamePojo = new ArrayList<>();
        List<String> elementNameJson = new ArrayList<>();
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
            }
            if(jsonProperty.required()) {
                equalsAndSetColor(obj1, obj2, field.getName(), result, jsonProperty.value(), ERROR);
            } else {
                equalsAndSetColor(obj1, obj2, field.getName(), result, jsonProperty.value(), WARNING);
            }
        }
        return result;
    }

    public List<List<String>> getServices(ConfigFile config1, ConfigFile config2) {
        List<List<String>> result = new ArrayList<>();
        List<com.company.pojo.Service> servicesMinSize = config1.getServices().size() <= config2.getServices().size() ?
                config1.getServices() : config2.getServices();
        List<com.company.pojo.Service> servicesMaxSize = servicesMinSize.equals(config1.getServices()) ?
                config2.getServices() : config1.getServices();
        List<List<Integer>> gradesMin = getGrades(servicesMinSize, servicesMaxSize);
        List<List<Integer>> gradesMax = getGrades(servicesMaxSize, servicesMinSize);
        //key-minSizeServices, value-maxSizeServices
        Map<Integer, Integer> comparedServices = new HashMap<>(elementMatcher(gradesMin, gradesMax, getWorstGrade(new com.company.pojo.Service(), false)));
        Map<Integer, Integer> additionalFromServicesMinSize = new HashMap<>();
        Map<Integer, Integer> additionalFromServicesMaxSize = new HashMap<>();
        List<Integer> skipFromServicesMinSize = new ArrayList<>(comparedServices.keySet());
        List<Integer> skipFromServicesMaxSize = new ArrayList<>(comparedServices.values());
        for (int i = 0; i < servicesMinSize.size(); i++) {
            if (!skipFromServicesMinSize.contains(i)) {
                additionalFromServicesMinSize.put(i, -1);
            }
        }
        for (int i = 0; i < servicesMaxSize.size(); i++) {
            if (!skipFromServicesMaxSize.contains(i)) {
                additionalFromServicesMaxSize.put(i, -1);
            }
        }
        List<String> servicesResult1 = new ArrayList<>(); // servicesMinSize
        List<String> servicesResult2 = new ArrayList<>(); // servicesMaxSize
        if(!servicesMinSize.isEmpty() && !servicesMaxSize.isEmpty()){
            comparedServices.keySet().forEach(key -> {
                com.company.pojo.Service currentService1 = servicesMinSize.get(key);
                com.company.pojo.Service currentService2 = servicesMaxSize.get(comparedServices.get(key));
                servicesResult1.addAll(setColorEveryWhere(Arrays.asList(currentService1.toString().split("\n")), DEFAULT));
                List<String> auxiliaryList = new ArrayList<>(setColorEveryWhere(Arrays.asList(currentService2.toString().split("\n")), DEFAULT));
                equalsAndSetColor(currentService1, currentService2, "serviceShortName", auxiliaryList, "service-short-name", WARNING);
                equalsAndSetColor(currentService1, currentService2, "serviceName", auxiliaryList, "service_name", ERROR);
                equalsAndSetColor(currentService1, currentService2, "artifactType", auxiliaryList, "artifact_type", ERROR);
                equalsAndSetColor(currentService1, currentService2, "dockerRegistry", auxiliaryList, "docker_registry", ERROR);
                equalsAndSetColor(currentService1, currentService2, "dockerImageName", auxiliaryList, "docker_image_name", ERROR);
                equalsAndSetColor(currentService1, currentService2, "dockerTag", auxiliaryList, "docker_tag", ERROR);
                equalsAndSetColor(currentService1, currentService2, "force", auxiliaryList, "force", WARNING);
                equalsAndSetColor(currentService1, currentService2, "gitRepository", auxiliaryList, "github_repository", WARNING);
                equalsAndSetColor(currentService1, currentService2, "githubBranch", auxiliaryList, "github_branch", WARNING);
                equalsAndSetColor(currentService1, currentService2, "githubBash", auxiliaryList, "github_hash", WARNING);
                if (!currentService1.getHashes().equals(currentService2.getHashes())) {
                    equalsAndSetColor(currentService1.getHashes(), currentService2.getHashes(), "sha1", auxiliaryList, "sha1", ERROR);
                    equalsAndSetColor(currentService1.getHashes(), currentService2.getHashes(), "sha256", auxiliaryList, "sha256", ERROR);
                }
                servicesResult2.addAll(auxiliaryList);
            });
        }
        servicesResult1.addAll(getAdditionalForPrint(additionalFromServicesMinSize.keySet(), servicesMinSize).get(0));
        servicesResult2.addAll(getAdditionalForPrint(additionalFromServicesMinSize.keySet(), servicesMinSize).get(1));
        servicesResult1.addAll(getAdditionalForPrint(additionalFromServicesMaxSize.keySet(), servicesMaxSize).get(1));
        servicesResult2.addAll(getAdditionalForPrint(additionalFromServicesMaxSize.keySet(), servicesMaxSize).get(0));
        if (!servicesMinSize.isEmpty())
            servicesResult1.set(searchLastNeedElementIgnoreColor(servicesResult1, "},"), "}");
        if (!servicesMaxSize.isEmpty())
           servicesResult2.set(searchLastNeedElementIgnoreColor(servicesResult2, "},"), "}");
        servicesResult1.add(0,"\"services\": [");
        servicesResult2.add(0,"\"services\": [");
        servicesResult1.add("],");
        servicesResult2.add("],");
        result.add(servicesResult1);
        result.add(servicesResult2);
        return result;
    }

    public void equalsAndSetColor(Object currentObject1, Object currentObject2, String elementNamePojo, List<String> list, String elementNameJson, String color) {
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

    public Map<Integer, Integer> elementMatcher(List<List<Integer>> gradesMin, List<List<Integer>> gradesMax, int gradesWorst) {
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

    public List<List<String>> getAdditionalForPrint(Collection<Integer> keys, List<?> list) {
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

    public int searchLastNeedElementIgnoreColor(List<String> list, String searchString) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).matches("^" + searchString + ".*$")) {
                indexes.add(i);
            }
        }
        return Collections.max(indexes);
    }

    public <T extends Comparable<T>> List<List<Integer>> getGrades(List<T> list1, List<T> list2) {
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

    public void setColor(List<String> list, String element, String color) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).matches("^\"" + element + "\" : .+$")) {
                list.set(i, list.get(i).replaceAll(KEY_EXPRESSION + DEFAULT, KEY_EXPRESSION + color));
            }
        }
    }

    public List<String> setColorEveryWhere(List<String> input, String color) {
        for (int i = 0; i < input.size(); i++)
            input.set(i, input.get(i) + KEY_EXPRESSION + color);
        return input;
    }
}
