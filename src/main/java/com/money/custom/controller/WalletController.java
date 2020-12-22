package com.money.custom.controller;

import com.money.custom.entity.request.QueryWalletDetailRequest;
import com.money.custom.service.WalletService;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.base.entity.VisitLogTypeEnum;
import com.money.framework.base.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@VisitLogFlag(module = "客户管理",resource = "客户充值记录")
@Controller
@RequestMapping("/wallet/*")
public class WalletController extends BaseController {

    @Autowired
    private WalletService walletService;

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "queryWalletRechargeRecord")
    public GridResponseBase listSearch(QueryWalletDetailRequest request) {
        int recordCount = this.walletService.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.walletService.selectSearchList(request));
    }
}
