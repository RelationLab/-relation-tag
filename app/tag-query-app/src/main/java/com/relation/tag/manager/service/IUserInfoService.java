package com.relation.tag.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.relation.tag.vo.UserInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author admin
 * @since 2022-05-30
 */
public interface IUserInfoService extends IService<UserInfo> {
    UserInfo getByAddress(String address);
}
