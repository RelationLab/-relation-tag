package com.relation.tag;

import com.relation.tag.request.GetAddressLabelsRequest;
import com.relation.tag.response.GetAddressLabelsResponse;
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
        GetAddressLabelsRequest.Input input = GetAddressLabelsRequest.Input.builder().baseId(100L)
                .labels( Lists.newArrayList("ALL_ALL_ALL_ACTIVITY_L1")).limit(10).build();
        GetAddressLabelsRequest request = GetAddressLabelsRequest.builder().input(input).build();
        List<GetAddressLabelsResponse> list =  addressLabelService.getAddressLabels(request);
        System.out.println(list);
    }

}
