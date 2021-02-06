package com.money.custom.controller;

import com.money.custom.entity.request.OrderRefundRequest;
import com.money.custom.entity.request.PurchaseConsumeRequest;
import com.money.custom.entity.request.QueryOrderConsumptionRequest;
import com.money.custom.entity.request.QueryOrderRequest;
import com.money.custom.service.BonusWalletService;
import com.money.custom.service.OrderConsumptionService;
import com.money.custom.service.OrderService;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.base.entity.ResponseBase;
import com.money.framework.base.entity.VisitLogTypeEnum;
import com.money.framework.base.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@VisitLogFlag(module = "客户管理", resource = "客户消费记录")
@Controller
@RequestMapping("/order/*")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;
    @Autowired
    OrderConsumptionService orderConsumptionService;
    @Autowired
    BonusWalletService bonusWalletService;

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public GridResponseBase listSearch(QueryOrderRequest request) {
        int recordCount = this.orderService.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.orderService.selectSearchList(request));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "queryOrderConsumption")
    public ResponseBase queryOrderConsumption(QueryOrderConsumptionRequest request) {
        return ResponseBase.success(this.orderConsumptionService.queryOrderConsumptions(request));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "queryOrderInfo4Refund")
    public ResponseBase queryBonusOfOrder(Integer orderId) {
        return ResponseBase.success(orderService.queryOrderInfo4Refund(orderId));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "refund", method = RequestMethod.POST)
    public ResponseBase refund(@Valid @RequestBody OrderRefundRequest request, BindingResult bindingResult) {
        this.orderService.refund(request);
        return ResponseBase.success();
    }
}
