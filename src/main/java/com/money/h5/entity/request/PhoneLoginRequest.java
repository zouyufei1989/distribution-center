package com.money.h5.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("微信手机号单点登录")
public class PhoneLoginRequest extends TransWechatInfo2CustomerRequest {

    @ApiModelProperty(value = "顾客不存在，是否自动创建")
    private boolean autoCreate = false;

    public boolean isAutoCreate() {
        return autoCreate;
    }

    public void setAutoCreate(boolean autoCreate) {
        this.autoCreate = autoCreate;
    }

}
