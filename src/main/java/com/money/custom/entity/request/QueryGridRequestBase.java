package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;
import com.money.h5.entity.H5GridRequestBase;

import java.util.Map;

public class QueryGridRequestBase extends OperationalEntity {

    private Integer page = 1;
    private Integer rows = 0;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("rows", rows);
        params.put("start", (page - 1) * rows);

        return params;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer calTotalPage(Integer recordCount) {
        int totalPage = 0;
        if (rows != 0) {
            totalPage = (recordCount / rows) + ((recordCount % rows) == 0 ? 0 : 1);
        }
        return totalPage;
    }

    public void copyPagerFromH5Request(H5GridRequestBase requestBase) {
        this.page = requestBase.getPage();
        this.rows = requestBase.getRows();
    }
}
