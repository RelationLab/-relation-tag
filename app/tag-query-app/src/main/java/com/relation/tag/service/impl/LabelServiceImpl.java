package com.relation.tag.service.impl;

import com.relation.tag.entity.postgresql.Label;
import com.relation.tag.mapper.primary.LabelMapper;
import com.relation.tag.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {
    @Autowired
    private LabelMapper labelMapper;

    @Override
    public List<Label> selectsByName(Collection<String> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return null;
        }
        return labelMapper.selectsByName(collection);
    }
}
