package com.money.custom.entity.response;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.money.framework.base.entity.ResponseBase;
import org.apache.commons.lang3.StringUtils;

public class SendSmsResponse extends ResponseBase {

    static final String SUCCESS_CODE = "OK";

    private String request;
    private String response;
    private boolean success;

    public SendSmsResponse() {}

    public SendSmsResponse(CommonRequest request, Exception ex) {
        this.request = JSON.toJSONString(request.getQueryParameters());
        this.response = ex.getMessage();
        this.success = false;
    }

    public SendSmsResponse(CommonRequest request, CommonResponse response) {
        this.request = JSON.toJSONString(request.getQueryParameters());
        this.response = response.getData();
        this.success = StringUtils.equals(JSON.parseObject(response.getData()).getString("Code"), SUCCESS_CODE);
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
