package com.money.custom.entity.request;

import com.money.custom.entity.User;

import java.util.Map;

public class QueryUserRequest extends QueryGridRequestBase {

    private User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("user", user);
        return params;
    }

    public void setPassengerId(String passengerId) {
        this.user.setPassengerId(passengerId);
    }

    public void setNickName(String nickName) {
        this.user.setNickName(nickName);
    }

    public void setUsername(String username){
        user.setUsername(username);
    }
}
