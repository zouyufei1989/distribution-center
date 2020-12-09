package com.money.framework.base.exception;

import com.money.custom.entity.enums.ResponseCodeEnum;

public class PandabusSpecException extends RuntimeException {

    private String msg;
    private Integer code;

    public PandabusSpecException() {}

    public PandabusSpecException(Exception ex) {
        super(ex);
    }

    public static PandabusSpecException businessError(ResponseCodeEnum errType) {
        return businessError(errType,errType.getName());
    }

    public static PandabusSpecException businessError(ResponseCodeEnum errType, String message) {
        PandabusSpecException ex = new PandabusSpecException();
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
}
