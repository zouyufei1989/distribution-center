package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MoAEmployeeRequest extends OperationalEntity {

    private Integer id;

    @NotBlank(message = "请输入员工姓名")
    @Length(max = 20, message = "员工姓名不可超过20个字符")
    private String name;
    private String serialNumber;
    @NotBlank(message = "请输入手机号")
    @Pattern(regexp = "^1\\d{10}$", message = "手机号长度错误")
    private String phone;
    @NotNull(message = "请选择状态")
    private Integer status;
    @NotBlank(message = "请输入入职日期")
    private String joinDate;
    @NotNull(message = "请选择性别")
    private Integer gender;
    @NotBlank(message = "请输入身份证号")
    @Length(max = 20, message = "身份证号不可超过20个字符")
    private String identifyNo;

    private String openId;
    private String sessionKey;
    private String nickName;
    private String headCover;

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIdentifyNo() {
        return identifyNo;
    }

    public void setIdentifyNo(String identifyNo) {
        this.identifyNo = identifyNo;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
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
}
