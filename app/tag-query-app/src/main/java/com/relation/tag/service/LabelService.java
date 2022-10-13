package com.relation.tag.service;

import com.relation.tag.entity.postgresql.Label;

import java.util.Collection;
import java.util.List;

public interface LabelService {
    List<Label> selectsByName(Collection<String> collection);
}
