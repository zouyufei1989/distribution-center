package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AssignGoods4PackageRequest extends OperationalEntity {

    @NotNull(message = "套餐id不可为空")
    private Integer goodsId;
    @NotEmpty(message = "未选中任何商品")
    private List<String> goodsItemIdList;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public List<String> getGoodsItemIdList() {
        return goodsItemIdList;
    }

    public void setGoodsItemIdList(List<String> goodsItemIdList) {
        this.goodsItemIdList = goodsItemIdList;
    }
}
