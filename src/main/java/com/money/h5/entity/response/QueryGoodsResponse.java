package com.money.h5.entity.response;

import com.money.custom.entity.Goods;
import com.money.custom.entity.GoodsTag;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.dto.H5Goods;
import com.money.h5.entity.dto.H5GoodsTag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryGoodsResponse extends ResponseBase {

    @Valid
    @ApiModelProperty(value = "商品列表")
    public List<H5Goods> goods;

    public QueryGoodsResponse() {}

    public QueryGoodsResponse(List<Goods> items) {
        if (CollectionUtils.isNotEmpty(items)) {
            this.goods = items.stream().map(H5Goods::new).collect(Collectors.toList());
        }
    }

    public List<H5Goods> getGoods() {
        return goods;
    }

}
