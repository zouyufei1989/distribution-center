package com.money.h5.entity;

import io.swagger.annotations.ApiModel;

@ApiModel
public class H5RequestBase {

    private String sign;
    private Long timestamp;
    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
