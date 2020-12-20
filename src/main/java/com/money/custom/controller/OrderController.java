package com.money.custom.controller;

import com.money.custom.entity.request.QueryOrderRequest;
import com.money.custom.service.OrderService;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.base.entity.VisitLogTypeEnum;
import com.money.framework.base.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@VisitLogFlag(module = "订单管理", resource = "订单管理")
@Controller
@RequestMapping("/order/*")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public GridResponseBase listSearch(QueryOrderRequest request) {
        int recordCount = this.orderService.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.orderService.selectSearchList(request));
    }

}
