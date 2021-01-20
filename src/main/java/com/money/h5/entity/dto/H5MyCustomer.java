package com.money.h5.entity.dto;

import com.money.custom.entity.Customer;
import com.money.custom.entity.enums.CustomerTotalNewEnum;
import com.money.framework.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

@ApiModel(description = "客源信息")
public class H5MyCustomer {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "openId")
    private String openId;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "编号")
    private String serialNumber;
    @ApiModelProperty(value = "注册日期")
    private String createDate;
    @ApiModelProperty(value = "是否消费过")
    private Boolean consumed;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "头像")
    private String headCover;

    public H5MyCustomer() {}

    public H5MyCustomer(Customer item) {
        this.id = item.getCustomerGroup().getId();
        this.serialNumber = item.getCustomerGroup().getSerialNumber();
        this.name = item.getName();
        if (StringUtils.isEmpty(this.name)) {
            this.name = item.getNickName();
        }
        this.createDate = DateUtils.format(item.getCustomerGroup().getCreateDate(), "yyyy-MM-dd");
        this.consumed = item.getCustomerGroup().getTotalNew().equals(CustomerTotalNewEnum.OLD.getValue());
        this.phone = item.getPhone();
        this.headCover = item.getHeadCover();
        this.openId = item.getOpenId();
    }

    public String getOpenId() {
        return openId;
    }

    public String getHeadCover() {
        return headCover;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getCreateDate() {
        return createDate;
    }

    public Boolean getConsumed() {
        return consumed;
    }
}
