package com.money.custom.service.impl;

import com.money.custom.dao.WalletDao;
import com.money.custom.entity.Customer;
import com.money.custom.entity.CustomerGroup;
import com.money.custom.entity.Wallet;
import com.money.custom.entity.WalletDetail;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.request.DeductionRequest;
import com.money.custom.entity.request.RechargeRequest;
import com.money.custom.service.CustomerGroupService;
import com.money.custom.service.CustomerService;
import com.money.custom.service.WalletService;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class WalletServiceImpl extends BaseServiceImpl implements WalletService {

    @Autowired
    WalletDao dao;
    @Autowired
    CustomerGroupService customerGroupService;

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

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.WALLET)
    @Transactional
    @Override
    public String recharge(RechargeRequest rechargeRequest) {
        CustomerGroup customer = customerGroupService.findById(rechargeRequest.getCustomerGroupId().toString());
        Assert.notNull(customer, "未查询到充值客户");
        Assert.notNull(customer.getWalletId(), "客户钱包尚未初始化");

        Wallet wallet = findById(customer.getWalletId().toString());
        WalletDetail detail = new WalletDetail(rechargeRequest, wallet);
        dao.addDetail(detail);

        wallet.recharge(rechargeRequest);
        edit(wallet);

        return wallet.getId().toString();
    }

    @Transactional
    @Override
    public String deduction(DeductionRequest deductionRequest) {
        CustomerGroup customer = customerGroupService.findById(deductionRequest.getCustomerGroupId().toString());
        Assert.notNull(customer, "未查询到充值客户");
        Assert.notNull(customer.getWalletId(), "客户钱包尚未初始化");

        Wallet wallet = findById(customer.getWalletId().toString());
        WalletDetail detail = new WalletDetail(deductionRequest, wallet);
        dao.addDetail(detail);

        wallet.deduction(deductionRequest);
        edit(wallet);

        return wallet.getId().toString();
    }

}
