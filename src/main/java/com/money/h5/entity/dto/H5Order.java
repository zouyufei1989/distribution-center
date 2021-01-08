package com.money.h5.entity.dto;

import com.money.custom.entity.Order;
import com.money.custom.entity.enums.GoodsTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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

    public H5Order() {}

    public H5Order(Order item) {
        id = item.getId();
        goodsName = item.getGoodsName();
        serialNumber = item.getSerialNumber();
        goodsType = item.getGoodsTypeId();
        goodsTypeName = item.getGoodsTypeName();
        coverImg = item.getGoodsCoverImg();
        if (item.getGoodsTypeId().equals(GoodsTypeEnum.PACKAGE.getValue())) {
            leftCnt = item.getItems().get(0).getCnt() - item.getItems().get(0).getCntUsed();
        } else if (item.getGoodsTypeId().equals(GoodsTypeEnum.ACTIVITY.getValue())) {
            leftCnt = item.getItems().stream().mapToInt(i -> i.getCnt() - i.getCntUsed()).sum();
        }
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
