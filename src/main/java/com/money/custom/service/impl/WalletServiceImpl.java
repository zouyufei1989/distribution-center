package com.money.custom.service.impl;

import com.money.custom.dao.WalletDao;
import com.money.custom.entity.Wallet;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.service.WalletService;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl extends BaseServiceImpl implements WalletService {

    @Autowired
    WalletDao dao;

    @Override
    public Wallet findById(String id) {
        return dao.findById(id);
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.WALLET)
    @Override
    public String add(Wallet item) {
        dao.add(item);
        return item.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.WALLET)
    @Override
    public String edit(Wallet item) {
        dao.edit(item);
        return item.getId().toString();
    }

}
