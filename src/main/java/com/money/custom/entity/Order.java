package com.money.custom.entity;

import com.money.custom.entity.enums.GoodsTypeEnum;
import com.money.custom.entity.enums.OrderStatusEnum;
import com.money.custom.entity.request.AddOrderRequest;
import com.money.framework.base.entity.BaseEntity;
import com.money.framework.util.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.UUID;

public class Order extends BaseEntity {

    private Integer id;
    private String batchId;

    private Integer customerGroupId;
    private Integer goodsId;
    private String goodsName;
    private Integer goodsCombine;
    private Integer goodsTypeId;
    private String goodsTypeName;
    private String expireDate;
    private String goodsDesc;
    private Integer goodsCnt;
    private Integer goodsItemTypeCnt;
    private Integer goodsSumPrice;

    private Integer orderPrice;
    private Integer orderCnt;

    private List<OrderItem> items;

    public Order() {}

    public Order(Goods goods, CustomerGroup customerInfo, AddOrderRequest request) {
        batchId = request.getBatchId();
        if (StringUtils.isEmpty(batchId)) {
            batchId = UUID.randomUUID().toString();
        }
        customerGroupId = customerInfo.getId();
        goodsId = goods.getId();
        goodsName = goods.getName();
        goodsCombine = goods.getCombine();
        goodsTypeId = goods.getType();
        goodsTypeName = EnumUtils.getNameByValue(GoodsTypeEnum.class, goods.getType());
        expireDate = goods.getExpireDate();
        goodsDesc = goods.getDesc();
        goodsCnt = goods.getCnt();
        goodsItemTypeCnt = goods.getGoodsItemTypeCnt();
        goodsSumPrice = goods.getSumPrice();

        orderPrice = goods.getSumPrice() * request.getCnt();
        orderCnt = request.getCnt();
        setStatus(OrderStatusEnum.PENDING_PAY.getValue());

        copyOperationInfo(request);
    }

    @Override
    public String getStatusName() {
        return EnumUtils.getNameByValue(OrderStatusEnum.class, getStatus());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsCombine() {
        return goodsCombine;
    }

    public void setGoodsCombine(Integer goodsCombine) {
        this.goodsCombine = goodsCombine;
    }

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public Integer getGoodsCnt() {
        return goodsCnt;
    }

    public void setGoodsCnt(Integer goodsCnt) {
        this.goodsCnt = goodsCnt;
    }

    public Integer getGoodsItemTypeCnt() {
        return goodsItemTypeCnt;
    }

    public void setGoodsItemTypeCnt(Integer goodsItemTypeCnt) {
        this.goodsItemTypeCnt = goodsItemTypeCnt;
    }

    public Integer getGoodsSumPrice() {
        return goodsSumPrice;
    }

    public void setGoodsSumPrice(Integer goodsSumPrice) {
        this.goodsSumPrice = goodsSumPrice;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderCnt() {
        return orderCnt;
    }

    public void setOrderCnt(Integer orderCnt) {
        this.orderCnt = orderCnt;
    }
}
