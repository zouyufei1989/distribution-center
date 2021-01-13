package com.money.h5.controller;

import com.money.custom.entity.Customer;
import com.money.custom.entity.enums.BonusChangeTypeEnum;
import com.money.custom.entity.request.QueryBonusWalletDetailRequest;
import com.money.custom.service.BonusWalletService;
import com.money.custom.service.CustomerService;
import com.money.h5.entity.H5GridRequestBase;
import com.money.h5.entity.response.QueryBonusDetailResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "提现")
@RequestMapping(value = "withdraw")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class WithdrawController {

    @Autowired
    BonusWalletService bonusWalletService;
    @Autowired
    CustomerService customerService;

    @ApiOperation(value = "提现列表（可分页）", notes = "openId = tabc")
    @ResponseBody
    @RequestMapping(value = "bonusDistributionList", method = RequestMethod.POST)
    public QueryBonusDetailResponse bonusDistributionList(@Valid @RequestBody H5GridRequestBase request, BindingResult bindingResult) {
        QueryBonusWalletDetailRequest bonusWalletDetailRequest = new QueryBonusWalletDetailRequest();
        bonusWalletDetailRequest.setOpenId(request.getOpenId());
        bonusWalletDetailRequest.setChangeType(BonusChangeTypeEnum.DISTRIBUTION.getValue());
        bonusWalletDetailRequest.copyPagerFromH5Request(request);
        int recordCount = this.bonusWalletService.selectSearchListCount(bonusWalletDetailRequest);

        Customer customer = customerService.findByOpenId(request.getOpenId());
        Long pending = customer.getBonusWallet().getPendingBonus();

        return new QueryBonusDetailResponse(recordCount, request.calTotalPage(recordCount), this.bonusWalletService.selectSearchList(bonusWalletDetailRequest), pending);
    }

}
