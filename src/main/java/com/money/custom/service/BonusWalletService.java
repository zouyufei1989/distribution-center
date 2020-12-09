package com.money.custom.service;

import com.money.custom.entity.BonusWallet;
import com.money.framework.base.service.BaseService;

public interface BonusWalletService extends BaseService {

    BonusWallet findById(String id);

    String add(BonusWallet item);

    String edit(BonusWallet item);

}
