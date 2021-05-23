package com.money.h5.entity.request;

import com.money.h5.entity.H5RequestBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

@ApiModel("微信小程序单点登录")
public class LoginRequest extends H5RequestBase {

    @ApiModelProperty(value = "临时登录凭证code")
    @NotEmpty(message = "jsCode不可为空")
    private String jsCode;

    @ApiModelProperty(value = "顾客不存在，是否自动创建")
    private boolean autoCreate = false;

    public String getJsCode() {
        return jsCode;
    }

    public void setJsCode(String jsCode) {
        this.jsCode = jsCode;
    }

    public boolean isAutoCreate() {
        return autoCreate;
    }

    public void setAutoCreate(boolean autoCreate) {
        this.autoCreate = autoCreate;
    }
}
