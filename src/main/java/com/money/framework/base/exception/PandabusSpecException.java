package com.money.framework.base.exception;

public class PandabusSpecException extends RuntimeException {

    private String msg;

    public PandabusSpecException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public PandabusSpecException(String msg, Exception ex) {
        super(msg, ex);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
