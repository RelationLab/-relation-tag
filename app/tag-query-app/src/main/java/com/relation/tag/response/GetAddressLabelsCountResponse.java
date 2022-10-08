package com.relation.tag.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAddressLabelsCountResponse {
    private Long count;
}
