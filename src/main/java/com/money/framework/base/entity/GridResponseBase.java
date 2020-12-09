package com.money.framework.base.entity;

public class GridResponseBase extends ResponseBase {

    private Integer records;
    private Integer total;
    private Object rows;

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
