package com.money.custom.service.impl;

import com.money.custom.dao.CustomerDao;
import com.money.custom.entity.BonusWallet;
import com.money.custom.entity.Customer;
import com.money.custom.entity.CustomerGroup;
import com.money.custom.entity.Wallet;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.enums.ResponseCodeEnum;
import com.money.custom.entity.enums.SerialNumberEnum;
import com.money.custom.entity.request.MoACustomerRequest;
import com.money.custom.entity.request.QueryCustomerRequest;
import com.money.custom.service.*;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.exception.PandabusSpecException;
import com.money.framework.base.service.impl.BaseServiceImpl;
import com.money.h5.entity.request.AddCustomer4WechatRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl extends BaseServiceImpl implements CustomerService {

    @Autowired
    CustomerDao dao;
    @Autowired
    WalletService walletService;
    @Autowired
    BonusWalletService bonusWalletService;
    @Autowired
    CustomerGroupService customerGroupService;
    @Autowired
    UtilsService utilsService;

    @Override
    public List<Customer> selectSearchList(QueryCustomerRequest request) {
        return dao.selectSearchList(request);
    }

    @Override
    public int selectSearchListCount(QueryCustomerRequest request) {
        return dao.selectSearchListCount(request);
    }

    @Override
    public Customer findById(String id) {
        CustomerGroup customerGroup = customerGroupService.findById(id);
        Assert.notNull(customerGroup,"未查询到门店顾客");

        Customer customer = dao.findById(customerGroup.getCustomerId().toString());
        Assert.notNull(customer,"未查询到顾客");
        customer.setCustomerGroup(customerGroup);

        if (Objects.nonNull(customerGroup.getParentId())) {
            Customer parent = findById(customerGroup.getParentId().toString());
            customer.setParent(parent);
        }

        if(Objects.nonNull(customerGroup.getWalletId())){
            Wallet wallet = walletService.findById(customerGroup.getWalletId().toString());
            customer.setWallet(wallet);
        }

        if(Objects.nonNull(customerGroup.getBonusWalletId())){
            BonusWallet bonusWallet = bonusWalletService.findById(customerGroup.getBonusWalletId().toString());
            customer.setBonusWallet(bonusWallet);
        }

        return customer;
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.CUSTOMER)
    @Override
    @Transactional
    public String add(MoACustomerRequest request) {
        List<Customer> customers = queryCustomerUsingPhone(request.getPhone());
        if (customers.stream().anyMatch(c -> c.getCustomerGroup().getGroupId().equals(request.getGroupId()))) {
            throw PandabusSpecException.businessError(ResponseCodeEnum.CUSTOMER_GROUP_EXISTS);
        }

        Customer customer = new Customer(request);
        if (CollectionUtils.isEmpty(customers)) {
            getLogger().info("创建customer: {}", request.getPhone());
            dao.add(customer);
        } else {
            getLogger().info("更新customer");
            customer.setId(customers.get(0).getId());
            dao.edit(customer);
        }

        getLogger().info("创建wallet: 店铺：{} - 手机：{}", request.getGroupId(), request.getPhone());
        String walletId = walletService.add(Wallet.totalNew(request));

        getLogger().info("创建bonus wallet: 店铺：{} - 手机：{}", request.getGroupId(), request.getPhone());
        String bonusWalletId = bonusWalletService.add(BonusWallet.totalNew(request));

        getLogger().info("创建customerGroup: 店铺：{} - 手机：{}", request.getGroupId(), request.getPhone());
        CustomerGroup customerGroup = new CustomerGroup(request, customer.getId(), walletId, bonusWalletId);
        customerGroupService.add(customerGroup);
        return customer.getId().toString();
    }

    private List<Customer> queryCustomerUsingPhone(String phone) {
        QueryCustomerRequest queryCustomerRequest = new QueryCustomerRequest();
        queryCustomerRequest.setExactPhone(phone);
        return selectSearchList(queryCustomerRequest);
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.CUSTOMER)
    @Override
    @Transactional
    public String addFromWechat(AddCustomer4WechatRequest request) {
        List<Customer> customers = queryCustomerUsingPhone(request.getPhone());
        if (customers.stream().anyMatch(c -> c.getCustomerGroup().getGroupId().equals(request.getGroupId()))) {
            throw PandabusSpecException.businessError(ResponseCodeEnum.CUSTOMER_GROUP_EXISTS);
        }

        Customer customer = new Customer(request);
        if (CollectionUtils.isEmpty(customers)) {
            getLogger().info("创建customer: {}", request.getPhone());
            customer.setName(request.getNickName());
            dao.add(customer);
        } else {
            getLogger().info("更新customer");
            customer.setId(customers.get(0).getId());
            dao.edit(customer);
        }

        getLogger().info("创建wallet: {} - {}", request.getGroupId(), request.getPhone());
        String walletId = walletService.add(Wallet.totalNew(customer));

        getLogger().info("创建bonus wallet: {} - {}", request.getGroupId(), request.getPhone());
        String bonusWalletId = bonusWalletService.add(BonusWallet.totalNew(customer));

        getLogger().info("创建customerGroup: {} - {}", request.getGroupId(), request.getPhone());

        CustomerGroup customerGroup = new CustomerGroup(request, utilsService.generateSerialNumber(SerialNumberEnum.CS), customer.getId(), walletId, bonusWalletId);
        customerGroupService.add(customerGroup);

        return customer.getId().toString();

    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.CUSTOMER)
    @Override
    @Transactional
    public String edit(MoACustomerRequest request) {
        CustomerGroup customerGroup = customerGroupService.findById(request.getId().toString());
        Assert.notNull(customerGroup, "客户门店关系不存在");

        CustomerGroup customerGroupUpdate = new CustomerGroup();
        customerGroupUpdate.setId(customerGroup.getId());
        customerGroupUpdate.setType(request.getType());
        customerGroupUpdate.setExpireDate(request.getExpireDate());
        customerGroupUpdate.setBankName(request.getBankName());
        customerGroupUpdate.setBankCardNumber(request.getBankCardNumber());
        customerGroupUpdate.copyOperationInfo(request);
        customerGroupService.edit(customerGroupUpdate);

        Customer updateCustomer = new Customer();
        updateCustomer.setId(customerGroup.getCustomerId());
        updateCustomer.setName(request.getName());
        dao.edit(updateCustomer);
        return updateCustomer.getId().toString();
    }

}
