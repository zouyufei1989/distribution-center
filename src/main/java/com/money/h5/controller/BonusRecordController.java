package com.money.h5.controller;

import com.money.custom.entity.request.QueryBonusWalletDetailRequest;
import com.money.custom.entity.request.QueryOrderConsumptionRequest;
import com.money.custom.service.BonusWalletService;
import com.money.custom.service.OrderConsumptionService;
import com.money.h5.entity.H5GridRequestBase;
import com.money.h5.entity.response.QueryBonusDetailResponse;
import com.money.h5.entity.response.QueryConsumptionResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "积分记录")
@RequestMapping(value = "bonusRecord")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class BonusRecordController {

    @Autowired
    BonusWalletService bonusWalletService;

    @ApiOperation(value = "查询积分记录（可分页）", notes = "openId = abc")
    @ResponseBody
    @RequestMapping(value = "queryBonusRecord", method = RequestMethod.POST)
    public QueryBonusDetailResponse queryBonusRecord(@Valid @RequestBody H5GridRequestBase request, BindingResult bindingResult) {
        QueryBonusWalletDetailRequest queryOrderConsumptionRequest = new QueryBonusWalletDetailRequest();
        queryOrderConsumptionRequest.setOpenId(request.getOpenId());
        queryOrderConsumptionRequest.setAddBonus(1);
        queryOrderConsumptionRequest.copyPagerFromH5Request(request);
        int recordCount = this.bonusWalletService.selectSearchListCount(queryOrderConsumptionRequest);
        return new QueryBonusDetailResponse(recordCount, request.calTotalPage(recordCount), this.bonusWalletService.selectSearchList(queryOrderConsumptionRequest));
    }

}
