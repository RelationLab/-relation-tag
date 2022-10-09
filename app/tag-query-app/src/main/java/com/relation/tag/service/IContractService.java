package com.relation.tag.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.relation.tag.entity.Contract;

import java.util.List;

/**
 * <p>
 * contract table 服务类
 * </p>
 *
 * @author admin
 * @since 2022-07-22
 */
public interface IContractService extends IService<Contract> {
    List<Contract> selectByContractAddresses(List<String> list);

    Contract selectByContractAddress(String contractAddress);

    Integer selectCountByContractAddress(String contractAddress);

}
