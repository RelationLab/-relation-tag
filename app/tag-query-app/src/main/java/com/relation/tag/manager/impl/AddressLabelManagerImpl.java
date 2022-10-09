package com.relation.tag.manager.impl;

import com.relation.tag.entity.Contract;
import com.relation.tag.entity.Label;
import com.relation.tag.enums.GetModeEnum;
import com.relation.tag.enums.ResponseCodeEnum;
import com.relation.tag.manager.AddressLabelManager;
import com.relation.tag.service.IAddressLabelGpService;
import com.relation.tag.service.IContractService;
import com.relation.tag.service.ILabelService;
import com.relation.tag.service.IUserInfoService;
import com.relation.tag.request.GetAddressLabelRequest;
import com.relation.tag.request.GetAddressLabelsRequest;
import com.relation.tag.response.GetAddressLabelsResponse;
import com.relation.tag.vo.AddressInfo;
import com.relation.tag.vo.AddressLabelVO;
import com.relation.tag.vo.LabelInfo;
import com.relation.tag.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.extension.util.PostgresUtil;
import org.springframework.boot.extension.util.VerifyBusinessUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AddressLabelManagerImpl implements AddressLabelManager {
    @Autowired
    @Qualifier("greenPlumAddressLabelGpServiceImpl")
    private IAddressLabelGpService addressLabelGpService;

    @Autowired
    @Qualifier("readOnlyLabelService")
    private ILabelService readOnlyLabelService;

    @Autowired
    @Qualifier("readOnlyUserInfoService")
    private IUserInfoService readOnlyUserInfoService;

    @Autowired
    private IContractService contractService;

    @Override
    public List<GetAddressLabelsResponse> getAddressLabels(GetAddressLabelsRequest request, HttpServletResponse response) {
        List<Label> labels = readOnlyLabelService.selectsByName(request.getInput().getLabels());
        VerifyBusinessUtil.equals(labels.size(), request.getInput().getLabels().size(), ResponseCodeEnum.ERROR_NOT_FOUNT_LABEL);

        Set<String> labelTypes = labels.stream().map(Label::getType).collect(Collectors.toSet());
        if (labelTypes.size() < labels.size()
                && GetModeEnum.valueOf(request.getInput().getMode()) == GetModeEnum.PRECISION) {
            return new ArrayList<>();
        }
        List<AddressLabelVO> addressList = addressLabelGpService.findLabelsAddress(labels, request.getInput().getBaseId(), request.getInput().getLimit());
        if (CollectionUtils.isEmpty(addressList)) {
            return new ArrayList<>();
        }
        List<String> listAddressStr = addressList.stream().map(AddressLabelVO::getAddress).collect(Collectors.toList());
        List<AddressLabelVO> AddressLabelVOs = addressLabelGpService.findLabelsByAddress(listAddressStr);
        List<Contract> contracts = contractService.selectByContractAddresses(AddressLabelVOs.stream().map(AddressLabelVO::getAddress).collect(Collectors.toList()));
        Map<String, Contract> contractMapTemp = contracts.stream().collect(Collectors.toMap(Contract::getContractAddress, Function.identity()));
        if (Objects.isNull(contractMapTemp)) {
            contractMapTemp = new HashMap<>();
        }
        Map<String, Contract> contractMap = contractMapTemp;
        return AddressLabelVOs.stream().map(AddressLabelVO -> {
            UserInfo userInfo = readOnlyUserInfoService.getByAddress(AddressLabelVO.getAddress());
            Contract contract = contractMap.get(AddressLabelVO.getAddress());
            boolean isContract = Objects.nonNull(contract);
            List<String> labelNames = PostgresUtil.parseArray(AddressLabelVO.getLabels());
            List<Label> labelList = readOnlyLabelService.selectsByName(labelNames);
            List<LabelInfo> labelInfos = labelList.stream().map(item -> LabelInfo.builder()
                    .content(item.getContent())
                    .name(item.getName())
                    .source(item.getSource())
                    .build()).collect(Collectors.toList());

            GetAddressLabelsResponse getAddressLabelsResponse = GetAddressLabelsResponse.builder()
                    .address(AddressInfo.builder()
                            .value(AddressLabelVO.getAddress())
                            .isContract(isContract)
                            .build())
                    .labels(labelInfos)
                    .build();

            if (Objects.nonNull(userInfo)) {
                getAddressLabelsResponse.setUserName(userInfo.getName());
                getAddressLabelsResponse.setUserIntroduction(userInfo.getIntroduction());
                getAddressLabelsResponse.setUserTwitter(userInfo.getTwitter());
            }

            return getAddressLabelsResponse;
        }).collect(Collectors.toList());

    }

    @Override
    public List<GetAddressLabelsResponse> getAddressLabel(GetAddressLabelRequest request) {
        VerifyBusinessUtil.notBlank(request.getInput().getAddress(), ResponseCodeEnum.ERROR_ADDRESS_NOT_NULL);
        List<AddressLabelVO> AddressLabelVOs = addressLabelGpService.findLabelsByAddress(Arrays.asList(request.getInput().getAddress()));

        if (CollectionUtils.isEmpty(AddressLabelVOs)) {
            return new ArrayList<>();
        }

        return AddressLabelVOs.stream().map(AddressLabelVO -> {
            UserInfo userInfo = readOnlyUserInfoService.getByAddress(AddressLabelVO.getAddress());
            Integer count = contractService.selectCountByContractAddress(AddressLabelVO.getAddress());
            boolean isContract = count > 0 ? true : false;
            List<String> labelNames = PostgresUtil.parseArray(AddressLabelVO.getLabels());
            List<Label> labelList = readOnlyLabelService.selectsByName(labelNames);
            List<LabelInfo> labelInfos = labelList.stream().map(item -> LabelInfo.builder()
                    .content(item.getContent())
                    .name(item.getName())
                    .source(item.getSource())
                    .build()).collect(Collectors.toList());

            GetAddressLabelsResponse getAddressLabelsResponse = GetAddressLabelsResponse.builder()
                    .address(AddressInfo.builder()
                            .value(AddressLabelVO.getAddress())
                            .isContract(isContract)
                            .build())
                    .labels(labelInfos)
                    .build();
            if (Objects.nonNull(userInfo)) {
                getAddressLabelsResponse.setUserName(userInfo.getName());
                getAddressLabelsResponse.setUserIntroduction(userInfo.getIntroduction());
                getAddressLabelsResponse.setUserTwitter(userInfo.getTwitter());
            }

            return getAddressLabelsResponse;
        }).collect(Collectors.toList());
    }


    @Override
    public Long getAddressCount(GetAddressLabelsRequest request) {
        if (CollectionUtils.isEmpty(request.getInput().getLabels())) {
            return 0L;
        }
        List<Label> labels = readOnlyLabelService.selectsByName(request.getInput().getLabels());
        if (labels == null || CollectionUtils.isEmpty(labels)) {
            return 0L;
        }
        Set<String> labelTypes = labels.stream().map(Label::getType).collect(Collectors.toSet());
        if (labelTypes.size() < labels.size()
                && GetModeEnum.valueOf(request.getInput().getMode()) == GetModeEnum.PRECISION) {
            return 0L;
        }
        return addressLabelGpService.findLabelsAddressCount(labels);

//        boolean sourceSYSTEMFlag = false;
//        for (Label label : labels) {
//            if (StringUtils.equals(label.getSource(), DataSourceEnum.SYSTEM.name())) {
//                sourceSYSTEMFlag = true;
//                break;
//            }
//        }
//        if (!sourceSYSTEMFlag) {
//            if (GetModeEnum.valueOf(request.getInput().getMode()) == GetModeEnum.PRECISION) {
//                return readOnlyAddressLabelService.selectContainLabelNamesCount(labels
//                        , request.getInput().getBaseAddress(), request.getInput().getLimit(), request.getInput().getLabels().size());
//            } else {
//                return readOnlyAddressLabelService.selectContainOrLabelNamesCount(labels
//                        , request.getInput().getBaseAddress(), request.getInput().getLimit(), request.getInput().getLabels().size());
//            }
//        }
//
//        if (GetModeEnum.valueOf(request.getInput().getMode()) == GetModeEnum.PRECISION) {
//            Long count = readOnlyAddressLabelService.selectContainLabelNamesCount(labels
//                    , request.getInput().getBaseAddress(), request.getInput().getLimit(), request.getInput().getLabels().size());
//            if (count != null && count.longValue() >= 10000 && request.getInput().getLabels().size() == 1) {
//                return readOnlyAddressLabelService.selectOrLabelNamesCount(labels, request.getInput().getBaseAddress(), request.getInput().getLimit());
//            }
//            return count;
//        }
//        Long count = readOnlyAddressLabelService.selectOrLabelNamesCount(labels, request.getInput().getBaseAddress(), request.getInput().getLimit());
//        if (count != null && count.longValue() < 10000) {
//            if (request.getInput().getLabels().size() == 1) {
//                return readOnlyAddressLabelService.selectContainLabelNamesCount(labels
//                        , request.getInput().getBaseAddress(), request.getInput().getLimit(), request.getInput().getLabels().size());
//            }
//            return readOnlyAddressLabelService.selectContainLabelNamesOrCount(labels
//                    , request.getInput().getBaseAddress(), request.getInput().getLimit(), request.getInput().getLabels().size());
//        }
//        return count;
    }

    @Override
    public void jsonGin() {
        log.info("jsonGin start .....");
        long statTime = System.currentTimeMillis();
        String sql = "insert\n" +
                "\tinto\n" +
                "\taddress_labels_json_gin(address,\n" +
                "\tlabels,\n" +
                "\tupdated_at)\n" +
                "   select\n" +
                "\taddress,\n" +
                "\tjsonb_object_agg(label_type, label_name order by label_type desc) as labels,\n" +
                "\tCURRENT_TIMESTAMP as updated_at\n" +
                "from\n" +
                "\taddress_label_gp \n" +
                "group by\n" +
                "\taddress;";
        contractService.exceSql(sql);
        log.info("jsonGin end .....time===={}",System.currentTimeMillis()-statTime);
    }
}
