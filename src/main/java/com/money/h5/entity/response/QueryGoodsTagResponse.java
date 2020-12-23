package com.money.h5.entity.response;

import com.money.custom.entity.GoodsTag;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.dto.H5GoodsTag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryGoodsTagResponse extends ResponseBase {

    @Valid
    @ApiModelProperty(value = "商品类型列表")
    public List<H5GoodsTag> tags;

    public QueryGoodsTagResponse() {}

    public QueryGoodsTagResponse(List<GoodsTag> items) {
        if (CollectionUtils.isNotEmpty(items)) {
            this.tags = items.stream().map(H5GoodsTag::new).collect(Collectors.toList());
        }
    }

    public List<H5GoodsTag> getTags() {
        return tags;
    }
}
