package com.money.framework.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GridResponseBase extends ResponseBase {

    @ApiModelProperty(value = "当前页面条数")
    private Integer records;
    @ApiModelProperty(value = "总页数")
    private Integer total;
    @ApiModelProperty(value = "当前页面数据")
    private Object rows;

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
