package com.relation.tag.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.relation.tag.entity.Contract;
import com.relation.tag.service.IContractService;
import com.relation.tag.mapper.primary.ContractMapper;
import org.springframework.boot.extension.annotation.DataCache;
import org.springframework.boot.extension.annotation.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * contract table 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-07-22
 */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements IContractService {

    @Override
    public List<Contract> selectByContractAddresses(List<String> list) {
        return this.baseMapper.selectByContractAddresses(list);
    }

    @Override
    @Query
    @DataCache(key = "ContractServiceImpl.selectByContractAddress.{contractAddress}")
    public Contract selectByContractAddress(String contractAddress) {
        return null;
    }

    @Override
    @Query
    public Integer selectCountByContractAddress(String contractAddress) {
        return null;
    }

    @Override
    public void exceSql(String sql) {
        this.baseMapper.exceSql(sql);
    }
}
