package com.relation.tag.service.readOnly;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.relation.tag.entity.Label;
import com.relation.tag.mapper.readOnly.ReadOnlyLabelMapper;
import com.relation.tag.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.extension.cache.CacheService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * label table 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-03-30
 */
@Service("readOnlyLabelService")
public class ReadOnlyLabelServiceImpl extends ServiceImpl<ReadOnlyLabelMapper, Label> implements ILabelService {
    @Autowired
    CacheService cacheService;

    @Override
    public List<Label> selectsByName(List<String> list) {
        if (list == null || CollectionUtils.isEmpty(list)) {
            return null;
        }
        return this.baseMapper.selectsByName(list);
    }

}
