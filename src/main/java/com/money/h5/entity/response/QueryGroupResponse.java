package com.money.h5.entity.response;

import com.money.custom.entity.Group;
import com.money.framework.base.entity.GridResponseBase;
import com.money.h5.entity.dto.H5Group;
import io.swagger.annotations.ApiModel;

import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryGroupResponse extends GridResponseBase {

    public QueryGroupResponse() {}

    public QueryGroupResponse(Integer total, Integer records, List<Group> items) {
        super(total, records, items.stream().map(H5Group::new).collect(Collectors.toList()));
    }

}
