package com.money.custom.controller;

import com.money.custom.entity.request.StatisticsBaseRequest;
import com.money.custom.entity.request.StatisticsGoodsSellingRankRequest;
import com.money.custom.entity.request.StatisticsGroupPerformanceRequest;
import com.money.custom.service.OperationStatisticsService;
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

@VisitLogFlag(module = "统计报表", resource = "运营情况统计")
@Controller
@RequestMapping("/statistics/*")
public class OperationStatisticsController extends BaseController {

    @Autowired
    private OperationStatisticsService operationStatisticsService;

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "operationSummaryInfo")
    public ResponseBase summaryInfo(@Valid @RequestBody StatisticsBaseRequest request, BindingResult bindingResult) {
        return ResponseBase.success(operationStatisticsService.summaryInfo(request));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "groupPerformance")
    public ResponseBase groupPerformance(@Valid @RequestBody StatisticsGroupPerformanceRequest request, BindingResult bindingResult) {
        return ResponseBase.success(operationStatisticsService.groupPerformance(request));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "goodsSellingRank")
    public ResponseBase goodsSellingRank(@Valid @RequestBody StatisticsGoodsSellingRankRequest request, BindingResult bindingResult) {
        return ResponseBase.success(operationStatisticsService.goodsSellingRank(request));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "groupPerformancePie")
    public ResponseBase groupPerformancePie(@Valid @RequestBody StatisticsBaseRequest request, BindingResult bindingResult) {
        return ResponseBase.success(operationStatisticsService.groupPerformancePie(request));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "bonusStatistics")
    public ResponseBase bonusStatistics(@Valid @RequestBody StatisticsBaseRequest request, BindingResult bindingResult) {
        return ResponseBase.success(operationStatisticsService.bonusStatistics(request));
    }

}
