package com.money.h5.controller;

import com.money.custom.entity.Customer;
import com.money.custom.entity.Goods;
import com.money.custom.entity.WalletDetail;
import com.money.custom.entity.enums.WalletChangeTypeEnum;
import com.money.custom.entity.request.QueryWalletDetailRequest;
import com.money.custom.service.CustomerService;
import com.money.custom.service.GoodsService;
import com.money.custom.service.WalletService;
import com.money.h5.entity.H5GridRequestBase;
import com.money.h5.entity.request.QueryByIdRequest;
import com.money.h5.entity.response.QueryGoodsDetailResponse;
import com.money.h5.entity.response.QueryWalletRechargeResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(description = "充值记录")
@RequestMapping(value = "walletRecharge")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class WalletRechargeController {

    @Autowired
    WalletService walletService;
    @Autowired
    CustomerService customerService;

    @ApiOperation(value = "充值列表（可分页）")
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public QueryWalletRechargeResponse list(@Valid @RequestBody H5GridRequestBase request, BindingResult bindingResult) {
        QueryWalletDetailRequest queryWalletDetailRequest = new QueryWalletDetailRequest();
        queryWalletDetailRequest.setOpenId(request.getOpenId());
        queryWalletDetailRequest.setChangeType(WalletChangeTypeEnum.RECHARGE.getValue());
        queryWalletDetailRequest.copyPagerFromH5Request(request);
        List<WalletDetail> walletDetails = walletService.selectSearchList(queryWalletDetailRequest);
        Integer recordCount = walletService.selectSearchListCount(queryWalletDetailRequest);

        return new QueryWalletRechargeResponse(recordCount, request.calTotalPage(recordCount), walletDetails);
    }

}
