package com.relation.tag.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public enum DictKeyEnum {
    SQL_EXPLAIN_MAX_COST(Arrays.asList(DictGroupEnum.SYSTEM), "SQL_EXPLAIN_MAX_COST"),
    USER_VIP_DEPOSIT_ADDRESS(Arrays.asList(DictGroupEnum.SYSTEM), "USER_VIP_DEPOSIT_ADDRESS"),
    USER_VIP_DEPOSIT_CONTRACT_ADDRESS(Arrays.asList(DictGroupEnum.SYSTEM), "USER_VIP_DEPOSIT_CONTRACT_ADDRESS"),
    USER_VIP_DEPOSIT_CONTRACT_DECIMALS(Arrays.asList(DictGroupEnum.SYSTEM), "USER_VIP_DEPOSIT_CONTRACT_DECIMALS"),
    USER_VIP_KEY_COUNT(Arrays.asList(DictGroupEnum.BUSINESS), "USER_VIP_KEY_COUNT"),
    ;

    private List<DictGroupEnum> supportGroup;
    private String description;

    public boolean support(DictGroupEnum dictGroupEnum) {
        return supportGroup.contains(dictGroupEnum);
    }
}
