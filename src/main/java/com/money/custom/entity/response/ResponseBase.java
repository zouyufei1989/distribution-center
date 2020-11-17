package com.money.custom.entity.response;

import com.money.custom.entity.enums.ResponseCodeEnum;

public class ResponseBase {

    private String message = "";
    private Integer code = ResponseCodeEnum.SUCCESS.getValue();

    public ResponseBase() {}

    public ResponseBase(String message, ResponseCodeEnum code) {
        this.message = message;
        this.code = code.getValue();
    }

    public boolean success(){
        return code.equals(ResponseCodeEnum.SUCCESS.getValue());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
