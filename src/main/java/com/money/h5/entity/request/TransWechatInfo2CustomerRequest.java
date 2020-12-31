package com.money.h5.entity.request;

import com.money.h5.entity.H5RequestBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel
public class TransWechatInfo2CustomerRequest extends H5RequestBase {
    @ApiModelProperty(value = "微信昵称")
    private String nickName;
    @ApiModelProperty(value = "微信头像")
    private String avatarUrl;

    @ApiModelProperty(value = "包括敏感数据在内的完整用户信息的加密数据")
    private String rawData;
    @ApiModelProperty(value = "加密算法的初始向量")
    private String iv;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}
