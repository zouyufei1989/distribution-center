package com.money.custom.service.impl;

import com.money.custom.dao.OrderPayDao;
import com.money.custom.entity.Customer;
import com.money.custom.entity.Order;
import com.money.custom.entity.OrderPay;
import com.money.custom.entity.OrderPayItem;
import com.money.custom.entity.enums.CustomerTypeEnum;
import com.money.custom.entity.enums.OrderStatusEnum;
import com.money.custom.entity.enums.PayTypeEnum;
import com.money.custom.entity.request.ChangeOrderStatusRequest;
import com.money.custom.entity.request.DeductionRequest;
import com.money.custom.entity.request.PayOrderRequest;
import com.money.custom.entity.request.QueryOrderRequest;
import com.money.custom.service.*;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderPayServiceImpl extends BaseServiceImpl implements OrderPayService {

    @Autowired
    OrderPayDao dao;
    @Autowired
    OrderPayItemService orderPayItemService;
    @Autowired
    OrderService orderService;
    @Autowired
    CustomerService customerService;
    @Autowired
    WalletService walletService;
    @Autowired
    BonusWalletService bonusWalletService;

    @Transactional
    @Override
    public void pay(PayOrderRequest request) {
        OrderPayService currSer = applicationContext.getBean(OrderPayService.class);

        Assert.notNull(request.getOrderBatchId(), "订单batchId不可为空");
        Assert.notNull(request.getSumMoney(), "订单金额错误");
        Assert.isTrue(request.getSumMoney() >= 0, "订单金额错误");

        List<Order> orders = queryOrders4Pay(request);
        Customer customer = queryCustomers4Pay(request, orders);
        splitAmountToPayType(request, customer);
        List<OrderPay> orderPays = splitAmountToOrders(request, orders);

        orderPays.forEach(o -> {
            o.setCustomer(customer);
            currSer.add(o);
        });
    }

    private List<OrderPay> splitAmountToOrders(PayOrderRequest request, List<Order> orders) {
        List<OrderPay> orderPays = orders.stream().limit(orders.size() - 1).map(o -> new OrderPay(o, request, false)).collect(Collectors.toList());
        orderPays.add(new OrderPay(orders.get(orders.size() - 1), request, true));
        return orderPays;
    }

    private void splitAmountToPayType(PayOrderRequest request, Customer customer) {
        Integer payAmount = request.getActuallyMoney();
        Integer money = 0;
        Integer bonus = 0;
        Integer offLineAmount = 0;
        if (PayTypeEnum.MONEY.pay(request.getPayType())) {
            money = customer.getWallet().getAvailableMoney();
            if (money > payAmount) {
                money = payAmount;
            }
            request.setMoneyAmount(money);
        }
        if (PayTypeEnum.BONUS.pay(request.getPayType())) {
            Assert.isTrue(money < payAmount, "余额充足，无需积分支付");
            bonus = customer.getBonusWallet().getAvailableBonus();
            if (money + bonus > payAmount) {
                bonus = payAmount - money;
            }
            request.setBonusAmount(bonus);
        }
        if (PayTypeEnum.OFFLINE.pay(request.getPayType())) {
            Assert.isTrue(money + bonus < payAmount, "余额、积分充足，无需线下支付");
            offLineAmount = request.getOffLineAmount();
            request.setOffLineAmount(offLineAmount);
        }
        Assert.isTrue(money + bonus + offLineAmount == payAmount, "实际支付金额错误");
    }

    private Customer queryCustomers4Pay(PayOrderRequest request, List<Order> orders) {
        Customer customer = customerService.findById(String.valueOf(orders.stream().mapToInt(Order::getCustomerGroupId).findAny().getAsInt()));
        Assert.notNull(customer, "未查询到顾客信息");
        if (!customer.getCustomerGroup().getType().equals(CustomerTypeEnum.SHARE_HOLDER.getValue())) {
            Assert.isTrue(!PayTypeEnum.BONUS.pay(request.getPayType()), "非股东，不可使用积分支付");
        }
        return customer;
    }

    private List<Order> queryOrders4Pay(PayOrderRequest request) {
        QueryOrderRequest queryOrderRequest = new QueryOrderRequest();
        queryOrderRequest.setOrderBatchId(request.getOrderBatchId());
        List<Order> orders = orderService.selectSearchList(queryOrderRequest);
        Assert.notEmpty(orders, "未查询到订单");
        Assert.isTrue(orders.stream().allMatch(i -> i.getStatus().equals(OrderStatusEnum.PENDING_PAY.getValue())), "订单状态异常，不可支付");
        Assert.isTrue(orders.stream().mapToInt(Order::getCustomerGroupId).distinct().count() == 1, "订单属于多位顾客");
        int batchSumPrice = orders.stream().mapToInt(Order::getOrderPrice).sum();
        Assert.isTrue(batchSumPrice == request.getSumMoney(), "商品价格已更新");
        return orders;
    }

    @Transactional
    @Override
    public String add(OrderPay item) {
        dao.add(item);

        if (PayTypeEnum.MONEY.pay(item.getPayType()) && item.getMoneyAmount() > 0) {
            String payItemId = orderPayItemService.add(new OrderPayItem(item, PayTypeEnum.MONEY, item.getMoneyAmount()));
            walletService.deduction(new DeductionRequest(item, item.getMoneyAmount(), payItemId));
        }
        if (PayTypeEnum.BONUS.pay(item.getPayType()) && item.getBonusAmount() > 0) {
            String payItemId = orderPayItemService.add(new OrderPayItem(item, PayTypeEnum.BONUS, item.getBonusAmount()));
            bonusWalletService.deduction(new DeductionRequest(item, item.getBonusAmount(), payItemId));
        }
        if (PayTypeEnum.OFFLINE.pay(item.getPayType()) && item.getOfflineAmount() > 0) {
            orderPayItemService.add(new OrderPayItem(item, PayTypeEnum.OFFLINE, item.getOfflineAmount()));
        }

        ChangeOrderStatusRequest changeOrderStatusRequest = new ChangeOrderStatusRequest(item.getOrderId().toString(), OrderStatusEnum.USING.getValue());
        changeOrderStatusRequest.copyOperationInfo(item);
        orderService.changeStatus(changeOrderStatusRequest);

        return item.getId().toString();
    }

    @Override
    public OrderPay findById(String id) {
        return dao.findById(id);
    }
}
