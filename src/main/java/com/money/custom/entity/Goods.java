package com.money.custom.entity;

import com.money.custom.entity.enums.GoodsCombineEnum;
import com.money.custom.entity.enums.GoodsTypeEnum;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.request.MoAGoods4PackageRequest;
import com.money.custom.entity.request.MoAGoods4SingleRequest;
import com.money.framework.base.entity.BaseEntity;
import com.money.framework.util.DateUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Objects;

public class Goods extends BaseEntity {

    private Integer id;
    private String name;
    private Integer combine;
    private Integer type;
    private List<GoodsItem> items;
    private String effectiveDate;
    private String expireDate;
    private String desc;
    private Integer cnt;
    private Integer goodsItemTypeCnt;

    public Goods() {}

    public Goods(MoAGoods4SingleRequest request) {
        this.name = request.getName();
        this.combine = GoodsCombineEnum.SINGLE.getValue();
        this.type = GoodsTypeEnum.SINGLE.getValue();
        this.effectiveDate = DateUtils.nowDate();
        this.expireDate = Consts.SINGLE_GOODS_EXPIRE_DATE;
        this.cnt = 1;
        this.goodsItemTypeCnt = 1;
        this.setStatus(request.getStatus());
        copyOperationInfo(request);
    }

    public Goods(MoAGoods4PackageRequest request) {
        this.name = request.getName();
        this.desc = request.getDesc();
        this.combine = GoodsCombineEnum.COMBINE.getValue();
        this.type = GoodsTypeEnum.PACKAGE.getValue();
        this.effectiveDate = DateUtils.nowDate();
        this.expireDate = Consts.PACKAGE_GOODS_EXPIRE_DATE;
        this.cnt = request.getCnt();
        this.goodsItemTypeCnt = 0;
        this.setStatus(request.getStatus());
        copyOperationInfo(request);
    }

    public Integer getGoodsItemTypeCnt() {
        return goodsItemTypeCnt;
    }

    public void setGoodsItemTypeCnt(Integer goodsItemTypeCnt) {
        this.goodsItemTypeCnt = goodsItemTypeCnt;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc4SingleShow() {
        if (Objects.isNull(this.type) || !this.type.equals(GoodsTypeEnum.SINGLE.getValue())) {
            return StringUtils.EMPTY;
        }
        return items.get(0).getDesc();
    }

    public String getProfitRate4SingleShow() {
        if (Objects.isNull(this.type) || !this.type.equals(GoodsTypeEnum.SINGLE.getValue())) {
            return StringUtils.EMPTY;
        }
        return items.get(0).getProfitRate4Show();
    }

    public String getPrice4SingleShow() {
        if (Objects.isNull(this.type) || !this.type.equals(GoodsTypeEnum.SINGLE.getValue())) {
            return StringUtils.EMPTY;
        }
        return items.get(0).getPrice4Show();
    }

    public String getGoodsTagName4SingleShow() {
        if (Objects.isNull(this.type) || !this.type.equals(GoodsTypeEnum.SINGLE.getValue())) {
            return StringUtils.EMPTY;
        }
        return items.get(0).getGoodsTagName();
    }

    public String getUnit4SingleShow() {
        if (Objects.isNull(this.type) || !this.type.equals(GoodsTypeEnum.SINGLE.getValue())) {
            return StringUtils.EMPTY;
        }
        return items.get(0).getUnit();
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public List<GoodsItem> getItems() {
        return items;
    }

    public void setItems(List<GoodsItem> items) {
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCombine() {
        return combine;
    }

    public void setCombine(Integer combine) {
        this.combine = combine;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHistoryType() {
        return HistoryEntityEnum.GOODS.getName();
    }
}
