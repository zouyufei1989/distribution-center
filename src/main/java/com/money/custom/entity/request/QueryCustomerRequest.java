package com.money.custom.entity.request;

import com.money.custom.entity.Customer;
import com.money.custom.entity.GoodsTag;

import java.util.List;
import java.util.Map;

public class QueryCustomerRequest extends QueryGridRequestBase {

    private Customer customer = new Customer();
    private String exactPhone;
    private String nameOrPhone;

    private List<Integer> customerGroupIds;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setExactPhone(String exactPhone) {
        this.exactPhone = exactPhone;
    }

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("customer", customer);
        params.put("exactPhone", exactPhone);
        params.put("nameOrPhone", nameOrPhone);
        params.put("customerGroupIds", customerGroupIds);
        return params;
    }

    public void setNameOrPhone(String nameOrPhone) {
        this.nameOrPhone = nameOrPhone;
    }

    public void setCustomerGroupIds(List<Integer> customerGroupIds) {
        this.customerGroupIds = customerGroupIds;
    }

    public void setParentId(Integer parentId) {
        this.customer.getCustomerGroup().setParentId(parentId);
    }
}
