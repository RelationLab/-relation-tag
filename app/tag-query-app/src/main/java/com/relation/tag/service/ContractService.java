package com.relation.tag.service;

import com.relation.tag.entity.postgresql.Contract;

import java.util.List;

public interface ContractService {
    List<Contract> selectByContractAddresses(List<String> list);

    Integer selectCountByContractAddress(String address);
}
