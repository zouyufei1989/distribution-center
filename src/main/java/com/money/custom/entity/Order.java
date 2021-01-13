package com.money.custom.entity;

import com.money.custom.entity.enums.CustomerTypeEnum;
import com.money.custom.entity.enums.GoodsTypeEnum;
import com.money.custom.entity.enums.OrderStatusEnum;
import com.money.custom.entity.enums.PayTypeEnum;
import com.money.custom.entity.request.AddOrderRequest;
import com.money.framework.base.entity.BaseEntity;
import com.money.framework.util.DateUtils;
import com.money.framework.util.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
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
    private Long goodsSumPrice;
    private String goodsCoverImg;

    private String goodsSerialNumber;
    private Long orderPrice;
    private Integer orderCnt;
    private String customerName;
    private String serialNumber;
    private String phone;
    private Integer customerType;
    private Integer payType;
    private Long sumMoney;
    private Long actuallyMoney;
    private String groupName;
    private String parentName;

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

    public Long getActuallyMoney() {
        return actuallyMoney;
    }

    public void setActuallyMoney(Long actuallyMoney) {
        this.actuallyMoney = actuallyMoney;
    }

    public String getCustomerTypeName() {
        return EnumUtils.getNameByValue(CustomerTypeEnum.class, customerType);
    }

    public String getPayTypeName() {
        List<String> payTypes = new ArrayList<>();
        if (PayTypeEnum.MONEY.pay(this.payType)) {
            payTypes.add(PayTypeEnum.MONEY.getName());
        }
        if (PayTypeEnum.BONUS.pay(this.payType)) {
            payTypes.add(PayTypeEnum.BONUS.getName());
        }
        if (PayTypeEnum.OFFLINE.pay(this.payType)) {
            payTypes.add(PayTypeEnum.OFFLINE.getName());
        }
        return StringUtils.join(payTypes, ",");
    }

    public String getGoodsCoverImg() {
        return goodsCoverImg;
    }

    public void setGoodsCoverImg(String goodsCoverImg) {
        this.goodsCoverImg = goodsCoverImg;
    }

    public String getGoodsSerialNumber() {
        return goodsSerialNumber;
    }

    public void setGoodsSerialNumber(String goodsSerialNumber) {
        this.goodsSerialNumber = goodsSerialNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Long sumMoney) {
        this.sumMoney = sumMoney;
    }

    @Override
    public String getGroupName() {
        return groupName;
    }

    @Override
    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
        return EnumUtils.getNameByValue(GoodsTypeEnum.class, this.goodsTypeId);
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

    public Long getGoodsSumPrice() {
        return goodsSumPrice;
    }

    public void setGoodsSumPrice(Long goodsSumPrice) {
        this.goodsSumPrice = goodsSumPrice;
    }

    public Long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Long orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderCnt() {
        return orderCnt;
    }

    public void setOrderCnt(Integer orderCnt) {
        this.orderCnt = orderCnt;
    }

    public boolean getExpired() {
        if (StringUtils.isEmpty(this.expireDate)) {
            return false;
        }
        return this.expireDate.compareTo(DateUtils.nowDate()) < 0;
    }
}
