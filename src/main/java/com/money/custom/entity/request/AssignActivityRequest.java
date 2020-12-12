package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AssignActivityRequest extends OperationalEntity {

    @NotNull(message = "请选择活动")
    private Integer activityId;
    @NotEmpty(message = "请选择要分配的股东")
    private List<AssignItem> items;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public List<AssignItem> getItems() {
        return items;
    }

    public void setItems(List<AssignItem> items) {
        this.items = items;
    }

    @Valid
    public static class AssignItem {
        @NotNull(message = "股东id不可为空")
        private Integer customerGroupId;
        @NotNull(message = "请输入分配数量")
        @Min(value = 1, message = "分配数量不可小于1")
        private Integer cnt;

        public Integer getCustomerGroupId() {
            return customerGroupId;
        }

        public void setCustomerGroupId(Integer customerGroupId) {
            this.customerGroupId = customerGroupId;
        }

        public Integer getCnt() {
            return cnt;
        }

        public void setCnt(Integer cnt) {
            this.cnt = cnt;
        }
    }
}
