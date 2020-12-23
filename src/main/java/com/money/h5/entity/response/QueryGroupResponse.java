package com.money.h5.entity.response;

import com.money.custom.entity.Group;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.dto.H5Group;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryGroupResponse extends ResponseBase {

    @Valid
    @ApiModelProperty(value = "门店列表")
    public List<H5Group> groups;

    public QueryGroupResponse() {}

    public QueryGroupResponse(List<Group> items) {
        if (CollectionUtils.isNotEmpty(items)) {
            this.groups = items.stream().map(H5Group::new).collect(Collectors.toList());
        }
    }

    public List<H5Group> getGroups() {
        return groups;
    }

    public void setGroups(List<H5Group> groups) {
        this.groups = groups;
    }
}
