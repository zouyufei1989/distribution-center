package com.money.h5.controller;

import com.money.custom.entity.CustomerActivity;
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
import java.util.List;

@Api(description = "活动列表")
@RequestMapping(value = "activityRecord")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class CustomerActivityController {

    @Autowired
    AssignActivityService assignActivityService;

    @ApiOperation(value = "查询活动列表")
    @ResponseBody
    @RequestMapping(value = "queryActivityList", method = RequestMethod.POST)
    public QueryActivityResponse queryActivityList(@Valid @RequestBody QueryByIdRequest request, BindingResult bindingResult) {
        List<CustomerActivity> customerActivities = assignActivityService.selectCustomerActivityList(Integer.parseInt(request.getId()));
        assignActivityService.selectCustomerActivityCount(Integer.parseInt(request.getId()));
        return new QueryActivityResponse(customerActivities);
    }

}
