package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class MoAGoods4ActivityRequest extends OperationalEntity {

    private Integer id;
    @Length(max = 20, message = "活动名称不可超过20个字符")
    @NotBlank(message = "请输入活动名称")
    private String name;
    private String desc;
    @NotNull(message = "请选择活动状态")
    private Integer status;
    @NotBlank(message = "编号不可为空")
    private String serialNumber;
    @NotBlank(message = "请输入有效期")
    private String expireDate;
    @NotBlank(message = "请上传封面图片")
    private String coverImg;
    @NotNull(message = "请选择活动范围")
    private Integer scope;
    @NotNull(message = "请输入每人最多领取次数")
    private Integer maxCntPerCus;
    @NotNull(message = "请输入活动有效期")
    private Integer expireMonthCnt;

    private List<ActivityItem> items;

    public List<ActivityItem> getItems() {
        return items;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public Integer getMaxCntPerCus() {
        return maxCntPerCus;
    }

    public void setMaxCntPerCus(Integer maxCntPerCus) {
        this.maxCntPerCus = maxCntPerCus;
    }

    public Integer getExpireMonthCnt() {
        return expireMonthCnt;
    }

    public void setExpireMonthCnt(Integer expireMonthCnt) {
        this.expireMonthCnt = expireMonthCnt;
    }

    public void setItems(List<ActivityItem> items) {
        this.items = items;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public static class ActivityItem {
        private Integer goodsItemId;
        private Integer cnt;

        public Integer getGoodsItemId() {
            return goodsItemId;
        }

        public void setGoodsItemId(Integer goodsItemId) {
            this.goodsItemId = goodsItemId;
        }

        public Integer getCnt() {
            return cnt;
        }

        public void setCnt(Integer cnt) {
            this.cnt = cnt;
        }
    }
}
