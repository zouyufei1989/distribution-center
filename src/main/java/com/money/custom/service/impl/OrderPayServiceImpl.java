package com.money.custom.service.impl;

import com.money.custom.dao.OrderPayDao;
import com.money.custom.dao.OrderPayItemDao;
import com.money.custom.entity.Order;
import com.money.custom.entity.OrderPay;
import com.money.custom.entity.enums.OrderStatusEnum;
import com.money.custom.entity.request.PayOrderRequest;
import com.money.custom.entity.request.QueryOrderRequest;
import com.money.custom.service.OrderItemService;
import com.money.custom.service.OrderPayService;
import com.money.custom.service.OrderService;
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
    OrderPayItemDao payItemDao;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderService orderService;

    @Transactional
    @Override
    public void pay(PayOrderRequest request) {
        OrderPayService currSer = applicationContext.getBean(OrderPayService.class);

        Assert.notNull(request.getOrderBatchId(), "订单batchId不可为空");
        Assert.notNull(request.getSumMoney(), "订单金额错误");
        Assert.isTrue(request.getSumMoney() >= 0, "订单金额错误");

        QueryOrderRequest queryOrderRequest = new QueryOrderRequest();
        queryOrderRequest.setOrderBatchId(request.getOrderBatchId());
        List<Order> orders = orderService.selectSearchList(queryOrderRequest);
        Assert.notEmpty(orders, "未查询到订单");
        Assert.isTrue(orders.stream().allMatch(i -> i.getStatus().equals(OrderStatusEnum.PENDING_PAY.getValue())), "订单状态异常，不可支付");

        int batchSumPrice = orders.stream().mapToInt(Order::getOrderPrice).sum();
        Assert.isTrue(batchSumPrice == request.getSumMoney(), "总金额不符");
        List<OrderPay> orderPays = orders.stream().map(o -> new OrderPay(o, request)).collect(Collectors.toList());
        Integer calibration = request.getActuallyMoney() - orderPays.stream().limit(orderPays.size() - 1).mapToInt(OrderPay::getActuallyMoney).sum();
        orderPays.get(orderPays.size() - 1).setActuallyMoney(calibration);

        orderPays.forEach(currSer::add);
    }

    @Override
    public String add(OrderPay item) {
        dao.add(item);
        return item.getId().toString();
    }
}
