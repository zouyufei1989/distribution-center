package com.money.h5.entity.response;

import com.money.custom.entity.Customer;
import com.money.custom.entity.WalletDetail;
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
public class QueryWalletRechargeResponse extends ResponseBase {

    @Valid
    @ApiModelProperty(value = "充值记录列表")
    private List<H5WalletDetail> details;

    @ApiModelProperty(value = "过期时间")
    private String expireDate;
    @ApiModelProperty(value = "积分比例")
    private Integer bonusRate;

    public QueryWalletRechargeResponse(){}

    public QueryWalletRechargeResponse(List<WalletDetail> items, Customer customer) {
        if(CollectionUtils.isNotEmpty(items)){
            details = items.stream().map(H5WalletDetail::new).collect(Collectors.toList());
        }
        this.expireDate = customer.getCustomerGroup().getExpireDate();
        this.bonusRate = customer.getBonusPlan().getBonusRate();
    }

    public List<H5WalletDetail> getDetails() {
        return details;
    }

    public void setDetails(List<H5WalletDetail> details) {
        this.details = details;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public Integer getBonusRate() {
        return bonusRate;
    }
}
