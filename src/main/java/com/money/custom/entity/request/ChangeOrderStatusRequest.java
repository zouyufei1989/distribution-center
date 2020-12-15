package com.money.custom.entity.request;

import com.money.custom.entity.enums.OrderStatusEnum;

import java.util.List;

public class ChangeOrderStatusRequest extends ChangeStatusBaseRequest<OrderStatusEnum> {

    public ChangeOrderStatusRequest() {}

    public ChangeOrderStatusRequest(String id, Integer status) {
        super(id, status);
    }

    public ChangeOrderStatusRequest(List<String> ids, Integer status) {
        super(ids, status);
    }

}
