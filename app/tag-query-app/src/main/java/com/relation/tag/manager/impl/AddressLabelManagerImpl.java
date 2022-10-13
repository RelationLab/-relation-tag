package com.relation.tag.manager.impl;

import com.relation.tag.entity.opensearch.AddressLabel;
import com.relation.tag.entity.postgresql.Contract;
import com.relation.tag.entity.postgresql.Label;
import com.relation.tag.entity.postgresql.UserInfo;
import com.relation.tag.enums.GetModeEnum;
import com.relation.tag.enums.ResponseCodeEnum;
import com.relation.tag.mapper.primary.ContractMapper;
import com.relation.tag.mapper.primary.LabelMapper;
import com.relation.tag.mapper.primary.UserInfoMapper;
import com.relation.tag.repository.AddressLabelRepository;
import com.relation.tag.request.GetAddressLabelRequest;
import com.relation.tag.request.GetAddressLabelsRequest;
import com.relation.tag.request.Page;
import com.relation.tag.response.GetAddressLabelsResponse;
import com.relation.tag.manager.AddressLabelManager;
import com.relation.tag.service.ContractService;
import com.relation.tag.service.LabelService;
import com.relation.tag.service.UserInfoService;
import com.relation.tag.vo.AddressInfo;
import com.relation.tag.vo.LabelInfo;
import com.relation.tag.vo.Labels;
import org.apache.lucene.search.join.ScoreMode;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
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

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.opensearch.index.query.QueryBuilders.nestedQuery;
import static org.opensearch.index.query.QueryBuilders.termQuery;

@Service
public class AddressLabelManagerImpl implements AddressLabelManager {
    @Autowired
    private AddressLabelRepository repository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private LabelService labelService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private UserInfoService userInfoService;


    @Override
    public List<GetAddressLabelsResponse> findByAddress(GetAddressLabelRequest request) {
        AddressLabel addressLabel = repository.findByAddress(request.getInput().getAddress());
        if (addressLabel == null) {
            return null;
        }
        List<UserInfo> userInfos = userInfoService.getByAddress(Lists.newArrayList(addressLabel.getAddress()));
        UserInfo userInfo = CollectionUtils.isEmpty(userInfos) ? null : userInfos.get(0);
        Integer count = contractService.selectCountByContractAddress(addressLabel.getAddress());
        boolean isContract = count > 0 ? true : false;
        List<String> labelNames = CollectionUtils.isEmpty(addressLabel.getLabels()) ? null : addressLabel.getLabels().stream().map(Labels::getName).collect(Collectors.toList());
        List<Label> labelList = labelService.selectsByName(labelNames);
        List<LabelInfo> labelInfos = labelList.stream().map(item -> LabelInfo.builder()
                .content(item.getContent())
                .name(item.getName())
                .source(item.getSource())
                .build()).collect(Collectors.toList());
        return Lists.newArrayList(GetAddressLabelsResponse.builder()
                .userName(Objects.nonNull(userInfo) ? userInfo.getName() : null)
                .userIntroduction(Objects.nonNull(userInfo) ? userInfo.getIntroduction() : null)
                .userTwitter(Objects.nonNull(userInfo) ? userInfo.getTwitter() : null)
                .address(AddressInfo.builder()
                        .value(addressLabel.getAddress())
                        .isContract(isContract)
                        .build())
                .labels(labelInfos)
                .build());
    }


    private void buildPage(Query searchQuery, Page page) {
        searchQuery.setPageable(PageRequest.of(page.getPage(), page.getPageSize(), Sort.by(Sort.Order.asc("address"))));
    }

