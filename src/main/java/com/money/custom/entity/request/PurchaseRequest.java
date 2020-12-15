package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PurchaseRequest extends OperationalEntity {

    @NotNull(message = "客户id不可为空")
    private Integer customerGroupId;
    @NotEmpty(message = "请选择要购买的商品")
    private List<Goods> goodsChoosed;
    @NotNull(message = "总金额不可为空")
    private Integer sumMoney;
    @NotNull(message = "实付金额不可为空")
    private Integer actuallyMoney;
    private Integer extraMoneyOffline;

    private Integer payMoney;
    private Integer payBonus;
    private Integer payOffline;

    public List<Goods> getGoodsChoosed() {
        return goodsChoosed;
    }

    public Integer getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(Integer customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    public void setGoodsChoosed(List<Goods> goodsChoosed) {
        this.goodsChoosed = goodsChoosed;
    }

    public Integer getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Integer sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Integer getActuallyMoney() {
        return actuallyMoney;
    }

    public void setActuallyMoney(Integer actuallyMoney) {
        this.actuallyMoney = actuallyMoney;
    }

    public Integer getExtraMoneyOffline() {
        return extraMoneyOffline;
    }

    public void setExtraMoneyOffline(Integer extraMoneyOffline) {
        this.extraMoneyOffline = extraMoneyOffline;
    }

    public Integer getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Integer payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getPayBonus() {
        return payBonus;
    }

    public void setPayBonus(Integer payBonus) {
        this.payBonus = payBonus;
    }

    public Integer getPayOffline() {
        return payOffline;
    }

    public void setPayOffline(Integer payOffline) {
        this.payOffline = payOffline;
    }

    @Valid
    public static class Goods {
        @NotNull(message = "商品id不可为空")
        private Integer id;
        @NotNull(message = "商品数量不可为空")
        @Min(value = 1, message = "商品数量最小为1")
        private Integer cnt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getCnt() {
            return cnt;
        }

        public void setCnt(Integer cnt) {
            this.cnt = cnt;
        }
    }

}
