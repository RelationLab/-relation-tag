package com.relation.tag.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetAddressLabelsRequest {
    @JsonProperty("request_query")
    private String requestQuery;

    @JsonProperty("session_variables")
    private SessionVariables sessionVariables;

    private Input input;

    private Action action;

    @Data
    public static class Input {
        private String mode;

        @JsonProperty("base_id")
        private Long baseId;

        @JsonProperty("base_address")
        private String baseAddress;

        private List<String> labels;

        private Integer limit = 10;
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
