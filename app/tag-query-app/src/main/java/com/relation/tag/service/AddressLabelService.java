package com.relation.tag.service;

import com.relation.tag.entity.AddressLabel;
import com.relation.tag.entity.Labels;
import com.relation.tag.entity.Page;
import com.relation.tag.repository.AddressLabelRepository;
import com.relation.tag.request.GetAddressLabelRequest;
import com.relation.tag.request.GetAddressLabelsRequest;
import com.relation.tag.response.GetAddressLabelsResponse;
import com.relation.tag.vo.AddressInfo;
import com.relation.tag.vo.LabelInfo;
import com.relation.tag.vo.UserInfo;
import org.apache.lucene.search.join.ScoreMode;
import org.assertj.core.util.Lists;
import org.opensearch.data.client.orhlc.NativeSearchQueryBuilder;
import org.opensearch.index.query.BoolQueryBuilder;
import org.opensearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.opensearch.index.query.QueryBuilders.nestedQuery;
import static org.opensearch.index.query.QueryBuilders.termQuery;

@Service
public class AddressLabelService {
    @Autowired
    private AddressLabelRepository repository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;


    public List<GetAddressLabelsResponse> findByAddress(GetAddressLabelRequest request) {
        AddressLabel addressLabel = repository.findByAddress(request.getInput().getAddress());
        if (addressLabel == null) {
            return null;
        }

        UserInfo userInfo = null;//readOnlyUserInfoService.getByAddress(AddressLabelVO.getAddress());
        Integer count = null;//contractService.selectCountByContractAddress(AddressLabelVO.getAddress());
        boolean isContract = count > 0 ? true : false;
        List<String> labelNames = CollectionUtils.isEmpty(addressLabel.getLabels()) ? null : addressLabel.getLabels().stream().map(Labels::getName).collect(Collectors.toList());
//        List<Label> labelList = readOnlyLabelService.selectsByName(labelNames);
//        List<LabelInfo> labelInfos = labelList.stream().map(item -> LabelInfo.builder()
//                .content(item.getContent())
//                .name(item.getName())
//                .source(item.getSource())
//                .build()).collect(Collectors.toList());
//
//        GetAddressLabelsResponse getAddressLabelsResponse = GetAddressLabelsResponse.builder()
//                .address(AddressInfo.builder()
//                        .value(AddressLabelVO.getAddress())
//                        .isContract(isContract)
//                        .build())
//                .labels(labelInfos)
//                .build();
        return Lists.newArrayList(GetAddressLabelsResponse.builder()
                .userName(Objects.nonNull(userInfo)? userInfo.getName() :null)
                        .userIntroduction(Objects.nonNull(userInfo)? userInfo.getIntroduction() :null)
                        .userTwitter(Objects.nonNull(userInfo)? userInfo.getTwitter() :null)
                .build());
    }

    private GetAddressLabelsResponse buildGetAddressLabelsResponse(SearchHit<AddressLabel> item) {
        AddressLabel addressLabel = item.getContent();
        List<LabelInfo> labelInfos = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(addressLabel.getLabels())) {
            addressLabel.getLabels().forEach(label -> {
                labelInfos.add(LabelInfo.builder().name(label.getName()).build());
            });
        }
        return GetAddressLabelsResponse.builder().address(
                AddressInfo.builder().value(addressLabel.getAddress()).build()
        ).labels(labelInfos).build();
    }

    private void buildPage(Query searchQuery, Page page) {
        searchQuery.setPageable(PageRequest.of(page.getPage(), page.getPageSize(), Sort.by(Sort.Order.asc("address"))));
    }

    private Query getNestedQuery(List<String> labels) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        labels.forEach(item -> {
            queryBuilder.should(nestedQuery(
                    "labels",
                    termQuery("labels.name", item),
                    ScoreMode.None));
        });
        return new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();
    }


    public List<GetAddressLabelsResponse> getAddressLabels(GetAddressLabelsRequest request) {
        GetAddressLabelsRequest.Input input = request.getInput();
        Query searchQuery = getNestedQuery(input.getLabels());
        buildPage(searchQuery, Page.builder().page(input.getBaseId().intValue()).pageSize(input.getLimit()).build());
        SearchHits<AddressLabel> addressLabels = elasticsearchOperations.search(searchQuery, AddressLabel.class);
        List<GetAddressLabelsResponse> returnList = Lists.newArrayList();
        addressLabels.forEach(item -> {
            returnList.add(buildGetAddressLabelsResponse(item));
        });
        return returnList;
    }
}
