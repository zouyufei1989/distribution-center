package com.money.custom.entity.response;

import com.money.custom.entity.User;

public class LoginResponse extends ResponseBase {

    private User loginUser;

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }
}
