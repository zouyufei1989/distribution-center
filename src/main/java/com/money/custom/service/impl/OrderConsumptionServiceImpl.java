package com.money.custom.service.impl;

import com.money.custom.dao.OrderConsumptionDao;
import com.money.custom.dao.OrderDao;
import com.money.custom.dao.OrderItemDao;
import com.money.custom.entity.*;
import com.money.custom.entity.enums.GoodsCombineEnum;
import com.money.custom.entity.enums.OrderStatusEnum;
import com.money.custom.entity.request.ChangeOrderStatusRequest;
import com.money.custom.entity.request.ConsumeRequest;
import com.money.custom.entity.request.QueryOrderConsumptionRequest;
import com.money.custom.entity.request.QueryOrderRequest;
import com.money.custom.service.CustomerService;
import com.money.custom.service.OrderConsumptionService;
import com.money.custom.service.OrderService;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.util.Asserts;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderConsumptionServiceImpl extends BaseServiceImpl implements OrderConsumptionService {

    @Autowired
    OrderConsumptionDao dao;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemDao orderItemDao;
    @Autowired
    CustomerService customerService;

    @Override
    public List<OrderConsumption> selectSearchList(QueryOrderConsumptionRequest request) {
        return dao.selectSearchList(request);
    }

    @Override
    public int selectSearchListCount(QueryOrderConsumptionRequest request) {
        return dao.selectSearchListCount(request);
    }

    @Override
    public List<OrderItemConsumption> queryOrderConsumptions(QueryOrderConsumptionRequest request) {
        List<OrderConsumption> consumptions = dao.selectSearchList(request);
        if (CollectionUtils.isEmpty(consumptions)) {
            return Lists.newArrayList();
        }

        List<OrderItemConsumption> orderItemConsumptions = consumptions.stream().map(c -> c.getItems()).reduce((i, j) -> {
            i.addAll(j);
            return i;
        }).get();

        if (request.getDistinctByConsumption()) {
            orderItemConsumptions = orderItemConsumptions.stream().collect(
                    Collectors.collectingAndThen(
                            Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(OrderItemConsumption::getOrderConsumptionId))), ArrayList::new)
            );
        }

        return orderItemConsumptions.stream().sorted(Comparator.comparingInt(OrderItemConsumption::getId).reversed()).collect(Collectors.toList());
    }


    @Transactional
    @Override
    public void consume(ConsumeRequest request) {
        Order order = orderService.findById(request.getOrderId().toString());
        Assert.notNull(order, "未查询到订单");
        Assert.isTrue(order.getStatus().equals(OrderStatusEnum.USING.getValue()), "订单不可用");
        if (order.getGoodsCombine().equals(GoodsCombineEnum.SINGLE.getValue())) {
            Assert.notNull(request.getOrderItemId(), "非套餐,请选择消费项目");
        }

        Customer customer = customerService.findById(request.getCustomerGroupId().toString());
        Assert.notNull(customer, "未查询到顾客");
        Assert.isTrue(customer.getCustomerGroup().getId().equals(order.getCustomerGroupId()), "订单、顾客匹配失败");

        List<OrderItem> orderItems = order.getItems();
        if (Objects.nonNull(request.getOrderItemId())) {
            orderItems = orderItems.stream().filter(o -> o.getId().equals(request.getOrderItemId())).collect(Collectors.toList());
        }
        Assert.isTrue(CollectionUtils.isNotEmpty(orderItems), "未查询到订单");

        OrderConsumption consumption = new OrderConsumption(order, request);
        dao.add(consumption);

        List<OrderItemConsumption> orderItemConsumptions = orderItems.stream().map(i -> new OrderItemConsumption(consumption, i, request)).collect(Collectors.toList());
        dao.addBatch(orderItemConsumptions);

        orderItemDao.consumeCnt(orderItems.stream().map(OrderItem::getId).collect(Collectors.toList()), request.getCnt());

        if (orderItems.stream().allMatch(o -> o.getCnt().equals(o.getCntUsed()))) {
            ChangeOrderStatusRequest changeOrderStatusRequest = new ChangeOrderStatusRequest(order.getId().toString(), OrderStatusEnum.USED.getValue());
            changeOrderStatusRequest.copyOperationInfo(request);
            orderService.changeStatus(changeOrderStatusRequest);
        }
    }
}
