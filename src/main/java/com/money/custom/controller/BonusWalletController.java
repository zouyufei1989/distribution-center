package com.money.custom.controller;

import com.money.custom.entity.request.DistributeBonusRequest;
import com.money.custom.entity.request.QueryBonusWalletDetailRequest;
import com.money.custom.entity.request.QueryBonusWalletRequest;
import com.money.custom.service.BonusWalletService;
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
import org.springframework.web.bind.annotation.ResponseBody;

@VisitLogFlag(module = "客户管理")
@Controller
@RequestMapping("/bonusWallet/*")
public class BonusWalletController extends BaseController {

    @Autowired
    private BonusWalletService bonusWalletService;

    @VisitLogFlag(resource = "积分发放", type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public GridResponseBase listSearch(QueryBonusWalletRequest request) {
        int recordCount = this.bonusWalletService.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.bonusWalletService.selectSearchList(request));
    }

    @VisitLogFlag(resource = "积分发放", type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "distributeBonus")
    public ResponseBase distribution(@RequestBody DistributeBonusRequest request, BindingResult bindingResult) {
        this.bonusWalletService.distribution(request);
        return ResponseBase.success();
    }

    @VisitLogFlag(resource = "积分发放记录", type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "queryBonusDistributions")
    public GridResponseBase queryBonusDistributions(QueryBonusWalletDetailRequest request) {
        int recordCount = this.bonusWalletService.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.bonusWalletService.selectSearchList(request));
    }

    @VisitLogFlag(resource = "积分记录", type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "queryBonusWalletDetail")
    public GridResponseBase queryBonusWalletDetail(QueryBonusWalletDetailRequest request) {
        int recordCount = this.bonusWalletService.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.bonusWalletService.selectSearchList(request));
    }
}
