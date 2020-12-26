package com.money.h5.entity.response;

import com.money.custom.entity.Goods;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.dto.H5ActivityDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryActivityDetailResponse extends ResponseBase {

    @ApiModelProperty(value = "总价值")
    private Integer totalPrice;
    @ApiModelProperty(value = "详情列表")
    private List<H5ActivityDetail> details;

    public QueryActivityDetailResponse() {}

    public QueryActivityDetailResponse(Goods goods) {
        totalPrice = goods.getItems().stream().mapToInt(i -> i.getPrice() * i.getCnt()).sum();
        details = goods.getItems().stream().map(H5ActivityDetail::new).collect(Collectors.toList());;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public List<H5ActivityDetail> getDetails() {
        return details;
    }
}
