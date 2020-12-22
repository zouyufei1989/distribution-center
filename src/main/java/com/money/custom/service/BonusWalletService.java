package com.money.custom.service;

import com.money.custom.entity.BonusWallet;
import com.money.custom.entity.request.*;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface BonusWalletService extends BaseService {

    List<BonusWallet> selectSearchList(QueryBonusWalletRequest request);

    Integer selectSearchListCount(QueryBonusWalletRequest request);

    BonusWallet findById(String id);

    String add(BonusWallet item);

    String edit(BonusWallet item);

    String recharge(BonusRechargeRequest request);

    String deduction(DeductionRequest request);

    void distribution(DistributeBonusRequest request);
}
