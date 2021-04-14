package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

import javax.validation.constraints.NotNull;

public class AddOrderRequest extends OperationalEntity {

    @NotNull(message = "商品id不可为空")
    private Integer goodsId;
    @NotNull(message = "商品数量不可为空")
    private Integer cnt;
    private String batchId;
    @NotNull(message = "客户id不可为空")
    private Integer customerGroupId;

    public Integer getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(Integer customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
}
