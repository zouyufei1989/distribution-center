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

    public String getJsCode() {
        return jsCode;
    }

    public void setJsCode(String jsCode) {
        this.jsCode = jsCode;
    }
}
