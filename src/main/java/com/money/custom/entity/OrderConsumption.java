package com.money.custom.entity;

import com.money.custom.entity.request.ConsumeRequest;
import com.money.framework.base.entity.BaseEntity;

import java.util.List;

public class OrderConsumption extends BaseEntity {

    private Integer id;
    private Integer orderId;
    private Integer goodsId;
    private Integer combine;
    private Integer reservationId;

    private List<OrderItemConsumption> items;

    private Order order;

    public OrderConsumption() {}

    public OrderConsumption(Order order, ConsumeRequest request) {
        orderId = order.getId();
        goodsId = order.getGoodsId();
        combine = order.getGoodsCombine();
        reservationId = request.getReservationId();
        copyOperationInfo(request);
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItemConsumption> getItems() {
        return items;
    }

    public void setItems(List<OrderItemConsumption> items) {
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCombine() {
        return combine;
    }

    public void setCombine(Integer combine) {
        this.combine = combine;
    }
}
