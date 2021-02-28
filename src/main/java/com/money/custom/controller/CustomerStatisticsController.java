package com.money.custom.controller;

import com.money.custom.entity.request.ShareHolderStatisticsRequest;
import com.money.custom.service.CustomerStatisticsService;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.ResponseBase;
import com.money.framework.base.entity.VisitLogTypeEnum;
import com.money.framework.base.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@VisitLogFlag(module = "统计报表")
@Controller
@RequestMapping("/statistics/*")
public class CustomerStatisticsController extends BaseController {

    @Autowired
    private CustomerStatisticsService customerStatisticsService;

    @VisitLogFlag(resource = "股东统计", type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "queryShareHolderStatistics")
    public ResponseBase queryShareHolderStatistics(@Valid @RequestBody ShareHolderStatisticsRequest request, BindingResult bindingResult) {
        return ResponseBase.success(customerStatisticsService.shareHolderStatistics(request));
    }

    @VisitLogFlag(resource = "顾客统计", type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "queryCustomerStatistics")
    public ResponseBase queryCustomerStatistics(@Valid @RequestBody ShareHolderStatisticsRequest request, BindingResult bindingResult) {
        return ResponseBase.success(customerStatisticsService.customerStatistics(request));
    }

    @VisitLogFlag(resource = "顾客总计", type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "queryCustomerSummaryStatistics")
    public ResponseBase queryCustomerSummaryStatistics(@Valid @RequestBody ShareHolderStatisticsRequest request, BindingResult bindingResult) {
        return ResponseBase.success(customerStatisticsService.customerSummaryStatistics(request));
    }
}
