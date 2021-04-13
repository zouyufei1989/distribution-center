package com.money.h5.entity.dto;

import com.money.custom.entity.Employee;
import com.money.custom.entity.Goods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "员工信息")
public class H5Employee {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "openId")
    private String openId;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "门店名")
    private String groupName;
    @ApiModelProperty(value = "手机号")
    private String phone;

    public H5Employee() {}

    public H5Employee(Employee employee) {
        id = employee.getId();
        name = employee.getName();
        openId = employee.getOpenId();
        groupName = employee.getGroupName();
        phone = employee.getPhone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
