package com.relation.tag;

import com.relation.tag.opensearch.service.AddressLabelService;
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
//        AddressLabel addressLabel = addressLabelService.findByAddress("0x49710473f4efa926e19436d0d475d4cde44b4b41");
        List<String> labels = Lists.newArrayList("ALL_ALL_ALL_ACTIVITY_L1");
        addressLabelService.findByLabels(labels);
        System.out.println();
    }

}
