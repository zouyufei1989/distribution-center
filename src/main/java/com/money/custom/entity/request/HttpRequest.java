package com.money.custom.entity.request;

import com.gexin.fastjson.JSON;
import com.money.custom.entity.enums.HttpLogTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

public class HttpRequest {

    private String url;
    private Object param;
    private Map<String, String> paramMap;
    private Map<String, String> headers;

    public HttpRequest(String url, HttpLogTypeEnum logTypeEnum) {
        this.url = url;
        this.logTypeEnum = logTypeEnum;
    }

    public HttpRequest(String url, Object param, HttpLogTypeEnum logTypeEnum) {
        this.url = url;
        this.param = param;
        this.logTypeEnum = logTypeEnum;
    }

    public HttpRequest(String url, Map<String, String> paramMap, HttpLogTypeEnum logTypeEnum) {
        this.url = url;
        this.paramMap = paramMap;
        this.logTypeEnum = logTypeEnum;
    }

    private HttpLogTypeEnum logTypeEnum;

    public String getParamStr() {
        if (Objects.nonNull(param)) {
            return JSON.toJSONString(param);
        }
        if (Objects.nonNull(paramMap)) {
            return JSON.toJSONString(paramMap);
        }

        return StringUtils.EMPTY;
    }

    public String getHeaderStr() {
        if (Objects.isNull(headers)) {
            return StringUtils.EMPTY;
        }

        return JSON.toJSONString(headers);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public HttpLogTypeEnum getLogTypeEnum() {
        return logTypeEnum;
    }

    public void setLogTypeEnum(HttpLogTypeEnum logTypeEnum) {
        this.logTypeEnum = logTypeEnum;
    }
}
