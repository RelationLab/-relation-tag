package com.relation.tag.controller;

import com.relation.tag.request.GetAddressLabelRequest;
import com.relation.tag.request.GetAddressLabelsRequest;
import com.relation.tag.response.GetAddressLabelsResponse;
import com.relation.tag.service.AddressLabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/${server.base-url}/public/v1")
@Validated
@Api("address label相关API")
public class PublicAddressLabelController {
    @Autowired
    private AddressLabelService addressLabelService;
    @PostMapping("address/labels")
    @ApiOperation("get address labels")
    public List<GetAddressLabelsResponse> getAddressLabels(@RequestBody GetAddressLabelsRequest request, HttpServletResponse response) {
        return addressLabelService.getAddressLabels(request);
    }

    @PostMapping("address/label")
    @ApiOperation("get address labels")
    public List<GetAddressLabelsResponse> getAddressLabel(@RequestBody GetAddressLabelRequest request) {
        return addressLabelService.findByAddress(request);
    }
}
