package com.money.custom.entity;

import com.money.framework.base.annotation.IgnoreXss;
import com.money.framework.base.entity.BaseEntity;
import com.money.framework.util.DateUtils;

@IgnoreXss
public class ExceptionLog extends BaseEntity {

    private Integer id;
    private String method;
    private String errorMessage;
    private String stackTrace = "";
    private String errorDate;
    private Integer cnt;

    public ExceptionLog() {}

    public ExceptionLog(String line) {
        String[] strings = line.split("#-#");
        this.errorDate = DateUtils.nowDate() + " " + strings[1];
        this.method = strings[strings.length - 1];
    }

    public void appendStackTrace(String line) {
        this.stackTrace += line + "\r\n";
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(String errorDate) {
        this.errorDate = errorDate;
    }
}
