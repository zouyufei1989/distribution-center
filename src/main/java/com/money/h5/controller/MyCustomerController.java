package com.money.h5.controller;

import com.money.custom.entity.Customer;
import com.money.custom.entity.OrderPay;
import com.money.custom.entity.request.QueryCustomerRequest;
import com.money.custom.entity.request.QueryOrderPayRequest;
import com.money.custom.service.CustomerService;
import com.money.custom.service.OrderPayService;
import com.money.framework.util.DateUtils;
import com.money.h5.entity.H5GridRequestBase;
import com.money.h5.entity.H5RequestBase;
import com.money.h5.entity.request.QueryByIdRequest;
import com.money.h5.entity.request.QueryMyCustomerRequest;
import com.money.h5.entity.response.QueryMyCustomerResponse;
import com.money.h5.entity.response.QueryOrderPayGroupByMonthResponse;
import com.money.h5.entity.response.QueryOrderPayResponse;
import com.money.h5.entity.response.QueryPersonalInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(description = "我的客源")
@RequestMapping(value = "myCustomers")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class MyCustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    OrderPayService orderPayService;

    @ApiOperation(value = "查询我的客源列表（可分页）", notes = "openId=tabc")
    @ResponseBody
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public QueryMyCustomerResponse queryMyCustomer(@Valid @RequestBody QueryMyCustomerRequest request, BindingResult bindingResult) {
        QueryCustomerRequest queryCustomerRequest = new QueryCustomerRequest();
        queryCustomerRequest.setParentOpenId(request.getOpenId());
        queryCustomerRequest.setNameOrPhone(request.getKey());
        queryCustomerRequest.copyPagerFromH5Request(request);
        queryCustomerRequest.copyPagerFromH5Request(request);
        List<Customer> customers = customerService.selectSearchList(queryCustomerRequest);
        Integer recordCount = customerService.selectSearchListCount(queryCustomerRequest);
        return new QueryMyCustomerResponse(recordCount, request.calTotalPage(recordCount), customers);
    }

    @ApiOperation(value = "查询客源的消费，按月聚合", notes = "id:6  近6个月")
    @ResponseBody
    @RequestMapping(value = "queryPayInfoGroupByMonth", method = RequestMethod.POST)
    public QueryOrderPayGroupByMonthResponse queryPayInfoGroupByMonth(@Valid @RequestBody QueryByIdRequest request, BindingResult bindingResult) {
        Integer monthInterval = Integer.parseInt(request.getId());
        QueryOrderPayRequest queryOrderPayRequest = new QueryOrderPayRequest(monthInterval);
        List<OrderPay> orderPays = orderPayService.selectSearchList(queryOrderPayRequest);
        return new QueryOrderPayGroupByMonthResponse(monthInterval, orderPays);
    }

    @ApiOperation(value = "查询客源的消费，按月展开", notes = "id: 2020-01 月份")
    @ResponseBody
    @RequestMapping(value = "queryPayInfoByMonth", method = RequestMethod.POST)
    public QueryOrderPayResponse queryPayInfoByMonth(@Valid @RequestBody QueryByIdRequest request, BindingResult bindingResult) {
        QueryOrderPayRequest queryOrderPayRequest = new QueryOrderPayRequest(request.getId());
        List<OrderPay> orderPays = orderPayService.selectSearchList(queryOrderPayRequest);
        Integer recordCount = orderPayService.selectSearchListCount(queryOrderPayRequest);
        return new QueryOrderPayResponse(recordCount, request.calTotalPage(recordCount), orderPays);
    }

}
