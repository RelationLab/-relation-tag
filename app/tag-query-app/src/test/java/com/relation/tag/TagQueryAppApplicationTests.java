package com.relation.tag;

import com.relation.tag.request.GetAddressLabelRequest;
import com.relation.tag.request.GetAddressLabelsRequest;
import com.relation.tag.response.GetAddressLabelsResponse;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TagQueryAppApplicationTests {

    @Autowired
    private AddressLabelService addressLabelService;

    @Test
    void contextLoads() {
    }

    @Test
    void getAddressLabelsTest() {
        GetAddressLabelsRequest.Input input = GetAddressLabelsRequest.Input.builder().baseId(100L)
                .labels(Lists.newArrayList("ALL_ALL_ALL_ACTIVITY_L1")).limit(10).build();
        GetAddressLabelsRequest request = GetAddressLabelsRequest.builder().input(input).build();
        List<GetAddressLabelsResponse> getAddressLabels = addressLabelService.getAddressLabels(request);
        System.out.println();
    }

    @Test
    void getAddressCountTest() {
        GetAddressLabelsRequest.Input input = GetAddressLabelsRequest.Input.builder().baseId(100L)
                .labels(Lists.newArrayList("ALL_ALL_ALL_ACTIVITY_L1")).limit(10).build();
        GetAddressLabelsRequest request = GetAddressLabelsRequest.builder().input(input).build();
        Long getAddressCount = addressLabelService.getAddressCount(request);
        System.out.println();
    }

    @Test
    void findByAddressTest() {
        List<GetAddressLabelsResponse> findByAddress = addressLabelService.findByAddress(
                GetAddressLabelRequest.builder()
                .input(GetAddressLabelRequest.AddressInput.builder()
                        .address("0x0308472078b66bcf7f00f2e3d5a12e44f7ffb829")
                        .build()).build());
        System.out.println();
    }
}
