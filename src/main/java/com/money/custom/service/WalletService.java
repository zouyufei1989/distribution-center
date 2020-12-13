package com.money.custom.service;

import com.money.custom.entity.Wallet;
import com.money.custom.entity.request.MoACustomerRequest;
import com.money.custom.entity.request.RechargeRequest;
import com.money.framework.base.service.BaseService;

public interface WalletService extends BaseService {

    //    List<Banner> selectSearchList(QueryGridRequestBase request);
//
//    int selectSearchListCount(QueryGridRequestBase request);
//
    Wallet findById(String id);

    String add(Wallet item);

    String edit(Wallet item);

    String recharge(RechargeRequest rechargeRequest);
//
//    List<String> changeStatus(ChangeStatusRequest request);

}
