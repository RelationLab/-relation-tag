package com.relation.tag;

import com.relation.tag.request.GetAddressLabelsRequest;
import com.relation.tag.service.AddressLabelService;
import org.assertj.core.util.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.relation.tag"}, exclude = {ElasticsearchDataAutoConfiguration.class})
public class TagQueryApplication {
    org.springframework.core.io.support.SpringFactoriesLoader SpringFactoriesLoader;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TagQueryApplication.class, args);
        AddressLabelService addressLabelService = context.getBean(AddressLabelService.class);
//        List<GetAddressLabelsResponse> list =  addressLabelService.findByAddress(GetAddressLabelRequest.builder()
//                .input(GetAddressLabelRequest.AddressInput.builder().address("0x0308472078b66bcf7f00f2e3d5a12e44f7ffb829")
//                        .build()).build());

        GetAddressLabelsRequest.Input input = GetAddressLabelsRequest.Input.builder().baseId(100L)
                .labels(Lists.newArrayList("ALL_ALL_ALL_ACTIVITY_L1")).limit(10).build();
        GetAddressLabelsRequest request = GetAddressLabelsRequest.builder().input(input).build();
        Long count = addressLabelService.getAddressCount(request);

//        List<GetAddressLabelsResponse> list =  addressLabelService.getAddressLabels(request);
        System.out.println();
    }

}
