package com.money.custom.entity;

import com.money.custom.entity.enums.GoodsCombineEnum;
import com.money.custom.entity.enums.ActivityScopeEnum;
import com.money.custom.entity.enums.GoodsTypeEnum;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.request.MoAGoods4ActivityRequest;
import com.money.custom.entity.request.MoAGoods4PackageRequest;
import com.money.custom.entity.request.MoAGoods4SingleRequest;
import com.money.framework.base.entity.BaseEntity;
import com.money.framework.util.DateUtils;
import com.money.framework.util.EnumUtils;
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
    private String serialNumber;
    private Integer scope;
    private Integer maxCntPerCus;
    private String coverImg;
    private Integer sumPrice;

    public Goods() {}

    public static Goods build4SingleAdd(MoAGoods4SingleRequest request, String serialNumber) {
        Goods item = new Goods();
        item.combine = GoodsCombineEnum.SINGLE.getValue();
        item.type = GoodsTypeEnum.SINGLE.getValue();
        item.effectiveDate = DateUtils.nowDate();
        item.expireDate = Consts.SINGLE_GOODS_EXPIRE_DATE;
        item.cnt = 1;
        item.goodsItemTypeCnt = 1;
        item.scope = ActivityScopeEnum.ALL.getValue();
        item.maxCntPerCus = Consts.MAX_CNT_PER_CUSTOMER;


        item.name = request.getName();
        item.setStatus(request.getStatus());
        item.serialNumber = serialNumber;
        item.sumPrice = request.getPrice();
        item.copyOperationInfo(request);
        return item;
    }

    public static Goods build4SingleEdit(MoAGoods4SingleRequest request) {
        Goods item = new Goods();
        item.name = request.getName();
        item.sumPrice = request.getPrice();
        item.setStatus(request.getStatus());
        item.copyOperationInfo(request);
        return item;
    }

    public static Goods build4PackageAdd(MoAGoods4PackageRequest request, String serialNumber) {
        Goods item = new Goods();

        item.combine = GoodsCombineEnum.COMBINE.getValue();
        item.type = GoodsTypeEnum.PACKAGE.getValue();
        item.effectiveDate = DateUtils.nowDate();
        item.expireDate = Consts.PACKAGE_GOODS_EXPIRE_DATE;
        item.goodsItemTypeCnt = 0;
        item.scope = ActivityScopeEnum.ALL.getValue();
        item.maxCntPerCus = Consts.MAX_CNT_PER_CUSTOMER;

        item.setStatus(request.getStatus());
        item.name = request.getName();
        item.desc = request.getDesc();
        item.cnt = request.getCnt();
        item.serialNumber = serialNumber;

        item.copyOperationInfo(request);
        return item;
    }

    public static Goods build4PackageEdit(MoAGoods4PackageRequest request) {
        Goods item = new Goods();

        item.setStatus(request.getStatus());
        item.name = request.getName();
        item.desc = request.getDesc();
        item.cnt = request.getCnt();

        item.copyOperationInfo(request);
        return item;
    }

    public static Goods build4ActivityAdd(MoAGoods4ActivityRequest request) {
        Goods item = new Goods();

        item.combine = GoodsCombineEnum.SINGLE.getValue();
        item.type = GoodsTypeEnum.ACTIVITY.getValue();
        item.effectiveDate = DateUtils.nowDate();
        item.expireDate = request.getExpireDate();
        item.goodsItemTypeCnt = request.getItems().size();
        item.scope = request.getScope();
        item.maxCntPerCus = request.getMaxCntPerCus();

        item.setStatus(request.getStatus());
        item.name = request.getName();
        item.desc = request.getDesc();
        item.cnt = request.getItems().stream().mapToInt(MoAGoods4ActivityRequest.ActivityItem::getCnt).sum();
        item.serialNumber = request.getSerialNumber();

        item.copyOperationInfo(request);
        return item;
    }

    public Integer getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Integer sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getScopeName() {
        return EnumUtils.getNameByValue(ActivityScopeEnum.class, this.scope);
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public Integer getMaxCntPerCus() {
        return maxCntPerCus;
    }

    public void setMaxCntPerCus(Integer maxCntPerCus) {
        this.maxCntPerCus = maxCntPerCus;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
