package com.money.h5.entity.request;

import com.money.h5.entity.H5RequestBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel("通过小程序获取手机号")
public class GainPhoneRequest extends H5RequestBase {

    @ApiModelProperty(value = "包括敏感数据在内的完整用户信息的加密数据")
    private String rawData;
    @ApiModelProperty(value = "加密算法的初始向量")
    private String iv;

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
