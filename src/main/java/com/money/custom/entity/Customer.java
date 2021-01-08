package com.money.custom.entity;

import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.request.MoACustomerRequest;
import com.money.custom.utils.StringFormatUtils;
import com.money.framework.base.entity.BaseEntity;
import com.money.h5.entity.request.AddCustomer4WechatRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

import java.util.Date;
import java.util.Objects;

public class Customer extends BaseEntity {

    private Integer id;
    private String name;
    private String phone;
    private String openId;
    private String nickName;
    private String headCover;

    private String customerGroupIds;
    private CustomerGroup customerGroup = new CustomerGroup();
    private Wallet wallet = new Wallet();
    private BonusWallet bonusWallet = new BonusWallet();
    private BonusPlan bonusPlan = new BonusPlan();
    private Customer parent;


    public Customer() {}

    public Customer(MoACustomerRequest request) {
        copyOperationInfo(request);
        name = request.getName();
        phone = request.getPhone();
    }

    public Customer(AddCustomer4WechatRequest request) {
        openId = request.getOpenId();

        copyOperationInfo(request);
    }

    public String getCustomerGroupIds() {
        return customerGroupIds;
    }

    public void setCustomerGroupIds(String customerGroupIds) {
        this.customerGroupIds = customerGroupIds;
    }

    public BonusPlan getBonusPlan() {
        return bonusPlan;
    }

    public void setBonusPlan(BonusPlan bonusPlan) {
        this.bonusPlan = bonusPlan;
    }

    public Customer getParent() {
        return parent;
    }

    public void setParent(Customer parent) {
        this.parent = parent;
    }

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


    public String getSumMoney4Show() {
        if (Objects.nonNull(wallet) && Objects.nonNull(wallet.getSumMoney())) {
            return StringFormatUtils.moneyFen2Yuan(this.wallet.getSumMoney());
        }
        return StringUtils.EMPTY;
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
