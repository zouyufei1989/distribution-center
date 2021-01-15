package com.money.h5.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@ApiModel
public class H5RequestBase {

    private String sign;
    private Long timestamp;
    @ApiModelProperty(value = "用户openId", example = "abc")
    @NotBlank(message = "openId不可为空")
    private String openId;
    private String phone;
    @ApiModelProperty(hidden = true)
    private boolean debug;

    public Map<String, Object> buildParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("openId", openId);
        return params;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
