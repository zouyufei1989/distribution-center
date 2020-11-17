package com.money.custom.entity.response;

import com.money.custom.entity.User;

public class LoginResponse extends ResponseBase {

    private User loginUser;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }
}
