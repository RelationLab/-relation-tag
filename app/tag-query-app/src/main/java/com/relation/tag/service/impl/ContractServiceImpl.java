package com.relation.tag.service.impl;

import com.relation.tag.entity.postgresql.Contract;
import com.relation.tag.mapper.primary.ContractMapper;
import com.relation.tag.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractMapper contractMapper;

    @Override
    public List<Contract> selectByContractAddresses(List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return contractMapper.selectByContractAddresses(list);
    }

    @Override
    public Integer selectCountByContractAddress(String address) {
        return contractMapper.selectCountByContractAddress(address);
    }
}
