package com.relation.tag.mapper.primary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.relation.tag.entity.postgresql.Contract;

import java.util.List;

public interface ContractMapper extends BaseMapper<Contract> {
    List<Contract> selectByContractAddresses(List<String> list);

    void exceSql(String exceSqlStr);
}
