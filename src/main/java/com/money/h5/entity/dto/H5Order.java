package com.money.h5.entity.dto;

import com.money.custom.entity.Order;
import com.money.custom.entity.enums.GoodsTypeEnum;
import com.money.custom.entity.enums.GroupReserveFlagEnum;
import com.money.framework.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel(description = "订单信息")
public class H5Order {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "名称")
    private String goodsName;
    @ApiModelProperty(value = "编号")
    private String serialNumber;
    @ApiModelProperty(value = "剩余次数")
    private Integer leftCnt;
    @ApiModelProperty(value = "商品类型")
    private Integer goodsType;
    @ApiModelProperty(value = "商品类型名")
    private String goodsTypeName;
    @ApiModelProperty(value = "封面图")
    private String coverImg;
    @ApiModelProperty(value = "是否可预约")
    private boolean reservable;
    @ApiModelProperty(value = "可预约天数 [今天，今天+reserveDays] ")
    private Integer reserveDays;
    @ApiModelProperty(value = "可预约的最晚一天")
    private String reserveDaysStr;

    public H5Order() {}

    public H5Order(Order item) {
        id = item.getId();
        goodsName = item.getGoodsName();
        serialNumber = item.getGoodsSerialNumber();
        goodsType = item.getGoodsTypeId();
        goodsTypeName = item.getGoodsTypeName();
        coverImg = item.getGoodsCoverImg();
        if (item.getGoodsTypeId().equals(GoodsTypeEnum.PACKAGE.getValue())) {
            leftCnt = item.getItems().get(0).getCnt() - item.getItems().get(0).getCntUsed();
        } else if (item.getGoodsTypeId().equals(GoodsTypeEnum.ACTIVITY.getValue())) {
            leftCnt = item.getItems().stream().mapToInt(i -> i.getCnt() - i.getCntUsed()).sum();
        }

        this.reservable = false;
        if (Objects.nonNull(item.getGroupReserveFlag()) && Objects.nonNull(item.getReservationPeriodCnt())) {
            this.reservable = item.getGroupReserveFlag().equals(GroupReserveFlagEnum.YES.getValue()) && item.getReservationPeriodCnt() > 0 && this.leftCnt > 0;
        }
        this.reserveDays = item.getGroupReserveDays();
        reserveDaysStr = DateUtils.nextNDayStr(this.reserveDays);
    }

    public String getReserveDaysStr() {
        return reserveDaysStr;
    }

    public Integer getReserveDays() {
        return reserveDays;
    }

    public boolean getReservable() {
        return reservable;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getLeftCnt() {
        return leftCnt;
    }

    public void setLeftCnt(Integer leftCnt) {
        this.leftCnt = leftCnt;
    }
}
