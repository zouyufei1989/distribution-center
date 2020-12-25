package com.money.h5.entity.response;

import com.money.custom.entity.Goods;
import com.money.framework.base.entity.GridResponseBase;
import com.money.h5.entity.dto.H5Goods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryGoodsResponse extends GridResponseBase {

    public QueryGoodsResponse(){}

    public QueryGoodsResponse(Integer total, Integer records, List<Goods> items) {
        super(total, records, items.stream().map(H5Goods::new).collect(Collectors.toList()));
    }

}
