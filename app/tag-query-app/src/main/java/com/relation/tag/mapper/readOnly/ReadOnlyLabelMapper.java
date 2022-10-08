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


    List<String> selectExplain(@Param("sql") String sql);

    List<Label> selectOrderRunOrder();

    List<String> selectAddressSql(@Param("sql") String sql);

    Long selectSqlCount(@Param("sql") String sql);

    Map<String, Object> selectMapSql(@Param("sql") String sql);

    List<Map<String, Object>> selectListMapSql(@Param("sql") String sql);

    Boolean selectCondition(@Param("condition") String condition);

    List<Label> selectOwnLabels(String owner);

    List<Label> selectUserAllLabels(@Param("owner") String owner, @Param("list") List<String> list);

    int selectCountByOwner(@Param("owner") String owner, @Param("list") List<String> list);

    List<Label> selectsByName(List<String> list);

    List<Label> selectPendingLabel();

    List<String> selectMarkTypeBySource(String source);
}
