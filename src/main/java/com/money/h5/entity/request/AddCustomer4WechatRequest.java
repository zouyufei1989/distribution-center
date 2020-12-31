package com.money.h5.entity.request;

import com.money.h5.entity.H5RequestBase;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddCustomer4WechatRequest extends H5RequestBase {

    private String openId;

    @Override
    public String getOpenId() {
        return openId;
    }

    @Override
    public void setOpenId(String openId) {
        this.openId = openId;
    }

}
