package com.relation.tag.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAddressLabelRequest {
    @JsonProperty("request_query")
    private String requestQuery;

    @JsonProperty("session_variables")
    private SessionVariables sessionVariables;

    private AddressInput input;

    private Action action;

    @Data
    @Builder
    public static class AddressInput {
        private String address;
    }

    @Data
    public static class Action {

        @JsonProperty("session_variables")
        private String name;
    }

    @Data
    public static class SessionVariables {
        @JsonProperty("x-hasura-role")
        private String xHasuraRole;
    }
}
