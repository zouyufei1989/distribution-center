package com.money.h5.entity.request;

import com.money.h5.entity.H5GridRequestBase;
import com.money.h5.entity.H5RequestBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@ApiModel
public class QueryByIdRequest extends H5GridRequestBase {

    @ApiModelProperty(value = "id")
    @NotBlank(message = "id不可为空")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("id", id);
        return params;
    }
}
