package com.money.h5.entity;

import com.money.custom.entity.enums.ResponseCodeEnum;
import com.money.framework.base.exception.PandabusSpecException;
import net.bytebuddy.implementation.bytecode.Throw;

public class H5ResponseBase {

    private Integer code;
    private String msg;

    public static H5ResponseBase error(PandabusSpecException ex) {
        H5ResponseBase response = new H5ResponseBase();
        response.setCode(ex.getCode());
        response.setMsg(ex.getMsg());
        return response;
    }

    public static H5ResponseBase error(IllegalArgumentException ex) {
        H5ResponseBase response = new H5ResponseBase();
        response.setCode(ResponseCodeEnum.ERROR.getValue());
        response.setMsg(ex.getMessage());
        return response;
    }

    public static H5ResponseBase error(Throwable ex) {
        H5ResponseBase response = new H5ResponseBase();
        response.setCode(ResponseCodeEnum.ERROR.getValue());
        response.setMsg("服务器异常");
        return response;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
