package com.money.custom.entity;

import com.money.custom.entity.enums.EmployeeStatusEnum;
import com.money.custom.entity.request.MoAEmployeeRequest;
import com.money.framework.base.entity.BaseEntity;
import com.money.framework.util.EnumUtils;

public class Employee extends BaseEntity {

    private Integer id;
    private String name;
    private String phone;
    private String openId;
    private String sessionKey;
    private String serialNumber;
    private String nickName;
    private String headCover;

    public static Employee build4Add(MoAEmployeeRequest request){
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setPhone(request.getPhone());
        employee.setGroupId(request.getGroupId());
        employee.setStatus(request.getStatus());
        employee.copyOperationInfo(request);
        return employee;
    }

    public static Employee build4Edit(MoAEmployeeRequest request){
        Employee employee = new Employee();
        employee.setId(request.getId());
        employee.setName(request.getName());
        employee.setPhone(request.getPhone());
        employee.setStatus(request.getStatus());
        employee.copyOperationInfo(request);
        return employee;
    }

    @Override
    public String getStatusName() {
        return EnumUtils.getNameByValue(EmployeeStatusEnum.class, getStatus());
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

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
