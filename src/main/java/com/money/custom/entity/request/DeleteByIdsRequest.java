package com.money.custom.entity.request;

import com.money.custom.entity.enums.DeletableTableNameEnum;
import com.money.framework.base.entity.OperationalEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteByIdsRequest extends OperationalEntity {

    private DeletableTableNameEnum tableNameEnum;
    private List<String> ids;

    public Map<String, Object> buildParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("tableName", tableNameEnum.getName());
        params.put("ids", ids);
        return params;
    }

    public DeletableTableNameEnum getTableNameEnum() {
        return tableNameEnum;
    }

    public void setTableNameEnum(DeletableTableNameEnum tableNameEnum) {
        this.tableNameEnum = tableNameEnum;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
