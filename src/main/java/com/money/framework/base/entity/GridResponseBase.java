package com.money.framework.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GridResponseBase extends ResponseBase {

    @ApiModelProperty(value = "总条数")
    protected Integer records;
    @ApiModelProperty(value = "总页数")
    protected Integer total;
    @ApiModelProperty(value = "当前页面数据")
    protected Object rows;

    public GridResponseBase() {}

    public GridResponseBase(Integer total, Integer records, Object rows) {
        this.total = total;
        this.records = records;
        this.rows = rows;
    }


    public GridResponseBase(Object rows) {
        this.rows = rows;
    }

    public Integer getRecords() {
        return records;
    }

    public Integer getTotal() {
        return total;
    }

    public Object getRows() {
        return rows;
    }
}
