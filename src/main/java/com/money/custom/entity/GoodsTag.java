package com.money.custom.entity;

import com.money.framework.base.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class GoodsTag extends BaseEntity {

    private Integer id;
    @Length(max = 20,message = "商品标签名称不可超过20个字符")
    @NotBlank(message = "请输入商品标签名称")
    private String name;
    @Length(max = 200,message = "备注不可超过200个字符")
    private String remark;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
