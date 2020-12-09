package com.money.custom.entity;

import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.request.MoACustomerRequest;
import com.money.framework.base.entity.BaseEntity;
import com.money.h5.entity.request.AddCustomer4WechatRequest;

import java.util.Date;

public class Customer extends BaseEntity {

    private Integer id;
    private String name;
    private String phone;
    private String openId;
    private String nickName;
    private String headCover;

    public Customer() {}

    public Customer(MoACustomerRequest request) {
        copyOperationInfo(request);
        name = request.getName();
    }

    public Customer(AddCustomer4WechatRequest request) {
        openId = request.getOpenId();
        phone = request.getPhone();
        nickName = request.getNickName();
        headCover = request.getHeadCover();

        copyOperationInfo(request);
    }

    private CustomerGroup customerGroup = new CustomerGroup();
    private Wallet wallet = new Wallet();
    private BonusWallet bonusWallet = new BonusWallet();

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public BonusWallet getBonusWallet() {
        return bonusWallet;
    }

    public void setBonusWallet(BonusWallet bonusWallet) {
        this.bonusWallet = bonusWallet;
    }

    public CustomerGroup getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(CustomerGroup customerGroup) {
        this.customerGroup = customerGroup;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadCover() {
        return headCover;
    }

    public void setHeadCover(String headCover) {
        this.headCover = headCover;
    }

    public String getHistoryType() {
        return HistoryEntityEnum.CUSTOMER.getName();
    }
}
