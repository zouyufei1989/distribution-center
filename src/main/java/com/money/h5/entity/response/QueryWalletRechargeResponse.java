package com.money.h5.entity.response;

import com.money.custom.entity.Customer;
import com.money.custom.entity.WalletDetail;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.dto.H5Banner;
import com.money.h5.entity.dto.H5WalletDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryWalletRechargeResponse extends GridResponseBase {

    @ApiModelProperty(value = "过期时间")
    private String expireDate;
    @ApiModelProperty(value = "积分比例")
    private Integer bonusRate;

    public QueryWalletRechargeResponse() {}

    public QueryWalletRechargeResponse(Integer total, Integer records, List<WalletDetail> items, Customer customer) {
        super(total, records, items.stream().map(H5WalletDetail::new).collect(Collectors.toList()));
        this.expireDate = customer.getCustomerGroup().getExpireDate();
        this.bonusRate = customer.getBonusPlan().getBonusRate();
    }

}
