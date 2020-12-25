package com.money.h5.controller;

import com.money.custom.entity.Customer;
import com.money.custom.entity.request.QueryCustomerRequest;
import com.money.custom.service.CustomerService;
import com.money.h5.entity.request.QueryByIdRequest;
import com.money.h5.entity.response.QueryMyCustomerResponse;
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

    @ApiOperation(value = "查询我的客源列表（可分页）", notes = "id=7")
    @ResponseBody
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public QueryMyCustomerResponse queryMyCustomer(@Valid @RequestBody QueryByIdRequest request, BindingResult bindingResult) {
        QueryCustomerRequest queryCustomerRequest = new QueryCustomerRequest();
        queryCustomerRequest.setParentId(Integer.parseInt(request.getId()));
        queryCustomerRequest.copyPagerFromH5Request(request);
        queryCustomerRequest.copyPagerFromH5Request(request);
        List<Customer> customers = customerService.selectSearchList(queryCustomerRequest);
        Integer recordCount = customerService.selectSearchListCount(queryCustomerRequest);
        return new QueryMyCustomerResponse(recordCount, request.calTotalPage(recordCount), customers);
    }

}
