package com.money.h5.controller;

import com.money.custom.service.AssignActivityService;
import com.money.h5.entity.request.QueryByIdRequest;
import com.money.h5.entity.response.QueryActivityResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "活动列表")
@RequestMapping(value = "activityRecord")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class CustomerActivityController {

    @Autowired
    AssignActivityService assignActivityService;

    @ApiOperation(value = "查询活动列表（可分页）", notes = "id = 10")
    @ResponseBody
    @RequestMapping(value = "queryActivityList", method = RequestMethod.POST)
    public QueryActivityResponse queryActivityList(@Valid @RequestBody QueryByIdRequest request, BindingResult bindingResult) {
        int recordCount = this.assignActivityService.selectCustomerActivityCount(request);
        return new QueryActivityResponse(recordCount, request.calTotalPage(recordCount), this.assignActivityService.selectCustomerActivityList(request));
    }

}
