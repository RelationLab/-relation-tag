package com.relation.tag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.relation.tag",
        "org.springframework.boot.extension"})
public class TagQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(TagQueryApplication.class, args);
    }

}
