package com.money.h5.controller;

import com.money.custom.entity.request.QueryOrderPayRequest;
import com.money.custom.service.OrderPayService;
import com.money.h5.entity.H5GridRequestBase;
import com.money.h5.entity.response.QueryOrderPayResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "消费记录")
@RequestMapping(value = "consumption")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class ConsumptionController {

    @Autowired
    OrderPayService orderPayService;

    @ApiOperation(value = "查询消费记录列表（可分页）", notes = "openId = abc")
    @ResponseBody
    @RequestMapping(value = "queryConsumptionList", method = RequestMethod.POST)
    public QueryOrderPayResponse queryPayInfoList(@Valid @RequestBody H5GridRequestBase request, BindingResult bindingResult) {
        QueryOrderPayRequest queryOrderPayRequest = new QueryOrderPayRequest();
        queryOrderPayRequest.setOpenId(request.getOpenId());
        queryOrderPayRequest.copyPagerFromH5Request(request);
        int recordCount = this.orderPayService.selectSearchListCount(queryOrderPayRequest);
        return new QueryOrderPayResponse(recordCount, request.calTotalPage(recordCount), this.orderPayService.selectSearchList(queryOrderPayRequest));
    }

}
