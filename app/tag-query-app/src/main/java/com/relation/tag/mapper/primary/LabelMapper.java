package com.relation.tag.mapper.primary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.relation.tag.entity.postgresql.Label;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * label table Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-03-30
 */
@Repository
public interface LabelMapper extends BaseMapper<Label> {

    List<Label> selectsByName(Collection<String> collection);

}
