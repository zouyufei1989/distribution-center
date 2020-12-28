package com.money.h5.entity.request;

import com.money.h5.entity.H5RequestBase;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddCustomer4WechatRequest extends H5RequestBase {

    private String nickName;
    private String headCover;
    private Integer groupId;
    private String openId;

    @Override
    public String getOpenId() {
        return openId;
    }

    @Override
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadCover() {
        return headCover;
    }

    public void setHeadCover(String headCover) {
        this.headCover = headCover;
    }
}
