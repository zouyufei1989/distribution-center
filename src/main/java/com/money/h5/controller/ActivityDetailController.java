package com.money.h5.controller;

import com.money.custom.entity.request.QueryBonusWalletDetailRequest;
import com.money.custom.entity.request.QueryClaimActivityRequest;
import com.money.custom.service.ActivityClaimRecordService;
import com.money.custom.service.GoodsService;
import com.money.h5.entity.request.QueryByIdRequest;
import com.money.h5.entity.response.QueryActivityClaimRecordResponse;
import com.money.h5.entity.response.QueryActivityDetailResponse;
import com.money.h5.entity.response.QueryBonusDetailResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "活动详情")
@RequestMapping(value = "activityDetail")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class ActivityDetailController {

    @Autowired
    GoodsService goodsService;
    @Autowired
    ActivityClaimRecordService activityClaimRecordService;

    @ApiOperation(value = "活动内容", notes = "id = 5")
    @ResponseBody
    @RequestMapping(value = "queryActivityDetail", method = RequestMethod.POST)
    public QueryActivityDetailResponse queryActivityList(@Valid @RequestBody QueryByIdRequest request, BindingResult bindingResult) {
        return new QueryActivityDetailResponse(this.goodsService.findById(request.getId()));
    }

    @ApiOperation(value = "赠送记录", notes = "id = 3; openId=tabc")
    @ResponseBody
    @RequestMapping(value = "queryActivityClaimRecord", method = RequestMethod.POST)
    public QueryActivityClaimRecordResponse queryActivityClaimRecord(@Valid @RequestBody QueryByIdRequest request, BindingResult bindingResult) {
        QueryClaimActivityRequest claimActivityRequest = new QueryClaimActivityRequest();
        claimActivityRequest.setActivityAssignId(Integer.parseInt(request.getId()));
        claimActivityRequest.copyPagerFromH5Request(request);
        claimActivityRequest.setOpenId(request.getOpenId());
        int recordCount = this.activityClaimRecordService.selectSearchListCount(claimActivityRequest);
        return new QueryActivityClaimRecordResponse(recordCount, request.calTotalPage(recordCount), this.activityClaimRecordService.selectSearchList(claimActivityRequest));
    }


}
