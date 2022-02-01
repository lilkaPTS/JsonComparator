package com.company.service;

import com.company.model.ConfigFile;
import com.company.model.ResponseView;
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
        List<List<String>> services = getServices(config1, config2);

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

    public List<List<String>> getServices(ConfigFile config1, ConfigFile config2) {
        List<List<String>> result = new ArrayList<>();
        List<com.company.pojo.Service> servicesMinSize = config1.getServices().size() <= config2.getServices().size() ?
                config1.getServices() : config2.getServices();
        List<com.company.pojo.Service> servicesMaxSize = servicesMinSize.equals(config1.getServices()) ?
                config2.getServices() : config1.getServices();
        List<List<Integer>> gradesMin = getGrades(servicesMinSize, servicesMaxSize);
        List<List<Integer>> gradesMax = getGrades(servicesMaxSize, servicesMinSize);
        //key-minSizeServices, value-maxSizeServices
        Map<Integer, Integer> comparedServices = new HashMap<>(elementMatcher(gradesMin, gradesMax, 84));
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
                if(Collections.min(gradesMax.get(immutableList.indexOf(bestGrade))) == bestGrade){// && !result.containsKey(immutableList.indexOf(bestGrade))) {
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

    public List<List<Integer>> getGrades(List<com.company.pojo.Service> list1, List<com.company.pojo.Service> list2) {
        List<List<Integer>> result = new ArrayList<>();
        for (com.company.pojo.Service service1 : list1) {
            List<Integer> currentGrades = new ArrayList<>();
            for (com.company.pojo.Service service2 : list2) {
                currentGrades.add(service1.compareTo(service2));
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
