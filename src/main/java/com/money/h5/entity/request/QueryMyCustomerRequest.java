package com.money.h5.entity.request;

import com.money.h5.entity.H5GridRequestBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel
public class QueryMyCustomerRequest extends H5GridRequestBase {

    @ApiModelProperty(value = "姓名或手机")
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
