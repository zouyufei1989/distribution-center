package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MoACustomerRequest extends OperationalEntity {

    private Integer id;

    @NotBlank(message = "请输入客户姓名")
    @Length(max = 20, message = "客户姓名不可超过20个字符")
    private String name;
    private String serialNumber;
    @NotBlank(message = "请输入手机号")
    @Pattern(regexp="^1\\d{10}$",message="手机号长度错误")
    private String phone;
    @NotNull(message = "请选择顾客类型")
    private Integer type;
    private String expireDate;
    @Length(max = 20, message = "银行卡号不可超过20个字符")
    private String bankCardNumber;
    @Length(max = 20, message = "开户行不可超过50个字符")
    private String bankName;
    @NotNull(message = "请选择状态")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
