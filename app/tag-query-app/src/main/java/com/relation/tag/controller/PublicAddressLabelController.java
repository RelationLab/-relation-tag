package com.relation.tag.controller;

import com.relation.tag.manager.AddressLabelManager;
import com.relation.tag.request.GetAddressLabelRequest;
import com.relation.tag.request.GetAddressLabelsRequest;
import com.relation.tag.response.GetAddressLabelsCountResponse;
import com.relation.tag.response.GetAddressLabelsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.extension.annotation.MethodDesc;
import org.springframework.boot.extension.entity.response.ResponseWrapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author wilson
 */
@RestController
@RequestMapping("/${server.base-url}/public/v1")
@Validated
@Api("address label相关API")
public class PublicAddressLabelController {
    @Autowired
    private AddressLabelManager addressLabelManager;

    @PostMapping("address/labels")
    @ApiOperation("get address labels")
    @MethodDesc("get address labels")
    public List<GetAddressLabelsResponse> getAddressLabels(@RequestBody GetAddressLabelsRequest request, HttpServletResponse response) {
        return addressLabelManager.getAddressLabels(request, response);
    }

    @ApiOperation("get address labels count")
    @MethodDesc("get address labels count")
    @RequestMapping("address/labels/count")
    public GetAddressLabelsCountResponse addressCount(@RequestBody GetAddressLabelsRequest request) {
        return GetAddressLabelsCountResponse.builder().count(addressLabelManager.getAddressCount(request)).build();
    }

    @PostMapping("address/label")
    @ApiOperation("get address labels")
    @MethodDesc("get address labels")
    public List<GetAddressLabelsResponse> getAddressLabel(@RequestBody GetAddressLabelRequest request) {
        return addressLabelManager.getAddressLabel(request);
    }
}
