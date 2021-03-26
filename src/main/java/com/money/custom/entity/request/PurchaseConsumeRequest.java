package com.money.custom.entity.request;

public class PurchaseConsumeRequest extends PurchaseRequest {

    private Integer reservationId;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }
}
