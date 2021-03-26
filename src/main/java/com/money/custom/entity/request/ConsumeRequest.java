package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ConsumeRequest extends OperationalEntity {

    @NotNull(message = "请输入订单ID")
    private Integer orderId;
    private Integer orderItemId;
    @NotNull(message = "请输入使用数量")
    @Min(value = 1, message = "使用次数不可小于1")
    private Integer cnt;
    @NotNull(message = "客户id不可为空")
    private Integer customerGroupId;
    private Integer reservationId;

    public ConsumeRequest() {}

    public ConsumeRequest(ReservationConsumptionRequest request) {
        this.orderId = request.getOrderId();
        this.cnt = request.getCnt();
        this.customerGroupId = request.getCustomerGroupId();
        this.reservationId = request.getReservationId();
        copyOperationInfo(request);
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(Integer customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }
}
