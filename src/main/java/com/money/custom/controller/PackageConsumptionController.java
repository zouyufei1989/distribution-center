package com.money.custom.controller;

import com.money.custom.entity.request.QueryPackageConsumptionRequest;
import com.money.custom.service.PackageConsumptionService;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.base.entity.VisitLogTypeEnum;
import com.money.framework.base.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@VisitLogFlag(module = "客户管理", resource = "项目消耗记录")
@Controller
@RequestMapping("/packageConsumption/*")
public class PackageConsumptionController extends BaseController {

    @Autowired
    private PackageConsumptionService packageConsumptionService;

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public GridResponseBase listSearch(QueryPackageConsumptionRequest request) {
        int recordCount = this.packageConsumptionService.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.packageConsumptionService.selectSearchList(request));
    }
}
