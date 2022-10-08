package com.relation.tag.manager.service.readOnly;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.relation.tag.manager.service.IUserInfoService;
import com.relation.tag.mapper.readOnly.ReadOnlyUserInfoMapper;
import com.relation.tag.vo.UserInfo;
import org.springframework.boot.extension.annotation.Query;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-05-30
 */
@Service("readOnlyUserInfoService")
public class ReadOnlyUserInfoServiceImpl extends ServiceImpl<ReadOnlyUserInfoMapper, UserInfo> implements IUserInfoService {

    /**
     * can
     *
     * @param address
     * @return
     */
    @Override
    @Query
    public UserInfo getByAddress(String address) {
        return null;
    }
}
