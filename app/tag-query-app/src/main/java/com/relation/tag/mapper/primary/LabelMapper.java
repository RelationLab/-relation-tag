package com.relation.tag.mapper.primary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.relation.tag.entity.postgresql.Label;

import java.util.List;

/**
 * <p>
 * label table Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-03-30
 */
public interface LabelMapper extends BaseMapper<Label> {

    List<Label> selectsByName(List<String> list);

}
