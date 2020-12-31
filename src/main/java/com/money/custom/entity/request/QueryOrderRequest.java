package com.money.custom.entity.request;

import com.money.custom.entity.Customer;
import com.money.custom.entity.CustomerGroup;
import com.money.custom.entity.Order;
import com.money.custom.entity.OrderPay;
import com.money.custom.entity.enums.GoodsTypeEnum;

import java.util.Map;

public class QueryOrderRequest extends QueryGridRequestBase {

    Order order = new Order();
    Customer customer = new Customer();
    OrderPay orderPay = new OrderPay();

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("order", order);
        params.put("customer", customer);
        params.put("orderPay", orderPay);
        return params;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setOrderBatchId(String batchId) {
        this.order.setBatchId(batchId);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderPay getOrderPay() {
        return orderPay;
    }

    public void setOrderPay(OrderPay orderPay) {
        this.orderPay = orderPay;
    }

    public void setCustomerGroupId(Integer customerGroupId) {
        this.customer.getCustomerGroup().setId(customerGroupId);
    }

    public void setType(GoodsTypeEnum type) {
        this.order.setGoodsTypeId(type.getValue());
    }

    public void setOpenId(String openId){
        this.customer.setOpenId(openId);
    }

    public void setGoodsId(Integer goodsId){
        this.order.setGoodsId(goodsId);
    }
}
