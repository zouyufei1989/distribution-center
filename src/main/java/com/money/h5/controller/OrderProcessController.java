package com.money.h5.controller;

import com.money.custom.entity.OrderConsumption;
import com.money.custom.entity.OrderItemConsumption;
import com.money.custom.entity.OrderPay;
import com.money.custom.entity.request.QueryOrderConsumptionRequest;
import com.money.custom.entity.request.QueryOrderPayRequest;
import com.money.custom.service.GoodsService;
import com.money.custom.service.OrderConsumptionService;
import com.money.custom.service.OrderPayService;
import com.money.h5.entity.request.QueryByIdRequest;
import com.money.h5.entity.response.QueryActivityDetailResponse;
import com.money.h5.entity.response.QueryOrderProcessResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import javax.validation.Valid;
import java.util.List;

@Api(description = "项目记录")
@RequestMapping(value = "orderProcess")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class OrderProcessController {

    @Autowired
    OrderPayService orderPayService;
    @Autowired
    OrderConsumptionService orderConsumptionService;

    @ApiOperation(value = "项目记录", notes = "id = 1")
    @ResponseBody
    @RequestMapping(value = "queryOrderProcessList", method = RequestMethod.POST)
    public QueryOrderProcessResponse queryOrderProcessList(@Valid @RequestBody QueryByIdRequest request, BindingResult bindingResult) {
        QueryOrderPayRequest queryOrderPayRequest = new QueryOrderPayRequest();
        queryOrderPayRequest.setOrderId(Integer.parseInt(request.getId()));
        List<OrderPay> orderPays = orderPayService.selectSearchList(queryOrderPayRequest);

        QueryOrderConsumptionRequest queryOrderConsumptionRequest = new QueryOrderConsumptionRequest();
        queryOrderConsumptionRequest.setOrderId(Integer.parseInt(request.getId()));
        List<OrderConsumption> orderConsumptions = orderConsumptionService.selectSearchList(queryOrderConsumptionRequest);

        return new QueryOrderProcessResponse(orderConsumptions,orderPays);
    }

}
