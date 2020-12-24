package com.money.h5.controller;

import com.money.custom.entity.Customer;
import com.money.custom.service.CustomerService;
import com.money.h5.entity.request.QueryByIdRequest;
import com.money.h5.entity.response.QueryPersonalInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "个人中心")
@RequestMapping(value = "personalCenter")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class PersonalCenterController {

    @Autowired
    CustomerService customerService;

    @ApiOperation(value = "查询个人信息")
    @ResponseBody
    @RequestMapping(value = "queryPersonalInfo", method = RequestMethod.POST)
    public QueryPersonalInfoResponse queryGoodsDetail(@Valid @RequestBody QueryByIdRequest request, BindingResult bindingResult) {
        Customer customer = customerService.findById(request.getId());
        return new QueryPersonalInfoResponse(customer);
    }

}
