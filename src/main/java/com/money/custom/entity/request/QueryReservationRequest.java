package com.money.custom.entity.request;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class QueryReservationRequest extends QueryGridRequestBase {

    private String openId;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private Integer status;
    private Integer goodsId;
    private String customerName;
    private String phone;
    private String date;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("openId", openId);
        params.put("startDate", date);
        params.put("endDate", date);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("status", status);
        params.put("goodsId", goodsId);
        params.put("customerName", customerName);
        params.put("phone", phone);

        if(StringUtils.isNotEmpty(startDate)){
            params.put("startDate", startDate);
        }
        if(StringUtils.isNotEmpty(endDate)){
            params.put("endDate", endDate);
        }
        return params;
    }


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
