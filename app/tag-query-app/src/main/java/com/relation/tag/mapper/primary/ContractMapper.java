package com.relation.tag.mapper.primary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.relation.tag.entity.Contract;

import java.util.List;

/**
 * <p>
 * contract table Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-07-22
 */
public interface ContractMapper extends BaseMapper<Contract> {
    List<Contract> selectByContractAddresses(List<String> list);

    void exceSql(String exceSqlStr);
}
