package com.money.h5.entity.response;

import com.money.custom.entity.Order;
import com.money.custom.entity.enums.GoodsTypeEnum;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.dto.H5Order;
import com.money.h5.entity.dto.H5WalletDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryOrderResponse extends ResponseBase {

    @Valid
    @ApiModelProperty(value = "订单列表")
    private List<H5Order> items;

    public QueryOrderResponse() {}

    public QueryOrderResponse build4PackageAndActivity(List<Order> orders) {
        if (CollectionUtils.isEmpty(orders)) {
            return this;
        }

        items = orders.stream().filter(i -> i.getGoodsTypeId().equals(GoodsTypeEnum.PACKAGE.getValue()) || i.getGoodsTypeId().equals(GoodsTypeEnum.ACTIVITY.getValue()))
                .map(H5Order::new).collect(Collectors.toList());
        return this;
    }

    public List<H5Order> getItems() {
        return items;
    }
}
