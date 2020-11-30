package com.money.framework.base.exception;

import com.money.custom.entity.Consts;

public class PandabusSpecException extends RuntimeException {

    private String msg;

    public static PandabusSpecException serverError() {
        return serverError(Consts.COMMON_ERR_MSG);
    }

    public static PandabusSpecException illegalArgumentError() {
        return serverError(Consts.ARGS_ERR_MSG);
    }

    public static PandabusSpecException serverError(String msg) {
        return new PandabusSpecException(msg);
    }

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
