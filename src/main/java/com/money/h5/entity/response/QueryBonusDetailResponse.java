package com.money.h5.entity.response;

import com.money.custom.entity.BonusWalletDetail;
import com.money.framework.base.entity.GridResponseBase;
import com.money.h5.entity.dto.H5BonusDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryBonusDetailResponse extends GridResponseBase {

    @ApiModelProperty(value = "列表")
    private List<H5BonusDetail> details;

    public QueryBonusDetailResponse() {}

    public QueryBonusDetailResponse(Integer total, Integer records, List<BonusWalletDetail> items) {
        super(total, records, items.stream().map(H5BonusDetail::new).collect(Collectors.toList()));

    }

    public List<H5BonusDetail> getDetails() {
        return details;
    }
}
