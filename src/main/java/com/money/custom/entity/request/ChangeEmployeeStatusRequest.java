package com.money.custom.entity.request;

import com.money.custom.entity.enums.CommonStatusEnum;
import com.money.custom.entity.enums.EmployeeStatusEnum;

import java.util.List;

public class ChangeEmployeeStatusRequest extends ChangeStatusBaseRequest<EmployeeStatusEnum> {

    public ChangeEmployeeStatusRequest() {}

    public ChangeEmployeeStatusRequest(String id, Integer status) {
        super(id, status);
    }

    public ChangeEmployeeStatusRequest(List<String> ids, Integer status) {
        super(ids, status);
    }

}
