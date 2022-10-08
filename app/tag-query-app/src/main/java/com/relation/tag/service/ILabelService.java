package com.relation.tag.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.relation.tag.entity.Label;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * label table 服务类
 * </p>
 *
 * @author admin
 * @since 2022-03-30
 */
public interface ILabelService extends IService<Label> {
//    List<String> selectExplain(String sql);
//
//    List<Label> selectOrderRunOrder();
//
//    List<String> selectAddressSql(String sql);
//
//    Long selectSqlCount(String sql);
//
//    Label selectByLabelType(String type);
//
//    Label selectByLabelName(String name);
//
//    Map<String, Object> selectMapSql(String sql);
//
//    List<Map<String, Object>> selectListMapSql(String sql);
//
//    Boolean selectCondition(String condition);

//    PageResponse<Label> selectCollectorLabels(LabelCollectionsRequest request);
//
//    List<Label> selectOwnLabels(String owner);
//
//    List<Label> selectUserSaved(String owner, String ruleType);
//
//    PageResponse<Label> selectUserLabels(UserLabelsRequest userLabelsRequest);
//
//    PageResponse<Label> selectLabelsBySource(GetLabelsRequest getLabelsRequest);
//
//    default Boolean deleteByIdAndOwner(Long id, String owner) {
//        return false;
//    }
//
//    ;
//
//    default int updateSqlLabel(UpdateSqlRequest updateSqlRequest) {
//        return 0;
//    }
//
//    ;
//
//    List<Label> selectUserAllLabels(String owner, List<String> list);
//
    List<Label> selectsByName(List<String> list);
//
//    Integer selectCountByName(String name);
//
//    default int insertUpdate(Label label) {
//        return 0;
//    }
//
//    ;
//
//    Integer selectCountBySource(String source);
//
//    Integer selectCountByOwner(String owner, List<String> list);
//
//    List<Label> selectPendingLabel();
//
//    List<Label> getPopularLabel();
//
//    default void updateLabelRefreshTime(List<Label> labels) {
//    }
//
//    ;
//
//    default List<String> selectMarkTypeBySource(String source) {
//        return null;
//    }
//
//    ;
//
//    default List<Label> selectInLabelTypes(List<String> lableTypes) {
//        return null;
//    }
//
//    default List<Label> listByMapPaging(ImmutableMap<String, ? extends Serializable> of) {
//        return null;
//    }
//
//    default List<Label> listWeb3() {
//        return null;
//    }
//
//    default void updateLabelRefreshTimeByTypes(List<String> lableTypes) {
//
//    }
//
//    default List<Label> listLikeTokenLabel(List<String> nftList) {
//        return null;
//    }
//
//    default List<Label> listLikeNftLabel(List<String> nftList0) {
//        return null;
//    }
//
//    default List<Label> selectArPendings(Integer limit) {
//        return null;
//    }

}
