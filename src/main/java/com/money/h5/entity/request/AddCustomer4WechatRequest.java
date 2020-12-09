package com.money.h5.entity.request;

import com.money.h5.entity.H5RequestBase;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddCustomer4WechatRequest extends H5RequestBase {

    @NotBlank(message = "openId不可为空")
    private String openId;
    private String nickName;
    private String headCover;
    @NotNull(message = "门店id不可为空")
    private Integer groupId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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
