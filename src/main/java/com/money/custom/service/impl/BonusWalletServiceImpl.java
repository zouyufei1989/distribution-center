package com.money.custom.service.impl;

import com.money.custom.dao.BonusWalletDao;
import com.money.custom.entity.BonusWallet;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.service.BonusWalletService;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BonusWalletServiceImpl extends BaseServiceImpl implements BonusWalletService {

    @Autowired
    BonusWalletDao dao;

    @Override
    public BonusWallet findById(String id) {
        return dao.findById(id);
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.BONUS_WALLET)
    @Override
    public String add(BonusWallet item) {
        dao.add(item);
        return item.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.BONUS_WALLET)
    @Override
    public String edit(BonusWallet item) {
        dao.edit(item);
        return item.getId().toString();
    }

}
