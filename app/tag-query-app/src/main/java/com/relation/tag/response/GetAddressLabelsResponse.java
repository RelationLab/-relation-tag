package com.relation.tag.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.relation.tag.vo.AddressInfo;
import com.relation.tag.vo.LabelInfo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAddressLabelsResponse {

    private AddressInfo address;

    private List<LabelInfo> labels;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_introduction")
    private String userIntroduction;

    @JsonProperty("user_twitter")
    private String userTwitter;

}
