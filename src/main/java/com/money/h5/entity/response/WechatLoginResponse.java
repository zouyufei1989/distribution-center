package com.money.h5.entity.response;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class WechatLoginResponse {
    private String openId;
    private String session_key;
    private String unionid;
    private Integer errcode;
    private String errmsg;

    public boolean success() {
        return Objects.isNull(errcode) || errcode.equals(0);
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
