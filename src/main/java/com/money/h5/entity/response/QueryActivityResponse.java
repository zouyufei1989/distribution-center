package com.money.h5.entity.response;

import com.money.custom.entity.CustomerActivity;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.dto.H5Activity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryActivityResponse extends ResponseBase {

    @Valid
    @ApiModelProperty(value = "活动列表")
    public List<H5Activity> activities;

    public QueryActivityResponse() {}

    public QueryActivityResponse(List<CustomerActivity> items) {
        if (CollectionUtils.isNotEmpty(items)) {
            this.activities = items.stream().map(H5Activity::new).collect(Collectors.toList());
        }
    }

    public List<H5Activity> getActivities() {
        return activities;
    }
}
