package com.money.custom.entity.request;

import com.money.custom.entity.enums.ReservationStatusEnum;

import java.util.List;

public class ChangeReservationStatusRequest extends ChangeStatusBaseRequest<ReservationStatusEnum> {

    public ChangeReservationStatusRequest() {}

    public ChangeReservationStatusRequest(String id, Integer status) {
        super(id, status);
    }

    public ChangeReservationStatusRequest(List<String> ids, Integer status) {
        super(ids, status);
    }

}
