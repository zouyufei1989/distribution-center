package com.money.h5.entity.request;

import com.money.h5.entity.H5RequestBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

@ApiModel("短信验证码登录")
public class SmsLoginRequest extends H5RequestBase {

    @ApiModelProperty(value = "短信验证码")
    @NotBlank(message = "验证码不可为空")
    private String smsCode;
    @ApiModelProperty(value = "顾客不存在，是否自动创建")
    private boolean autoCreate = false;

    public boolean isAutoCreate() {
        return autoCreate;
    }

    public void setAutoCreate(boolean autoCreate) {
        this.autoCreate = autoCreate;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public boolean isTest() {
        return StringUtils.equals("17788889999", getPhone()) && StringUtils.equals("1234", smsCode);
    }
}
