package com.money.h5.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.Map;

@ApiModel
public class H5GridRequestBase extends H5RequestBase {

    @ApiModelProperty(value = "每页条数", notes = "默认0,查询全部", example = "10")
    @Min(value = 0)
    @Max(value = 50)
    private Integer rows = 0;
    @ApiModelProperty(value = "页码", notes = "默认1，从1开始", example = "1")
    @Min(value = 1)
    private Integer page = 1;

    public Map<String, Object> buildParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("rows", rows);
        params.put("start", (page - 1) * rows);

        return params;
    }

    public Integer calTotalPage(Integer recordCount) {
        int totalPage = 0;
        if (rows != 0) {
            totalPage = (recordCount / rows) + ((recordCount % rows) == 0 ? 0 : 1);
        }
        return totalPage;
    }


    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
