package com.relation.tag.manager;

import com.relation.tag.request.GetAddressLabelRequest;
import com.relation.tag.request.GetAddressLabelsRequest;
import com.relation.tag.response.GetAddressLabelsResponse;

import java.util.List;

public interface AddressLabelManager {
    List<GetAddressLabelsResponse> findByAddress(GetAddressLabelRequest request);

    Long getAddressCount(GetAddressLabelsRequest request);

    List<GetAddressLabelsResponse> getAddressLabels(GetAddressLabelsRequest request);
}
