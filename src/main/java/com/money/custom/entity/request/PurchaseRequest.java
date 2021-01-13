package com.money.custom.entity.request;

import com.money.custom.entity.enums.PayTypeEnum;
import com.money.framework.base.entity.OperationalEntity;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class PurchaseRequest extends OperationalEntity {

    @NotNull(message = "客户id不可为空")
    private Integer customerGroupId;
    @NotEmpty(message = "请选择要购买的商品")
    private List<Goods> goodsChoosed;
    @NotNull(message = "总金额不可为空")
    private Long sumMoney;
    @NotNull(message = "实付金额不可为空")
    private Long actuallyMoney;
    private Long extraMoneyOffline;

    private Boolean payMoney;
    private Boolean payBonus;
    private Boolean payOffline;

    public Integer getPayType() {
        Integer type = PayTypeEnum.NONE.getValue();
        if (Objects.nonNull(payMoney) && payMoney) {
            type += PayTypeEnum.MONEY.getValue();
        }
        if (Objects.nonNull(payBonus) && payBonus) {
            type += PayTypeEnum.BONUS.getValue();
        }
        if (Objects.nonNull(payOffline) && payOffline) {
            type += PayTypeEnum.OFFLINE.getValue();
        }
        return type;
    }

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

    public Long getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Long sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Long getActuallyMoney() {
        return actuallyMoney;
    }

    public void setActuallyMoney(Long actuallyMoney) {
        this.actuallyMoney = actuallyMoney;
    }

    public Long getExtraMoneyOffline() {
        return Objects.nonNull(extraMoneyOffline) ? extraMoneyOffline : 0;
    }

    public void setExtraMoneyOffline(Long extraMoneyOffline) {
        this.extraMoneyOffline = extraMoneyOffline;
    }

    public void setPayMoney(Boolean payMoney) {
        this.payMoney = payMoney;
    }

    public void setPayBonus(Boolean payBonus) {
        this.payBonus = payBonus;
    }

    public void setPayOffline(Boolean payOffline) {
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
