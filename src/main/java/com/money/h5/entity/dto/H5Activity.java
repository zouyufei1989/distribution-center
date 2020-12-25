package com.money.h5.entity.dto;

import com.money.custom.entity.CustomerActivity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "活动信息")
public class H5Activity {

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "名称")
    private String activityName;
    @ApiModelProperty(value = "编号")
    private String serialNumber;
    @ApiModelProperty(value = "剩余次数")
    private Integer leftCnt;

    public H5Activity() {}

    public H5Activity(CustomerActivity item) {
        id = item.getActivityId();
        activityName = item.getActivityName();
        serialNumber = item.getActivitySerialNumber();
        leftCnt = item.getAvailableCnt();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getLeftCnt() {
        return leftCnt;
    }

    public void setLeftCnt(Integer leftCnt) {
        this.leftCnt = leftCnt;
    }
}
