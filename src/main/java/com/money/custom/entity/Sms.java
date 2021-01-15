package com.money.custom.entity;

import com.alibaba.fastjson.JSONObject;
import com.gexin.fastjson.JSON;
import com.money.custom.entity.enums.MessageLevelEnum;
import com.money.custom.entity.enums.SmsStatusEnum;
import com.money.custom.entity.enums.SmsTypeEnum;
import com.money.custom.utils.StringFormatUtils;
import com.money.framework.base.annotation.IgnoreXss;
import com.money.framework.base.entity.BaseEntity;
import com.money.framework.util.EnumUtils;

public class Sms extends BaseEntity {

    private Integer id;
    private String phone;
    private Integer type;
    @IgnoreXss
    private String request;
    @IgnoreXss
    private String response;
    @IgnoreXss
    private String params;
    private Integer level;

    @Override
    public String toString() {
        return "Sms{" +
                "phone='" + phone + '\'' +
                ", type=" + getTypeName() +
                ", params='" + params + '\'' +
                '}';
    }

    public static Sms bonusNotify(String phone, Long bonus) {
        Sms sms = new Sms();
        sms.setPhone(phone);
        sms.setType(SmsTypeEnum.BONUS_NOTIFY.getValue());
        sms.setStatus(SmsStatusEnum.PENDING.getValue());
        sms.setLevel(MessageLevelEnum.MEDIUM.getValue());

        JSONObject obj = new JSONObject();
        obj.put("bonus", StringFormatUtils.moneyFen2Yuan(bonus));
        sms.setParams(JSON.toJSONString(obj));

        return sms;
    }

    public String getTypeName() {
        return EnumUtils.getNameByValue(SmsTypeEnum.class, type);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
