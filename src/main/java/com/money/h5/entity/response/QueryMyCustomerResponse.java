package com.money.h5.entity.response;

import com.money.custom.entity.Customer;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.dto.H5MyCustomer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryMyCustomerResponse extends ResponseBase {

    @Valid
    @ApiModelProperty(value = "我的客源集合")
    public List<H5MyCustomer> items;

    public QueryMyCustomerResponse() {}

    public QueryMyCustomerResponse(List<Customer> customers) {
        if (CollectionUtils.isNotEmpty(customers)) {
            items = customers.stream().map(H5MyCustomer::new).collect(Collectors.toList());
        }
    }

}
