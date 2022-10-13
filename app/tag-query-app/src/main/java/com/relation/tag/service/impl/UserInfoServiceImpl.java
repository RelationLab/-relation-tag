package com.relation.tag.service.impl;

import com.relation.tag.entity.postgresql.UserInfo;
import com.relation.tag.mapper.primary.UserInfoMapper;
import com.relation.tag.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> getByAddress(List<String> addressList) {
        if (CollectionUtils.isEmpty(addressList)) {
            return null;
        }
        return userInfoMapper.getByAddress(addressList);
    }
}
