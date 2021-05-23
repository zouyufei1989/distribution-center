package com.money.framework.base.exception;

import com.money.custom.entity.enums.ResponseCodeEnum;

public class CustomSpecException extends RuntimeException {

    private String msg;
    private Integer code;

    public CustomSpecException() {}

    public CustomSpecException(String message) {
        super(message);
    }

    public CustomSpecException(Exception ex) {
        super(ex);
    }

    public static CustomSpecException businessError(ResponseCodeEnum errType) {
        return businessError(errType, errType.getName());
    }

    public static CustomSpecException businessError(ResponseCodeEnum errType, String message) {
        CustomSpecException ex = new CustomSpecException(message);
        ex.setMsg(message);
        ex.setCode(errType.getValue());

        return ex;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    public static void main(String[] args) {
        throw CustomSpecException.businessError(ResponseCodeEnum.CUSTOMER_NO_GROUP);
    }
}
