package com.money.custom.entity;

import com.money.custom.entity.enums.CustomerTypeEnum;
import com.money.custom.entity.enums.OrderStatusEnum;
import com.money.custom.entity.enums.ReservationStatusEnum;
import com.money.framework.base.entity.BaseEntity;
import com.money.framework.util.DateUtils;
import com.money.framework.util.EnumUtils;
import com.money.h5.entity.request.ReserveRequest;

import java.util.Date;

public class Reservation extends BaseEntity {


    private Integer id;
    private Integer orderId;
    private Integer status;
    private Integer customerGroupId;
    private String date;
    private String startTime;
    private String endTime;
    private Date cancelDate;
    private String remark;

    private String openId;
    private String goodsName;
    private Integer goodsId;
    private String customerName;
    private String phone;
    private String serialNumber;
    private Integer customerType;
    private Integer goodsTypeId;
    private Integer orderStatus;


    public Reservation() {}

    public Reservation(ReserveRequest reserveRequest) {
        this.orderId = reserveRequest.getOrderId();
        this.openId = reserveRequest.getOpenId();
        this.date = reserveRequest.getDate();
        this.startTime = reserveRequest.getStartTime();
        this.endTime = reserveRequest.getEndTime();
        copyOperationInfo(reserveRequest);
    }

    public boolean getOrderAvailable(){
        return OrderStatusEnum.orderAvailable(this.orderStatus);
    }

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getCustomerTypeName() {
        return EnumUtils.getNameByValue(CustomerTypeEnum.class, this.customerType);
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

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public long getTimestamp() {
        return DateUtils.parse(this.date + " " + this.startTime, "yyyy-MM-dd HH:mm").getTime();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getStatusName() {
        return EnumUtils.getNameByValue(ReservationStatusEnum.class, getStatus());
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(Integer customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}
