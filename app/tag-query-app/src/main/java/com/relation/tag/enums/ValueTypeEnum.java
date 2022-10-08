package com.relation.tag.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ValueTypeEnum {
    TOTAL("total:", true)
    , TOTAL_REDIS("redis:", true)
    , TOTAL_PARAM("param:", true)
    , REDIS("redis:", false)
    , PARAM("param:", false)
    , RESULT("result:", false);
    private String prefix;

    private Boolean total;

    public String parseParamName(String name) {
        return name.replace(this.prefix, "");
    }

    public static ValueTypeEnum valueOfName(String name) {
        return Arrays.stream(ValueTypeEnum.values())
                .filter(item -> name.startsWith(item.prefix)).findFirst().orElse(null);
    }
}
