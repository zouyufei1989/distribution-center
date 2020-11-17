package com.money.h5.entity;

public class H5RequestBase {

    private String sign;
    private Long timestamp;

    public String getSign() {
        return sign;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
