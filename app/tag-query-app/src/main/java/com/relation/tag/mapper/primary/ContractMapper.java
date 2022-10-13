package com.relation.tag.mapper.primary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.relation.tag.entity.postgresql.Contract;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractMapper extends BaseMapper<Contract> {
    List<Contract> selectByContractAddresses(List<String> list);

    void exceSql(String exceSqlStr);
}
