package com.money.h5.controller;

import com.money.custom.service.AssignActivityService;
import com.money.custom.service.GoodsService;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.request.QueryByIdRequest;
import com.money.h5.entity.response.QueryActivityDetailResponse;
import com.money.h5.entity.response.QueryActivityResponse;
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

    @ApiOperation(value = "活动内容", notes = "id = 10")
    @ResponseBody
    @RequestMapping(value = "queryActivityDetail", method = RequestMethod.POST)
    public QueryActivityDetailResponse queryActivityList(@Valid @RequestBody QueryByIdRequest request, BindingResult bindingResult) {
        return new QueryActivityDetailResponse(this.goodsService.findById(request.getId()));
    }

}
