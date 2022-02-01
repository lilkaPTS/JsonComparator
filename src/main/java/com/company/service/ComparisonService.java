package com.company.service;

import com.company.model.ConfigFile;
import com.company.model.ResponseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public Map<Integer, Integer> elementMatcher(List<List<Integer>> gradesMin, List<List<Integer>> gradesMax, int gradesWorst) {
        Map<Integer, Integer> result =  new HashMap<>();
        for (int i = 0; i < gradesMin.size(); i++) {
            List<Integer> immutableList = new ArrayList<>(gradesMin.get(i));
            List<Integer> currentGrades = new ArrayList<>(gradesMin.get(i));
            int bestGrade = -1;
            for (int j = 0; j < immutableList.size(); j++) {
                bestGrade = Collections.min(currentGrades);
                if(bestGrade >= gradesWorst) {
                    result.put(i, -1);
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

    /**
     * Functional part of this function based for two matrices (gradesMin, gradesMax),
     * based of then
     */
    public List<List<String>> getServices(ConfigFile config1, ConfigFile config2){
        List<List<String>> result = new ArrayList<>();
        List<com.company.pojo.Service> config1Services = config1.getServices();
        List<com.company.pojo.Service> config2Services = config2.getServices();
        //gradesMax is the transpose of the gradesMin of comparator scores
        List<List<Integer>> gradesMin = config1Services.size() <= config2Services.size() ?
                    getGrades(config1.getServices(), config2.getServices()) :
                    getGrades(config2.getServices(), config1.getServices());
        List<List<Integer>> gradesMax = config1Services.size() >= config2Services.size()
                && !gradesMin.equals(getGrades(config1.getServices(), config2.getServices()))?
                    getGrades(config1.getServices(), config2.getServices()) :
                    getGrades(config2.getServices(), config1.getServices());
        //List<com.company.pojo.Service> config2Services = config2.getServices();
        //gradesWorst is maximum comparator score
        Map<Integer, Integer> comparedServicesAndAdditionalServices1 = new HashMap<>(elementMatcher(gradesMin, gradesMax, 84));
        Map<Integer, Integer> additionalServices2 = new HashMap<>();
        List<Integer> deletedIndexes = new ArrayList<>(comparedServicesAndAdditionalServices1.values());
        for (int i = 0; i < config2Services.size(); i++) { /// errrrrorooooooorrrr
            if(!deletedIndexes.contains(i)){
                additionalServices2.put(i, -1);
            }
        }
        System.out.println("gradesMin");
        gradesMin.forEach(System.out::println);
        System.out.println("gradesMax");
        gradesMax.forEach(System.out::println);
        System.out.println("comparedServicesAndAdditionalServices1");
        System.out.println(comparedServicesAndAdditionalServices1);
        System.out.println("additionalServices2");
        System.out.println(additionalServices2);
        List<String> servicesResult1 = new ArrayList<>();
        List<String> servicesResult2 = new ArrayList<>();
        servicesResult1.add("\"services\": [");
        servicesResult2.add("\"services\": [");
        comparedServicesAndAdditionalServices1.keySet().forEach(key -> {
            if(comparedServicesAndAdditionalServices1.get(key) != -1) {
                servicesResult1.addAll(setColorEveryWhere(Arrays.asList(config1Services.get(key).toString().split("\n")), DEFAULT));
                List<String> auxiliaryList = new ArrayList<>(setColorEveryWhere(Arrays.asList(config2Services.get(comparedServicesAndAdditionalServices1.get(key)).toString().split("\n")), DEFAULT));
                com.company.pojo.Service currentService1 = config1Services.get(key);
                com.company.pojo.Service currentService2 = config2Services.get(comparedServicesAndAdditionalServices1.get(key));
                if(!currentService1.getServiceName().equals(currentService2.getServiceName())) {
                    setColor(auxiliaryList, "service_name", ERROR);
                }
                if(!currentService1.getArtifactType().equals(currentService2.getArtifactType())) {
                    setColor(auxiliaryList, "artifact_type", ERROR);
                }
                if(!currentService1.getDockerRegistry().equals(currentService2.getDockerRegistry())) {
                    setColor(auxiliaryList, "docker_registry", ERROR);
                }
                if(!currentService1.getDockerImageName().equals(currentService2.getDockerImageName())) {
                    setColor(auxiliaryList, "docker_image_name", ERROR);
                }
                if(!currentService1.getDockerTag().equals(currentService2.getDockerTag())) {
                    setColor(auxiliaryList, "docker_tag", ERROR);
                }
                if(!currentService1.getHashes().equals(currentService2.getHashes())) {
                    List<Integer> inconsistenciesHashes = getMultipliersV2(config1Services.get(key).getHashes().compareTo(config2Services.get(comparedServicesAndAdditionalServices1.get(key)).getHashes()));
                    if(inconsistenciesHashes.contains(2)) {
                        setColor(auxiliaryList, "sha1", ERROR);
                    }
                    if(inconsistenciesHashes.contains(3)) {
                        setColor(auxiliaryList, "sha256", ERROR);
                    }
                }
                if(currentService1.getServiceShortName() != null) {
                    if(!currentService1.getServiceShortName().equals(currentService2.getServiceShortName())) {
                        setColor(auxiliaryList, "service-short-name", WARNING);
                    }
                } else if(currentService2.getServiceShortName() != null) {
                    setColor(auxiliaryList, "service-short-name", WARNING);
                }
                /*if(currentService1.getf) != null &&
                        currentService1.getServiceShortName().equals(currentService2.getServiceShortName())) {
                    setColor(auxiliaryList, "service-short-name", WARNING);
                } else if(currentService1.getServiceName() == null && currentService2.getServiceName() != null) {
                    setColor(auxiliaryList, "service-short-name", WARNING);
                }
                if(currentService1.getServiceName() != null &&
                        currentService1.getServiceShortName().equals(currentService2.getServiceShortName())) {
                    setColor(auxiliaryList, "service-short-name", WARNING);
                } else if(currentService1.getServiceName() == null && currentService2.getServiceName() != null) {
                    setColor(auxiliaryList, "service-short-name", WARNING);
                }
                if(currentService1.getServiceName() != null &&
                        currentService1.getServiceShortName().equals(currentService2.getServiceShortName())) {
                    setColor(auxiliaryList, "service-short-name", WARNING);
                } else if(currentService1.getServiceName() == null && currentService2.getServiceName() != null) {
                    setColor(auxiliaryList, "service-short-name", WARNING);
                }
                if(currentService1.getServiceName() != null &&
                        currentService1.getServiceShortName().equals(currentService2.getServiceShortName())) {
                    setColor(auxiliaryList, "service-short-name", WARNING);
                } else if(currentService1.getServiceName() == null && currentService2.getServiceName() != null) {
                    setColor(auxiliaryList, "service-short-name", WARNING);
                }*/
                servicesResult2.addAll(auxiliaryList);
            } else {
                List<String> auxiliaryList = new ArrayList<>();
                for (int i = 0; i < config1Services.get(key).toString().split("\n").length; i++) {
                    auxiliaryList.add(" ");
                }
                servicesResult1.addAll(setColorEveryWhere(Arrays.asList(config1Services.get(key).toString().split("\n")), NOTIFICATION));
                servicesResult2.addAll(setColorEveryWhere(auxiliaryList, DEFAULT));
            }
        });
        additionalServices2.keySet().forEach(key -> {
            List<String> auxiliaryList = new ArrayList<>();
            for (int i = 0; i < config2Services.get(key).toString().split("\n").length; i++) {
                auxiliaryList.add(" ");
            }
            servicesResult1.addAll(setColorEveryWhere(auxiliaryList, DEFAULT));
            servicesResult2.addAll(setColorEveryWhere(Arrays.asList(config2Services.get(key).toString().split("\n")), NOTIFICATION));
        });
        if(!config1.getServices().isEmpty())
            servicesResult1.set(searchLastNeedElementIgnoreColor(servicesResult1, "},"), "}");
        if(!config2.getServices().isEmpty())
            servicesResult2.set(searchLastNeedElementIgnoreColor(servicesResult2, "},"), "}");
        servicesResult1.add("],");
        servicesResult2.add("],");
        result.add(servicesResult1);
        result.add(servicesResult2);
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

    public boolean isInteger(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
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
}
