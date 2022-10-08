package com.relation.tag.manager;

import com.relation.tag.request.GetAddressLabelRequest;
import com.relation.tag.request.GetAddressLabelsRequest;
import com.relation.tag.response.GetAddressLabelsResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AddressLabelManager {
    public List<GetAddressLabelsResponse> getAddressLabels(GetAddressLabelsRequest request, HttpServletResponse response);

    List<GetAddressLabelsResponse> getAddressLabel(GetAddressLabelRequest request);

    Long getAddressCount(GetAddressLabelsRequest request);
}
