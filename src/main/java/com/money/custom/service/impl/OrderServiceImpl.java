package com.money.custom.service.impl;

import com.money.custom.dao.OrderDao;
import com.money.custom.entity.CustomerGroup;
import com.money.custom.entity.Goods;
import com.money.custom.entity.Order;
import com.money.custom.entity.OrderItem;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.request.AddOrderRequest;
import com.money.custom.entity.request.ChangeOrderStatusRequest;
import com.money.custom.entity.request.QueryOrderItemRequest;
import com.money.custom.entity.request.QueryOrderRequest;
import com.money.custom.service.CustomerGroupService;
import com.money.custom.service.GoodsService;
import com.money.custom.service.OrderItemService;
import com.money.custom.service.OrderService;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

    @Autowired
    OrderDao dao;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    CustomerGroupService customerGroupService;

    @Override
    public List<Order> selectSearchList(QueryOrderRequest request) {
        return dao.selectSearchList(request);
    }

    @Override
    public int selectSearchListCount(QueryOrderRequest request) {
        return dao.selectSearchListCount(request);
    }

    @Override
    public Order findById(String id) {
        Order order = dao.findById(id);

        if (Objects.nonNull(order)) {
            QueryOrderItemRequest request = new QueryOrderItemRequest();
            request.setOrderId(order.getId());
            List<OrderItem> orderItems = orderItemService.selectSearchList(request);
            order.setItems(orderItems);
        }

        return dao.findById(id);
    }

    @Transactional
    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.ORDER)
    @Override
    public String add(AddOrderRequest request) {
        Assert.notNull(request.getCustomerGroupId(), "顾客id不可为空");
        CustomerGroup customerInfo = customerGroupService.findById(request.getCustomerGroupId().toString());
        Assert.notNull(customerInfo, "顾客不存在");

        Assert.notNull(request.getGoodsId(), "商品id不可为空");
        Goods goods = goodsService.findById(request.getGoodsId().toString());
        Assert.notNull(goods, "商品不存在");
        Assert.isTrue(goods.getGroupId().equals(customerInfo.getGroupId()), "客户不可跨门店购买商品");

        Order order = new Order(goods, customerInfo, request);
        dao.add(order);

        List<OrderItem> orderItems = goods.getItems().stream().map(i -> new OrderItem(i, order.getId(), request)).collect(Collectors.toList());
        orderItemService.addBatch(orderItems);

        return order.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.ORDER)
    @Override
    public List<String> changeStatus(ChangeOrderStatusRequest request) {
        dao.changeStatus(request);
        return request.getIds();
    }

}
