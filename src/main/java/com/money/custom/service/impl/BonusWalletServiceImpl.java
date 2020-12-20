package com.money.custom.service.impl;

import com.money.custom.dao.BonusWalletDao;
import com.money.custom.entity.*;
import com.money.custom.entity.enums.CustomerTypeEnum;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.request.BonusRechargeRequest;
import com.money.custom.entity.request.DeductionRequest;
import com.money.custom.entity.request.RechargeRequest;
import com.money.custom.service.BonusWalletService;
import com.money.custom.service.CustomerGroupService;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class BonusWalletServiceImpl extends BaseServiceImpl implements BonusWalletService {

    @Autowired
    BonusWalletDao dao;
    @Autowired
    CustomerGroupService customerGroupService;

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

    @Transactional
    @Override
    public String recharge(BonusRechargeRequest request) {
        CustomerGroup customer = customerGroupService.findById(request.getCustomerGroupId().toString());
        Assert.notNull(customer, "未查询到充值客户");
        Assert.notNull(customer.getBonusWalletId(), "客户积分钱包尚未初始化");

        BonusWallet wallet = findById(customer.getBonusWalletId().toString());
        if (!customer.getType().equals(CustomerTypeEnum.SHARE_HOLDER.getValue())) {
            return wallet.getId().toString();
        }

        BonusWalletDetail detail = new BonusWalletDetail(request, wallet);
        dao.addDetail(detail);

        wallet.recharge(request);
        edit(wallet);
        //TODO send sms to shareholder

        return wallet.getId().toString();
    }

    @Transactional
    @Override
    public String deduction(DeductionRequest request) {
        CustomerGroup customer = customerGroupService.findById(request.getCustomerGroupId().toString());
        Assert.notNull(customer, "未查询到充值客户");
        Assert.notNull(customer.getBonusWalletId(), "客户积分钱包尚未初始化");

        BonusWallet wallet = findById(customer.getBonusWalletId().toString());

        BonusWalletDetail detail = new BonusWalletDetail(request, wallet);
        dao.addDetail(detail);

        wallet.deduction(request);
        edit(wallet);

        return wallet.getId().toString();
    }

}
