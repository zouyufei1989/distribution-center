package com.money.h5.controller;

import com.money.custom.entity.request.QueryOrderConsumptionRequest;
import com.money.custom.service.AssignActivityService;
import com.money.custom.service.OrderConsumptionService;
import com.money.h5.entity.H5GridRequestBase;
import com.money.h5.entity.response.QueryActivityResponse;
import com.money.h5.entity.response.QueryConsumptionResponse;
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
    OrderConsumptionService orderConsumptionService;

    @ApiOperation(value = "查询消费记录列表（可分页）", notes = "openId = abc")
    @ResponseBody
    @RequestMapping(value = "queryConsumptionList", method = RequestMethod.POST)
    public QueryConsumptionResponse queryActivityList(@Valid @RequestBody H5GridRequestBase request, BindingResult bindingResult) {
        QueryOrderConsumptionRequest queryOrderConsumptionRequest = new QueryOrderConsumptionRequest();
        queryOrderConsumptionRequest.setOpenId(request.getOpenId());
        queryOrderConsumptionRequest.copyPagerFromH5Request(request);
        int recordCount = this.orderConsumptionService.selectSearchListCount(queryOrderConsumptionRequest);
        return new QueryConsumptionResponse(recordCount, request.calTotalPage(recordCount), this.orderConsumptionService.selectSearchList(queryOrderConsumptionRequest));
    }

}
