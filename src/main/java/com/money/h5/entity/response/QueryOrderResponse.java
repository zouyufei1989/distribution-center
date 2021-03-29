package com.money.h5.entity.response;

import com.money.custom.entity.Order;
import com.money.custom.entity.enums.GoodsTypeEnum;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.dto.H5Order;
import io.swagger.annotations.ApiModel;

import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryOrderResponse extends GridResponseBase {

    public QueryOrderResponse() {}

    public QueryOrderResponse(Integer records, Integer total, List<Order> items) {
        super(total, records, items.stream().map(H5Order::new).collect(Collectors.toList()));
    }
}
