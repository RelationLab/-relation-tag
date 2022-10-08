package com.relation.tag.mapper.readOnly;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.relation.tag.entity.Label;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.extension.mapper.PostgresPageMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * label table Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-03-30
 */
public interface ReadOnlyLabelMapper extends BaseMapper<Label>, PostgresPageMapper<Label> {

    List<Label> selectsByName(List<String> list);
}
