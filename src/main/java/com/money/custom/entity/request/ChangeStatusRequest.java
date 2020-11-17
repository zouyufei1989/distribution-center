package com.money.custom.entity.request;

import com.money.custom.entity.enums.CommonStatusEnum;

import java.util.List;

public class ChangeStatusRequest extends ChangeStatusBaseRequest<CommonStatusEnum> {

    public ChangeStatusRequest() {}

    public ChangeStatusRequest(String id, Integer status) {
        super(id, status);
    }

    public ChangeStatusRequest(List<String> ids, Integer status) {
        super(ids, status);
    }

}
