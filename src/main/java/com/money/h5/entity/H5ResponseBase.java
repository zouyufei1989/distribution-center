package com.money.h5.entity;

public class H5ResponseBase {

    private Integer code;
    private String msg;

    private BusinessResponse businessResponse;

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

    public BusinessResponse getBusinessResponse() {
        return businessResponse;
    }

    public void setBusinessResponse(BusinessResponse businessResponse) {
        this.businessResponse = businessResponse;
    }



    static class BusinessResponse{
        private Integer businessCode;
        private String businessMsg;
    }

}
