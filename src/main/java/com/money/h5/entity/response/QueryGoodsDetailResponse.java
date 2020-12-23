package com.money.h5.entity.response;

import com.money.custom.entity.Goods;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.dto.H5Goods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryGoodsDetailResponse extends ResponseBase {

    @Valid
    @ApiModelProperty(value = "商品")
    public H5Goods goods;

    public QueryGoodsDetailResponse() {}

    public QueryGoodsDetailResponse(Goods item) {
        goods = new H5Goods();
        goods.setName(item.getName());
        goods.setDetail(item.getItems().get(0).getDetail());
    }

    public H5Goods getGoods() {
        return goods;
    }
}
