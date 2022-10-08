package com.relation.tag.manager.service.greenplum;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.relation.tag.entity.AddressLabelGp;
import com.relation.tag.entity.Label;
import com.relation.tag.mapper.greenplum.GreenplumAddressLabelGpMapper;
import com.relation.tag.manager.service.IAddressLabelGpService;
import com.relation.tag.vo.AddressLabelVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("greenPlumAddressLabelGpServiceImpl")
public class GreenPlumAddressLabelGpServiceImpl extends ServiceImpl<GreenplumAddressLabelGpMapper, AddressLabelGp> implements IAddressLabelGpService {


    @Override
    public List<AddressLabelVO> findLabelsAddress(List<Label> list, Long pageNo, Integer limit) {
        Long offSet = (pageNo - 1) * limit;
        return baseMapper.findLabelsAddress(list, limit, offSet);
    }

    @Override
    public Long findLabelsAddressCount(List<Label> labels) {
        return baseMapper.findLabelsAddressCount(labels);
    }

    @Override
    public List<AddressLabelVO> findLabelsByAddress(List<String> addressList) {
        return baseMapper.findLabelsByAddress(addressList);
    }
}
