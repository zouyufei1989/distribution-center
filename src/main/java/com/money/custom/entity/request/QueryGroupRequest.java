package com.money.custom.entity.request;

import com.money.custom.entity.Group;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class QueryGroupRequest extends QueryGridRequestBase {

    private Group group = new Group();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    private Integer customerGroupId;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("group", group);
        params.put("customerGroupId", customerGroupId);
        return params;
    }

    public void setCustomerGroupId(String customerGroupId) {
        if (StringUtils.isEmpty(customerGroupId)) {
            return;
        }
        this.customerGroupId = Integer.parseInt(customerGroupId);
    }
}
