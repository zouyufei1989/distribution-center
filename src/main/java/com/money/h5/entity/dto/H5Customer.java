package com.money.h5.entity.dto;

import com.money.custom.entity.Customer;
import com.money.custom.entity.Goods;
import com.money.framework.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "个人信息")
public class H5Customer {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "编号")
    private String serialNumber;
    @ApiModelProperty(value = "有效期")
    private String expireDate;
    @ApiModelProperty(value = "所属门店名")
    private String groupName;
    @ApiModelProperty(value = "账户余额")
    private Integer availableMoney;

    public H5Customer() {}

    public H5Customer(Customer item) {
        this.id = item.getCustomerGroup().getId();
        this.serialNumber = item.getCustomerGroup().getSerialNumber();
        this.name = item.getName();
        this.expireDate = DateUtils.getMonthEnd(item.getCustomerGroup().getExpireDate());
        this.groupName = item.getCustomerGroup().getGroupName();
        this.availableMoney = item.getWallet().getAvailableMoney();
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(Integer availableMoney) {
        this.availableMoney = availableMoney;
    }
}
