package com.money.custom.service.impl;

import com.money.custom.dao.OrderPayItemDao;
import com.money.custom.entity.Customer;
import com.money.custom.entity.CustomerGroup;
import com.money.custom.entity.OrderItem;
import com.money.custom.entity.OrderPayItem;
import com.money.custom.entity.enums.BonusChangeTypeEnum;
import com.money.custom.entity.enums.CashBackTypeEnum;
import com.money.custom.entity.enums.CustomerTotalNewEnum;
import com.money.custom.entity.enums.PayTypeEnum;
import com.money.custom.entity.request.BonusRechargeRequest;
import com.money.custom.entity.request.QueryOrderItemRequest;
import com.money.custom.service.*;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class OrderPayItemServiceImpl extends BaseServiceImpl implements OrderPayItemService {

    @Autowired
    OrderPayItemDao dao;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    BonusWalletService bonusWalletService;
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerGroupService customerGroupService;

    @Transactional
    @Override
    public String add(OrderPayItem item) {
        dao.add(item);

        if (!(PayTypeEnum.MONEY.pay(item.getType()) || PayTypeEnum.OFFLINE.pay(item.getType()))) {
            return item.getId().toString();
        }

        if (Objects.isNull(item.getCustomer().getCustomerGroup().getParentId())) {
            return item.getId().toString();
        }
        Customer parent = customerService.findById(item.getCustomer().getCustomerGroup().getParentId().toString());

        if (Objects.isNull(parent.getBonusPlan()) || Objects.isNull(parent.getBonusPlan().getBonusRate())) {
            getLogger().warn("未设置分红方案");
            return item.getId().toString();
        }

        Long bonus = calProfit(item) * parent.getBonusPlan().getBonusRate() / 100 / 100;
        BonusRechargeRequest bonusRechargeRequest = new BonusRechargeRequest(bonus, item, parent.getBonusPlan(), BonusChangeTypeEnum.GAIN);
        bonusWalletService.recharge(bonusRechargeRequest);

        if (parent.getBonusPlan().getCashbackFirst().equals(CashBackTypeEnum.CASHBACK.getValue()) && item.getCustomer().getCustomerGroup().getTotalNew().equals(CustomerTotalNewEnum.NEW.getValue())) {
            bonusRechargeRequest = new BonusRechargeRequest(parent.getBonusPlan().getCashbackAmount(), item, parent.getBonusPlan(),BonusChangeTypeEnum.BONUSBACK);
            bonusWalletService.recharge(bonusRechargeRequest);
        }

        item.getCustomer().getCustomerGroup().setTotalNew(CustomerTotalNewEnum.OLD.getValue());
        customerGroupService.edit(item.getCustomer().getCustomerGroup());

        return item.getId().toString();
    }


    Integer calProfit(OrderPayItem item) {
        QueryOrderItemRequest queryOrderItemRequest = new QueryOrderItemRequest();
        queryOrderItemRequest.setOrderId(item.getOrderId());
        List<OrderItem> orderItems = orderItemService.selectSearchList(queryOrderItemRequest);
        long sumPrice = orderItems.stream().mapToLong(o -> o.getGoodsPrice() * o.getCnt()).sum();
        long sumProfit = orderItems.stream().map(OrderItem::calProfit).mapToInt(Integer::intValue).sum();
        return (int) (item.getAmount() * sumProfit / sumPrice);
    }


}
