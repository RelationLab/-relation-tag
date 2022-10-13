package com.relation.tag;

import com.relation.tag.entity.AddressLabel;
import com.relation.tag.entity.Page;
import com.relation.tag.service.AddressLabelService;
import org.assertj.core.util.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.relation.tag"}, exclude = {ElasticsearchDataAutoConfiguration.class})
public class TagQueryApplication {
    org.springframework.core.io.support.SpringFactoriesLoader SpringFactoriesLoader;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TagQueryApplication.class, args);
        AddressLabelService addressLabelService = context.getBean(AddressLabelService.class);
        List<String> labels = Lists.newArrayList("ALL_ALL_ALL_ACTIVITY_L1");
        List<AddressLabel> list =  addressLabelService.findByLabels(labels, Page.builder().page(0).pageSize(10).build());
        System.out.println(list);
    }

}
