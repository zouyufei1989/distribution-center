package com.money.custom.service.impl;

import com.money.custom.dao.OrderDao;
import com.money.custom.dao.OrderItemDao;
import com.money.custom.dao.OrderRefundDao;
import com.money.custom.entity.*;
import com.money.custom.entity.enums.GoodsTypeEnum;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.enums.OrderRefundTypeEnum;
import com.money.custom.entity.enums.OrderStatusEnum;
import com.money.custom.entity.request.*;
import com.money.custom.service.*;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

    @Autowired
    OrderDao dao;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderItemDao orderItemDao;
    @Autowired
    GoodsService goodsService;
    @Autowired
    CustomerGroupService customerGroupService;
    @Autowired
    OrderRefundDao orderRefundDao;
    @Autowired
    WalletService walletService;

    @Override
    public List<Order> selectSearchList(QueryOrderRequest request) {
        List<Order> orders = dao.selectSearchList(request);
        if (CollectionUtils.isNotEmpty(orders)) {
            List<OrderItem> orderItems = orderItemDao.selectOrderItemsOfOrder(orders.stream().map(Order::getId).collect(Collectors.toList()));
            Map<Integer, List<OrderItem>> orderItemMap = orderItems.stream().collect(Collectors.groupingBy(OrderItem::getOrderId));
            orders.forEach(o -> o.setItems(orderItemMap.get(o.getId())));
        }
        return orders;
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
        if (goods.getType().equals(GoodsTypeEnum.PACKAGE.getValue())) {
            Assert.isTrue(CollectionUtils.isNotEmpty(goods.getItems()), "套餐未设置商品，不可购买");
        }
        if (goods.getType().equals(GoodsTypeEnum.ACTIVITY.getValue())) {
            Assert.isTrue(CollectionUtils.isNotEmpty(goods.getItems()), "活动未设置商品，不可购买");
        }


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

    @Transactional
    @Override
    public void refund(OrderRefundRequest refundRequest) {
        OrderService service = applicationContext.getBean(OrderService.class);

        OrderRefundParams orderRefundParams = queryOrderInfo4Refund(refundRequest.getOrderId());
        Assert.notNull(orderRefundParams, "订单不存在");
        Assert.isTrue(!orderRefundParams.getOrderStatus().equals(OrderStatusEnum.REFUND.getValue()), "当前订单已退款");
        Assert.isTrue(refundRequest.getRefundAmount() <= orderRefundParams.getOrderActualMoney(), "退款金额不可大于订单实际支付金额");

        OrderRefund refund = new OrderRefund(refundRequest);
        orderRefundDao.add(refund);

        if (refundRequest.getType().equals(OrderRefundTypeEnum.WALLET.getValue())) {
            RechargeRequest rechargeRequest = new RechargeRequest();
            rechargeRequest.setAmount(refundRequest.getRefundAmount());
            rechargeRequest.setCustomerGroupId(orderRefundParams.getCustomerGroupId());
            rechargeRequest.copyOperationInfo(refundRequest);
            walletService.recharge(rechargeRequest);
        }

        ChangeOrderStatusRequest changeOrderStatusRequest = new ChangeOrderStatusRequest(refundRequest.getOrderId().toString(), OrderStatusEnum.REFUND.getValue());
        changeOrderStatusRequest.copyOperationInfo(refundRequest);
        service.changeStatus(changeOrderStatusRequest);
    }

    @Override
    public OrderRefundParams queryOrderInfo4Refund(Integer orderId) {
        return dao.queryOrderInfo4Refund(orderId);
    }

}
