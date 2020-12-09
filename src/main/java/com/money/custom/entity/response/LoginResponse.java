package com.money.custom.entity.response;

import com.money.custom.entity.User;
import com.money.framework.base.entity.ResponseBase;

public class LoginResponse extends ResponseBase {

    private User loginUser;

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }
}
