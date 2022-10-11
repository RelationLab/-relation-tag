package com.relation.tag;

import com.relation.tag.opensearch.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.relation.tag",
        "org.springframework.boot.extension"})
public class TagQueryApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TagQueryApplication.class, args);
        MyService myService = context.getBean(MyService.class);
        myService.doWork();
    }

}
