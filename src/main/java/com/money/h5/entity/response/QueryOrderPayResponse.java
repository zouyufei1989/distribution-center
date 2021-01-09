package com.money.h5.entity.response;

import com.money.custom.entity.OrderPay;
import com.money.framework.base.entity.GridResponseBase;
import com.money.h5.entity.dto.H5PayInfo;
import io.swagger.annotations.ApiModel;

import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryOrderPayResponse extends GridResponseBase {

    public QueryOrderPayResponse() {}

    public QueryOrderPayResponse(Integer total, Integer records, List<OrderPay> orderConsumptions) {
        super(total, records, orderConsumptions.stream().map(H5PayInfo::new).collect(Collectors.toList()));

    }

}
