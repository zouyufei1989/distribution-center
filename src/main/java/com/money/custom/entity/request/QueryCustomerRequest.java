package com.money.custom.entity.request;

import com.money.custom.entity.Customer;
import com.money.custom.entity.GoodsTag;

import java.util.Map;

public class QueryCustomerRequest extends QueryGridRequestBase {

    private Customer customer = new Customer();
    private String exactPhone;

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
        return params;
    }
}
