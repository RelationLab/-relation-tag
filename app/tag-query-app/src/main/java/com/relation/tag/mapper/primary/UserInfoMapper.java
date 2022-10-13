package com.relation.tag.mapper.primary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.relation.tag.entity.postgresql.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-05-30
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    List<UserInfo>  getByAddress(List<String> addressList);
}
