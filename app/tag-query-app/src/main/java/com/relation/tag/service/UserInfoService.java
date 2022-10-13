package com.relation.tag.service;

import com.relation.tag.entity.postgresql.UserInfo;

import java.util.List;

public interface UserInfoService {
    List<UserInfo> getByAddress(List<String> addressList);
}
