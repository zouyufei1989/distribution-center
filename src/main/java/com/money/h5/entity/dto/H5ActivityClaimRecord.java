package com.money.h5.entity.dto;

import com.money.custom.entity.ActivityClaimRecord;
import com.money.custom.entity.CustomerActivity;
import com.money.framework.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "赠送记录")
public class H5ActivityClaimRecord {

    @ApiModelProperty(value = "姓名")
    private String customerName;
    @ApiModelProperty(value = "编号")
    private String serialNumber;
    @ApiModelProperty(value = "领取时间")
    private String createDate;

    public H5ActivityClaimRecord() {}

    public H5ActivityClaimRecord(ActivityClaimRecord item) {
        customerName = item.getCustomerName();
        serialNumber = item.getCustomerSerialNumber();
        createDate = DateUtils.format(item.getCreateDate(),"yyyy-MM-dd HH:mm:ss");
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getCreateDate() {
        return createDate;
    }
}
