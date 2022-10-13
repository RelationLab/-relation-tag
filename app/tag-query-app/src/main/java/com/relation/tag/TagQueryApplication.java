package com.relation.tag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.relation.tag"}, exclude = {ElasticsearchDataAutoConfiguration.class})
public class TagQueryApplication {
    org.springframework.core.io.support.SpringFactoriesLoader SpringFactoriesLoader;

    public static void main(String[] args) {
        SpringApplication.run(TagQueryApplication.class, args);
    }

}
