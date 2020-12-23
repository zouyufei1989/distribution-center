package com.money.h5.entity.response;

import com.money.custom.entity.Group;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.dto.GroupDetail;
import com.money.h5.entity.dto.H5Group;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryGroupDetailResponse extends ResponseBase {

    @ApiModelProperty(value = "门店详情")
    public GroupDetail detail;

    public QueryGroupDetailResponse(Group group) {
        detail = new GroupDetail(group);
    }

    public GroupDetail getDetail() {
        return detail;
    }

    public void setDetail(GroupDetail detail) {
        this.detail = detail;
    }
}
