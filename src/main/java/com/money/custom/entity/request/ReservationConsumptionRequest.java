package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ReservationConsumptionRequest extends OperationalEntity {

    @NotNull(message = "未指定顾客")
    private Integer customerGroupId;
    @NotNull(message = "未指定预约")
    private Integer reservationId;
    @NotNull(message = "请指定消费项目")
    private Integer orderId;
    @NotNull(message = "请指定消耗次数")
    @Min(value = 1,message = "最小消耗次数1次")
    private Integer cnt;

    private List<ConsumeRequest> consumeRequests;
    private PurchaseConsumeRequest purchaseConsumeRequest;

    public List<ConsumeRequest> getConsumeRequests() {
        return consumeRequests;
    }

    public void setConsumeRequests(List<ConsumeRequest> consumeRequests) {
        this.consumeRequests = consumeRequests;
    }

    public PurchaseConsumeRequest getPurchaseConsumeRequest() {
        return purchaseConsumeRequest;
    }

    public void setPurchaseConsumeRequest(PurchaseConsumeRequest purchaseConsumeRequest) {
        this.purchaseConsumeRequest = purchaseConsumeRequest;
    }

    public Integer getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(Integer customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
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
