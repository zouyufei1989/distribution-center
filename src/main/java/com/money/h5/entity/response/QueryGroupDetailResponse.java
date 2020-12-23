package com.money.h5.entity.response;

import com.money.custom.entity.Group;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.dto.H5GroupDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryGroupDetailResponse extends ResponseBase {

    @ApiModelProperty(value = "门店详情")
    public H5GroupDetail detail;

    public QueryGroupDetailResponse(){}

    public QueryGroupDetailResponse(Group group) {
        detail = new H5GroupDetail(group);
    }

    public H5GroupDetail getDetail() {
        return detail;
    }

    public void setDetail(H5GroupDetail detail) {
        this.detail = detail;
    }
}
