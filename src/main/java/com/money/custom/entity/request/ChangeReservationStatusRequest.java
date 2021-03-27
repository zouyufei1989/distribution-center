package com.money.custom.entity.request;

import com.money.custom.entity.enums.ReservationStatusEnum;

import java.util.List;
import java.util.Map;

public class ChangeReservationStatusRequest extends ChangeStatusBaseRequest<ReservationStatusEnum> {

    private ReservationStatusEnum srcStatus;

    public ChangeReservationStatusRequest() {}

    public ChangeReservationStatusRequest(String id, Integer status) {
        super(id, status);
    }

    public ChangeReservationStatusRequest(List<String> ids, Integer status) {
        super(ids, status);
    }

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("srcStatus",srcStatus.getValue());
        return params;
    }

    public ReservationStatusEnum getSrcStatus() {
        return srcStatus;
    }

    public void setSrcStatus(ReservationStatusEnum srcStatus) {
        this.srcStatus = srcStatus;
    }
}
