package com.money.framework.base.entity;

import com.money.custom.entity.enums.ResponseCodeEnum;
import com.money.framework.base.exception.PandabusSpecException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ResponseBase {

    @ApiModelProperty(value = "状态码 0-成功")
    private Integer code = ResponseCodeEnum.SUCCESS.getValue();
    private String message = ResponseCodeEnum.SUCCESS.getName();
    private Boolean success = true;
    private Object data;
    private Object extraData;

    public static ResponseBase success() {
        return new ResponseBase();
    }

    public static ResponseBase success(Object data) {
        ResponseBase responseBase = new ResponseBase();
        responseBase.setData(data);
        return responseBase;
    }

    public static ResponseBase success(Object data,Object extraData) {
        ResponseBase responseBase = new ResponseBase();
        responseBase.setData(data);
        responseBase.setExtraData(extraData);
        return responseBase;
    }

    public static ResponseBase error(ResponseCodeEnum type) {
        ResponseBase responseBase = new ResponseBase();
        responseBase.success = false;
        responseBase.code = type.getValue();
        responseBase.message = type.getName();
        return responseBase;
    }

    public ResponseBase error(Throwable ex) {
        this.success = false;
        if (ex instanceof PandabusSpecException) {
            this.code = ((PandabusSpecException) ex).getCode();
            this.message = ((PandabusSpecException) ex).getMsg();
            return this;
        }
        if (ex instanceof IllegalArgumentException) {
            this.code = ResponseCodeEnum.INVALID_ARGUMENT.getValue();
            this.message = ex.getMessage();
            return this;
        }

        this.code = ResponseCodeEnum.ERROR.getValue();
        this.message = ResponseCodeEnum.ERROR.getName();
        return this;
    }

    public Object getExtraData() {
        return extraData;
    }

    public void setExtraData(Object extraData) {
        this.extraData = extraData;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
