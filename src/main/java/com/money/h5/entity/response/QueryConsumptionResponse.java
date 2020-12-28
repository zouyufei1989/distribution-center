package com.money.h5.entity.response;

import com.money.custom.entity.OrderConsumption;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.H5GridRequestBase;
import com.money.h5.entity.dto.H5Activity;
import com.money.h5.entity.dto.H5Consumption;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryConsumptionResponse extends GridResponseBase {

    @ApiModelProperty(value = "列表")
    private List<H5Consumption> details;

    public QueryConsumptionResponse() {}

    public QueryConsumptionResponse(Integer total, Integer records, List<OrderConsumption> orderConsumptions) {
        super(total, records, orderConsumptions.stream().map(H5Consumption::new).collect(Collectors.toList()));

    }

    public List<H5Consumption> getDetails() {
        return details;
    }
}
