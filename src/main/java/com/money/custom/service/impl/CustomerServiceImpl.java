package com.money.custom.service.impl;

import com.money.custom.dao.CustomerDao;
import com.money.custom.entity.*;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.enums.OrderStatusEnum;
import com.money.custom.entity.enums.ResponseCodeEnum;
import com.money.custom.entity.enums.SerialNumberEnum;
import com.money.custom.entity.request.*;
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
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
    OrderService orderService;
    @Autowired
    OrderPayService orderPayService;
    @Autowired
    UtilsService utilsService;
    @Autowired
    BonusPlanService bonusPlanService;
    @Autowired
    OrderConsumptionService orderConsumptionService;


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
        Assert.notNull(customerGroup, "未查询到门店顾客");

        Customer customer = dao.findById(customerGroup.getCustomerId().toString());
        Assert.notNull(customer, "未查询到顾客");
        customer.setCustomerGroup(customerGroup);

        if (Objects.nonNull(customerGroup.getParentId())) {
            Customer parent = findById(customerGroup.getParentId().toString());
            customer.setParent(parent);
        }

        if (Objects.nonNull(customerGroup.getWalletId())) {
            Wallet wallet = walletService.findById(customerGroup.getWalletId().toString());
            customer.setWallet(wallet);
        }

        if (Objects.nonNull(customerGroup.getBonusWalletId())) {
            BonusWallet bonusWallet = bonusWalletService.findById(customerGroup.getBonusWalletId().toString());
            customer.setBonusWallet(bonusWallet);
        }

        if (Objects.nonNull(customerGroup.getBonusPlanId())) {
            BonusPlan bonusPlan = bonusPlanService.findById(customerGroup.getBonusPlanId().toString());
            customer.setBonusPlan(bonusPlan);
        }

        return customer;
    }

    @Override
    public Customer findByOpenId(String openId) {
        Customer customer = dao.findById(openId);

        if (Objects.isNull(customer)) {
            return null;
        }

        if (StringUtils.isEmpty(customer.getCustomerGroupIds())) {
            return customer;
        }

        String[] customerGroupIds = customer.getCustomerGroupIds().split(",");
        Assert.isTrue(customerGroupIds.length <= 1, "暂不支持注册多个门店");
        return findById(customerGroupIds[0]);
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.CUSTOMER)
    @Override
    @Transactional
    public String add(MoACustomerRequest request) {
        List<Customer> customers = queryCustomerUsingPhone(request.getPhone());
        if (customers.stream().anyMatch(c -> Objects.nonNull(c.getCustomerGroup().getGroupId()) && c.getCustomerGroup().getGroupId().equals(request.getGroupId()))) {
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

    @Override
    public String add(Customer customer) {
        dao.add(customer);
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
        Customer srcCus = findByOpenId(request.getOpenId());

        if (Objects.isNull(srcCus)) {
            Customer customer = new Customer(request);
            customer.ofH5(request.getOpenId());
            getLogger().info("创建customer: {}", request.getOpenId());
            customer.setOpenId(request.getOpenId());
            dao.add(customer);
        } else {
            getLogger().info("更新customer");
            srcCus.setOpenId(request.getOpenId());
            srcCus.ofH5(request.getOpenId());
            dao.edit(srcCus);
        }

        return request.getOpenId();
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
        customerGroupUpdate.setCashbackFirst(request.getCashbackFirst());
        customerGroupUpdate.setExpireDate(request.getExpireDate());
        customerGroupUpdate.setBankName(request.getBankName());
        customerGroupUpdate.setBankCardNumber(request.getBankCardNumber());
        customerGroupUpdate.copyOperationInfo(request);
        customerGroupService.edit(customerGroupUpdate);

        Customer updateCustomer = new Customer();
        updateCustomer.setId(customerGroup.getCustomerId());
        updateCustomer.setName(request.getName());
        updateCustomer.setPhone(request.getPhone());
        dao.edit(updateCustomer);
        return updateCustomer.getId().toString();
    }

    @Override
    public String edit(Customer customer) {
        dao.edit(customer);
        return customer.getOpenId();
    }

    @Transactional
    @Override
    public String purchase(PurchaseRequest request) {
        String batchId = UUID.randomUUID().toString();

        AddOrderRequest addOrderRequest = new AddOrderRequest();
        addOrderRequest.setBatchId(batchId);
        addOrderRequest.setCustomerGroupId(request.getCustomerGroupId());
        addOrderRequest.copyOperationInfo(request);

        request.getGoodsChoosed().forEach(g -> {
            addOrderRequest.setGoodsId(g.getId());
            addOrderRequest.setCnt(g.getCnt());
            orderService.add(addOrderRequest);
        });

        orderPayService.pay(new PayOrderRequest(request, batchId));
        return batchId;
    }

    @Transactional
    @Override
    public void purchaseThenConsumeAll(PurchaseConsumeRequest request) {
        CustomerService curService = applicationContext.getBean(CustomerService.class);
        String batchId = curService.purchase(request);

        QueryOrderRequest queryOrderRequest = new QueryOrderRequest();
        queryOrderRequest.setOrderBatchId(batchId);
        List<Order> orders = orderService.selectSearchList(queryOrderRequest);
        Assert.isTrue(CollectionUtils.isNotEmpty(orders), "未查询到订单");

        ConsumeRequest consumeRequest = new ConsumeRequest();
        consumeRequest.copyOperationInfo(request);
        orders.forEach(o -> {
            consumeRequest.setOrderId(o.getId());
            o.getItems().forEach(i -> {
                consumeRequest.setOrderItemId(i.getId());
                consumeRequest.setCnt(i.getCnt());
                consumeRequest.setCustomerGroupId(request.getCustomerGroupId());
                orderConsumptionService.consume(consumeRequest);
            });
        });
    }

    @Override
    public String deleteByOpenId(String openId) {
        dao.deleteByOpenId(openId);
        return openId;
    }

}