    private Query getNestedQuery(List<String> labels, String mode) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        labels.forEach(item -> {
            if (GetModeEnum.valueOf(mode) == GetModeEnum.PRECISION) {
                queryBuilder.must(nestedQuery(
                        "labels",
                        termQuery("labels.name", item),
                        ScoreMode.None));
            } else {
                queryBuilder.should(nestedQuery(
                        "labels",
                        termQuery("labels.name", item),
                        ScoreMode.None));
            }
        });
        return new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();
    }

    private GetAddressLabelsResponse buildGetAddressLabelsResponse(AddressLabel addressLabel, Map<String, Contract> contractMap, Map<String, Label> labelNamesMap, Map<String, UserInfo> userInfoMap) {
        List<LabelInfo> labelInfos = Lists.newArrayList();
        buildLabelInfos(labelInfos, addressLabel, labelNamesMap);
        String address = addressLabel.getAddress();
        UserInfo userInfo = userInfoMap.get(address);
        Contract contract = contractMap.get(address);
        boolean isContract = Objects.nonNull(contract);
        return GetAddressLabelsResponse.builder()
                .address(AddressInfo.builder()
                        .value(address)
                        .isContract(isContract)
                        .build())
                .labels(labelInfos)
                .userTwitter(Objects.nonNull(userInfo) ? userInfo.getTwitter() : null)
                .userIntroduction(Objects.nonNull(userInfo) ? userInfo.getIntroduction() : null)
                .userName(Objects.nonNull(userInfo) ? userInfo.getName() : null)
                .build();
    }

    private void buildLabelInfos(List<LabelInfo> labelInfos, AddressLabel addressLabel, Map<String, Label> labelNamesMap) {
        if (CollectionUtils.isEmpty(addressLabel.getLabels())) {
            return;
        }
        addressLabel.getLabels().forEach(label -> {
            String labelName = label.getName();
            Label labelItem = labelNamesMap.get(labelName);
            labelInfos.add(LabelInfo.builder()
                    .name(labelName)
                    .content(labelItem != null ? labelItem.getContent() : null)
                    .source(labelItem != null ? labelItem.getSource() : null)
                    .build());
        });
    }

    @Override
    public List<GetAddressLabelsResponse> getAddressLabels(GetAddressLabelsRequest request) {
        GetAddressLabelsRequest.Input input = request.getInput();
        List<String> inputLabels = input.getLabels();
        List<Label> labels = labelService.selectsByName(inputLabels);
        if (CollectionUtils.isEmpty(labels) || labels.size() != inputLabels.size()) {
            throw new RuntimeException(ResponseCodeEnum.ERROR_NOT_FOUNT_LABEL.errorMessage(), null);
        }
        Set<String> labelTypes = labels.stream().map(Label::getType).collect(Collectors.toSet());
        if (labelTypes.size() < labels.size() && GetModeEnum.valueOf(input.getMode()) == GetModeEnum.PRECISION) {
            return new ArrayList<>();
        }
        Query searchQuery = getNestedQuery(input.getLabels(), input.getMode());
        List<AddressLabel> addressLabels = findByLabels(input, searchQuery);
        List<String> addressList = addressLabels.stream().map(AddressLabel::getAddress).collect(Collectors.toList());
        List<Contract> contracts = contractService.selectByContractAddresses(addressList);
        Map<String, Contract> contractMap = contracts.stream().collect(Collectors.toMap(Contract::getContractAddress, Function.identity()));
        Set<String> labelNames = Sets.newHashSet();
        addressLabels.forEach(item -> {
            labelNames.addAll(item.getLabels().stream().map(Labels::getName).collect(Collectors.toSet()));
        });
        List<Label> labelList = labelService.selectsByName(labelNames);
        Map<String, Label> labelNamesMap = labelList.stream().collect(Collectors.toMap(Label::getName, Function.identity()));
        List<UserInfo> userInfos = userInfoService.getByAddress(addressList);
        Map<String, UserInfo> userInfoMap = userInfos.stream().collect(Collectors.toMap(UserInfo::getAddress, Function.identity()));

        return addressLabels.stream().map(item -> {
            return buildGetAddressLabelsResponse(item, contractMap, labelNamesMap, userInfoMap);
        }).collect(Collectors.toList());
    }

    private List<AddressLabel> findByLabels(GetAddressLabelsRequest.Input input, Query searchQuery) {
        buildPage(searchQuery, Page.builder().page(input.getBaseId().intValue()).pageSize(input.getLimit()).build());
        SearchHits<AddressLabel> addressLabels = elasticsearchOperations.search(searchQuery, AddressLabel.class);
        if (addressLabels == null) {
            return new ArrayList<>();
        }
        return addressLabels.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    @Override
    public Long getAddressCount(GetAddressLabelsRequest request) {
        GetAddressLabelsRequest.Input input = request.getInput();
        List<String> labelNames = input.getLabels();
        if (CollectionUtils.isEmpty(labelNames)) {
            return 0L;
        }
        List<Label> labels = labelService.selectsByName(labelNames);
        if (labels == null || CollectionUtils.isEmpty(labels)) {
            return 0L;
        }
        Set<String> labelTypes = labels.stream().map(Label::getType).collect(Collectors.toSet());
        if (labelTypes.size() < labels.size()
                && GetModeEnum.valueOf(request.getInput().getMode()) == GetModeEnum.PRECISION) {
            return 0L;
        }
        Query searchQuery = getNestedQuery(labelNames, input.getMode());
        return elasticsearchOperations.count(searchQuery, AddressLabel.class);
    }
}
