package com.money.h5.controller;

import com.money.custom.entity.Customer;
import com.money.custom.entity.Order;
import com.money.custom.entity.enums.GoodsTypeEnum;
import com.money.custom.entity.request.QueryOrderRequest;
import com.money.custom.service.CustomerService;
import com.money.custom.service.OrderService;
import com.money.h5.entity.H5GridRequestBase;
import com.money.h5.entity.H5RequestBase;
import com.money.h5.entity.request.QueryByIdRequest;
import com.money.h5.entity.response.QueryOrderResponse;
import com.money.h5.entity.response.QueryPersonalInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(description = "个人中心")
@RequestMapping(value = "personalCenter")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class PersonalCenterController {

    @Autowired
    CustomerService customerService;
    @Autowired
    OrderService orderService;

    @ApiOperation(value = "查询个人信息", notes = "id=1")
    @ResponseBody
    @RequestMapping(value = "queryPersonalInfo", method = RequestMethod.POST)
    public QueryPersonalInfoResponse queryGoodsDetail(@Valid @RequestBody H5RequestBase request, BindingResult bindingResult) {
        Customer customer = customerService.findByOpenId(request.getOpenId());
        return new QueryPersonalInfoResponse(customer);
    }

    @ApiOperation(value = "查询套餐、活动信息（可分页）", notes = "openId=abc")
    @ResponseBody
    @RequestMapping(value = "queryOrderInfo", method = RequestMethod.POST)
    public QueryOrderResponse queryOrderInfo(@Valid @RequestBody H5GridRequestBase request, BindingResult bindingResult) {
        QueryOrderRequest queryOrderRequest = new QueryOrderRequest();
        queryOrderRequest.setOpenId(request.getOpenId());
        queryOrderRequest.copyPagerFromH5Request(request);
        queryOrderRequest.setGoodsType(GoodsTypeEnum.ACTIVITY, GoodsTypeEnum.PACKAGE);
        List<Order> orders = orderService.selectSearchList(queryOrderRequest);
        Integer recordCount = orderService.selectSearchListCount(queryOrderRequest);
        return new QueryOrderResponse(recordCount, request.calTotalPage(recordCount), orders);
    }

}
