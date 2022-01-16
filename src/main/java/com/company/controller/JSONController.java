package com.company.controller;


import com.company.POJO.ConfigurationFile;
import com.company.model.JSONStructure;
import com.company.myPOJO.Root;
import com.company.service.SchemaService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class JSONController {
    @Autowired
    private SchemaService schemaService;

    @GetMapping("/")
    public String getStartPage() throws IOException {
        schemaService.schemaGenerate(JSONStructure.class);
        String j = "{\"artifacts\": [\n" +
                "    {\n" +
                "      \"mvn\": [\n" +
                "        {\n" +
                "          \"groupId\": \"log4j\",\n" +
                "          \"artifactId\": \"log4j\",\n" +
                "          \"version\": \"1.2.17\",\n" +
                "          \"service_name\": \"test-service-1\",\n" +
                "          \"classifier\": \"sources\",\n" +
                "          \"mvn_type\": \"pom\",\n" +
                "          \"mvn_repository\": \"https://repo1.maven.org/maven2\",\n" +
                "          \"hashes\": {\n" +
                "            \"sha1\": \"1123456f0f1erc9791fgfc5928c0d89a0bf31ae9\",\n" +
                "            \"sha256\": \"zxc34f60ffb0265ecgiuy2f2cbd2a4763528fef2e1a752465ca3fd5a01bf3513\"\n" +
                "          }\n" +
                "        }, {\n" +
                "          \"groupId\": \"log4j\",\n" +
                "          \"artifactId\": \"log4j\",\n" +
                "          \"version\": \"1.2.17\",\n" +
                "          \"service_name\": \"test-service-1\",\n" +
                "          \"mvn_type\": \"jar\",\n" +
                "          \"mvn_repository\": \"https://repo1.maven.org/maven2\",\n" +
                "          \"hashes\": {\n" +
                "            \"sha1\": \"tei2471b518c3882a29dce746579875dd13f3fty\",\n" +
                "            \"sha256\": \"hfyt975s4444f71f2ae76dfab1c51bf3cc9e55bi89d4e73944f54d8ed56ad28c\"\n" +
                "          }\n" +
                "        }, {\n" +
                "          \"groupId\": \"junit\",\n" +
                "          \"artifactId\": \"junit\",\n" +
                "          \"version\": \"4.12\",\n" +
                "          \"classifier\": \"sources\",\n" +
                "          \"mvn_type\": \"jar\",\n" +
                "          \"mvn_repository\": \"https://repo1.maven.org/maven2\",\n" +
                "          \"hashes\": {\n" +
                "            \"sha1\": \"a6c32b40bf3d76eca54e3c601e5d1470c86fcdfa\",\n" +
                "            \"sha256\": \"9f43fea92033ad82bcad2ae44cec5c82abc9d6ee4b095cab921d11ead98bf2ff\"\n" +
                "          }\n" +
                "        }, {\n" +
                "          \"groupId\": \"org.hamcrest\",\n" +
                "          \"artifactId\": \"hamcrest-core\",\n" +
                "          \"version\": \"1.3\",\n" +
                "          \"mvn_type\": \"jar\",\n" +
                "          \"mvn_repository\": \"https://repo1.maven.org/maven2\",\n" +
                "          \"hashes\": {\n" +
                "            \"sha1\": \"42a25dc3219429f0e5d060061f71acb49bf010a0\",\n" +
                "            \"sha256\": \"66fdef91e9739348df7a096aa384a5685f4e875584cce89386a7a47251c4d8e9\"\n" +
                "          }\n" +
                "        }, {\n" +
                "          \"groupId\": \"org.hamcrest\",\n" +
                "          \"artifactId\": \"hamcrest-core\",\n" +
                "          \"version\": \"1.3\",\n" +
                "          \"mvn_type\": \"pom\",\n" +
                "          \"mvn_repository\": \"https://repo1.maven.org/maven2\",\n" +
                "          \"hashes\": {\n" +
                "            \"sha1\": \"872e413497b906e7c9fa85ccc96046c5d1ef7ece\",\n" +
                "            \"sha256\": \"fde386a7905173a1b103de6ab820727584b50d0e32282e2797787c20a64ffa93\"\n" +
                "          }\n" +
                "        }\n" +
                "      ],\n" +
                "      \"target_repository\": \"mvn\"\n" +
                "    }, {\n" +
                "      \"service-short-name\": \"TEST_2\",\n" +
                "      \"service_name\": \"node-v0.6.11.tar.gz\",\n" +
                "      \"hashes\": {\n" +
                "        \"sha1\": \"5t8a614b22f1806f53ed68b1e68a2d3849130c15\",\n" +
                "        \"sha256\": \"e5nbyucce66bf62e142cf3430c137ac5f0c82b81f3f6099457a523afe3375916\"\n" +
                "      },\n" +
                "      \"file\": [\n" +
                "        \"https://nodejs.org/dist/node-v0.6.11.tar.gz\"\n" +
                "      ],\n" +
                "      \"target_repository\": \"file\"\n" +
                "    }, {\n" +
                "      \"service_name\": \"jenkins-2.223\",\n" +
                "      \"hashes\": {\n" +
                "        \"sha1\": \"fun7f57f7ff89adcff04c223a01cd06ab0932c21\",\n" +
                "        \"sha256\": \"3a38b40bf1b906146f6d330b8ad6211d9a50a9a97b16f92b5e6ce40b93a69238\"\n" +
                "      },\n" +
                "      \"file\": [\n" +
                "        \"http://ftp-nyc.osuosl.org/pub/jenkins/osx/jenkins-2.223.pkg\"\n" +
                "      ],\n" +
                "      \"target_repository\": \"file\"\n" +
                "    }\n" +
                "  ]}";
        String j1 = "{\n" +
                "  \"metadata\": {\n" +
                "    \"description\": {\n" +
                "      \"version\": 2\n" +
                "    },\n" +
                "    \"application\": {\n" +
                "      \"name\": \"application_name\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        Root obj = mapper.readValue(j, Root.class);
        //Gson g = new Gson();
        //ConfigurationFile cf = g.fromJson(j, ConfigurationFile.class);
        return "start";
    }
}
