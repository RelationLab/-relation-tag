package com.relation.tag.manager.service.readOnly;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.relation.tag.entity.Label;
import com.relation.tag.manager.service.ILabelService;
import com.relation.tag.mapper.readOnly.ReadOnlyLabelMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.extension.annotation.Query;
import org.springframework.boot.extension.cache.CacheService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

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
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

//    @Override
//    public List<String> selectExplain(String sql) {
//        return this.baseMapper.selectExplain(sql);
//    }
//
//    @Override
//    public List<Label> selectOrderRunOrder() {
//        return this.baseMapper.selectOrderRunOrder();
//    }
//
//    @Override
//    public List<String> selectAddressSql(String sql) {
//        return this.baseMapper.selectAddressSql(sql);
//    }
//
//    @Override
//    public Long selectSqlCount(String sql) {
//        return this.baseMapper.selectSqlCount(sql);
//    }
//
//    /**
//     * can not
//     *
//     * @param type
//     * @return
//     */
//    @Override
//    @Query
//    public Label selectByLabelType(String type) {
//        return null;
//    }
//
//    @Override
//    @Query
//    public Label selectByLabelName(String name) {
//        return null;
//    }
//
//    @Override
//    public Map<String, Object> selectMapSql(String sql) {
//        return this.baseMapper.selectMapSql(sql);
//    }
//
//    @Override
//    public List<Map<String, Object>> selectListMapSql(String sql) {
//        return this.baseMapper.selectListMapSql(sql);
//    }
//
//    @Override
//    public Boolean selectCondition(String condition) {
//        return this.baseMapper.selectCondition(condition);
//    }

//    /**
//     * can read only
//     * @param request
//     * @return
//     */
//    @Override
//    public PageResponse<Label> selectCollectorLabels(LabelCollectionsRequest request) {
//        return SqlUtil.execute(sqlSessionFactory, request, this.baseMapper);
//    }
//
//    @Override
//    @Query
//    public List<Label> selectOwnLabels(String owner) {
//        return null;
//    }
//
//    @Override
//    @Query
//    public List<Label> selectUserSaved(String owner, String ruleType) {return null;}
//
//    @Override
//    public PageResponse<Label> selectUserLabels(UserLabelsRequest userLabelsRequest) {
//        return SqlUtil.execute(sqlSessionFactory, userLabelsRequest, this.baseMapper);
//    }
//
//    @Override
//    public PageResponse<Label> selectLabelsBySource(GetLabelsRequest getLabelsRequest) {
//        return SqlUtil.execute(sqlSessionFactory, getLabelsRequest, this.baseMapper);
//    }
//
//    @Override
//    public List<Label> selectUserAllLabels(String owner, List<String> list) {
//        return this.baseMapper.selectUserAllLabels(owner, list);
//    }

    @Override
    public List<Label> selectsByName(List<String> list) {
        if (list==null|| CollectionUtils.isEmpty(list)){
            return null;
        }
        return this.baseMapper.selectsByName(list);
    }
//
//    @Override
//    @Query
//    public Integer selectCountByName(String name) {
//        return null;
//    }
//
//    @Override
//    @Query
//    public Integer selectCountBySource(String source) {
//        return null;
//    }
//
//    @Override
//    public Integer selectCountByOwner(String owner, List<String> list) {
//        return this.baseMapper.selectCountByOwner(owner, list);
//    }
//
//    @Override
//    public List<Label> selectPendingLabel() {
//        return this.baseMapper.selectPendingLabel();
//    }
//
//    @Override
//    public List<Label> getPopularLabel() {
//        return this.baseMapper.selectByMap(ImmutableMap.of("popular", true));
//    }
//
//    @Override
//    public List<String> selectMarkTypeBySource(String source) {
//        return this.baseMapper.selectMarkTypeBySource(source);
//    }

}
