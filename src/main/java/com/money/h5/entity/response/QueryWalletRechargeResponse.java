package com.money.h5.entity.response;

import com.money.custom.entity.WalletDetail;
import com.money.framework.base.entity.GridResponseBase;
import com.money.h5.entity.dto.H5WalletDetail;
import io.swagger.annotations.ApiModel;

import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryWalletRechargeResponse extends GridResponseBase {

    public QueryWalletRechargeResponse() {}

    public QueryWalletRechargeResponse(Integer total, Integer records, List<WalletDetail> items) {
        super(total, records, items.stream().map(H5WalletDetail::new).collect(Collectors.toList()));
    }

}
