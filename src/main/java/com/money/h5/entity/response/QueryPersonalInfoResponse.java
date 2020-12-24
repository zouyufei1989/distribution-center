package com.money.h5.entity.response;

import com.money.custom.entity.Customer;
import com.money.custom.entity.Group;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.dto.H5Customer;
import com.money.h5.entity.dto.H5Group;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryPersonalInfoResponse extends ResponseBase {

    @Valid
    @ApiModelProperty(value = "个人信息")
    public H5Customer personalInfo;

    public QueryPersonalInfoResponse() {}

    public QueryPersonalInfoResponse(Customer customer) {
        personalInfo = new H5Customer(customer);
    }

    public H5Customer getPersonalInfo() {
        return personalInfo;
    }
}
