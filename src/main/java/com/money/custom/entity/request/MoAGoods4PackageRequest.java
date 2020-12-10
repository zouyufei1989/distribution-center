package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MoAGoods4PackageRequest extends OperationalEntity {

    private Integer id;
    @Length(max = 20, message = "商品名称不可超过20个字符")
    @NotBlank(message = "请输入商品名称")
    private String name;
    private String desc;
    @NotNull(message = "请选择套餐状态")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}
