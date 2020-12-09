package com.money.custom.entity.request;

import com.money.custom.entity.CustomerGroup;
import com.money.framework.base.entity.OperationalEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AssignBonusPlanRequest extends OperationalEntity {

    @NotNull(message = "id不可为空")
    private Integer id;
    @NotNull(message = "请选择积分方案")
    private Integer bonusPlanId;

    public CustomerGroup getEditObj() {
        CustomerGroup customerGroup = new CustomerGroup();
        customerGroup.setId(id);
        customerGroup.setBonusPlanId(bonusPlanId);
        customerGroup.copyOperationInfo(this);
        return customerGroup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBonusPlanId() {
        return bonusPlanId;
    }

    public void setBonusPlanId(Integer bonusPlanId) {
        this.bonusPlanId = bonusPlanId;
    }
}
