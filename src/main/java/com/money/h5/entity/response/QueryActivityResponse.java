package com.money.h5.entity.response;

import com.money.custom.entity.CustomerActivity;
import com.money.framework.base.entity.GridResponseBase;
import com.money.h5.entity.H5GridRequestBase;
import com.money.h5.entity.H5RequestBase;
import com.money.h5.entity.dto.H5Activity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryActivityResponse extends GridResponseBase {

    public QueryActivityResponse() {}

    public QueryActivityResponse(Integer total, Integer records, List<CustomerActivity> items) {
        super(total, records, items.stream().map(H5Activity::new).collect(Collectors.toList()));
    }
}
