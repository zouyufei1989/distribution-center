package com.money.custom.entity;

import com.money.custom.entity.enums.CommonStatusEnum;
import com.money.custom.entity.enums.CustomerTypeEnum;
import com.money.custom.entity.request.MoACustomerRequest;
import com.money.framework.base.entity.BaseEntity;
import com.money.framework.util.DateUtils;
import com.money.framework.util.EnumUtils;
import com.money.h5.entity.request.AddCustomer4WechatRequest;

public class CustomerGroup extends BaseEntity {

    private Integer id;
    private Integer walletId;
    private Integer bonusWalletId;
    private Integer customerId;
    private Integer bonusPlanId;
    private String serialNumber;
    private Integer type;
    private String expireDate;

    private String bonusPlanName;

    public CustomerGroup() {}

    public CustomerGroup(MoACustomerRequest request, Integer customerId, String walletId, String bonusWalletId) {
        copyOperationInfo(request);
        setCustomerId(customerId);
        setWalletId(walletId);
        setBonusWalletId(bonusWalletId);
        setBankCardNumber(request.getBankCardNumber());
        setBankName(request.getBankName());
        setExpireDate(request.getExpireDate());
        setSerialNumber(request.getSerialNumber());
        setStatus(request.getStatus());
        setType(request.getType());
    }

    public CustomerGroup(AddCustomer4WechatRequest request, String serialNumber, Integer customerId, String walletId, String bonusWalletId) {
        copyOperationInfo(request);
        setCustomerId(customerId);
        setWalletId(walletId);
        setBonusWalletId(bonusWalletId);
        setSerialNumber(serialNumber);
        setStatus(CommonStatusEnum.ENABLE.getValue());
        setType(CustomerTypeEnum.NORMAL.getValue());
    }

    public String getBonusPlanName() {
        return bonusPlanName;
    }

    public void setBonusPlanName(String bonusPlanName) {
        this.bonusPlanName = bonusPlanName;
    }

    public String getTypeName() {
        return EnumUtils.getNameByValue(CustomerTypeEnum.class, this.type);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = Integer.parseInt(walletId);
    }

    public Integer getBonusWalletId() {
        return bonusWalletId;
    }

    public void setBonusWalletId(Integer bonusWalletId) {
        this.bonusWalletId = bonusWalletId;
    }

    public void setBonusWalletId(String bonusWalletId) {
        this.bonusWalletId = Integer.parseInt(bonusWalletId);
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getBonusPlanId() {
        return bonusPlanId;
    }

    public void setBonusPlanId(Integer bonusPlanId) {
        this.bonusPlanId = bonusPlanId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    private String bankCardNumber;
    private String bankName;


}
