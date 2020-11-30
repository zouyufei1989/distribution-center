package com.money.custom.entity.request;

import com.money.custom.entity.Group;

import java.util.Map;

public class QueryGroupRequest extends QueryGridRequestBase {

    private Group group = new Group();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("group", group);
        return params;
    }

    public void setName(String name){
        this.group.setName(name);
    }

    public void setOwnerName(String ownerName){
        this.group.setOwnerName(ownerName);
    }

    public void setStatus(Integer status){
        this.group.setStatus(status);
    }
}
