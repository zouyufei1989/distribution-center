package com.money.custom.entity.dto;

import com.money.custom.entity.Customer;
import com.money.custom.entity.enums.CustomerTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeDto {

    private String id;
    private String title;
    private List<TreeNodeDto> children = new ArrayList<>();

    public TreeNodeDto() {}

    public TreeNodeDto(Customer customer) {
        id = customer.getCustomerGroup().getId().toString();
        title = customer.getName();
        if (customer.getCustomerGroup().getType().equals(CustomerTypeEnum.SHARE_HOLDER.getValue())) {
            title += "（" + customer.getCustomerGroup().getTypeName() + "）";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TreeNodeDto> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNodeDto> children) {
        this.children = children;
    }
}
