package com.relation.tag.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressInfo {
    private String value;

    @JsonProperty("is_contract")
    private Boolean isContract;
}
