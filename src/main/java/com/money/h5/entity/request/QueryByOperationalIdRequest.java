package com.money.h5.entity.request;

import com.money.h5.entity.H5GridRequestBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@ApiModel
public class QueryByOperationalIdRequest extends H5GridRequestBase {

    @ApiModelProperty(value = "id")
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
