package com.relation.tag.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum DataSourceEnum {
    SYSTEM("", false, false),
    USER("", false, false),
    ETHERSCAN("etherscan_labels", true, false),
    DUNE("dune_labels", true, true)
    ;

    private String tableName;

    private Boolean needSyncData;

    private Boolean hasTime;

    public static List<DataSourceEnum> needSyncDatas() {
        return Arrays.stream(DataSourceEnum.values()).filter(DataSourceEnum::getNeedSyncData).collect(Collectors.toList());
    }
}
