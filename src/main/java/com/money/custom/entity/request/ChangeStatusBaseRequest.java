package com.money.custom.entity.request;

import com.money.custom.entity.enums.IEnumKeyValue;
import com.money.framework.base.entity.OperationalEntity;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ChangeStatusBaseRequest<T extends IEnumKeyValue> extends OperationalEntity {

    private List<String> ids;
    private Integer status;

    public ChangeStatusBaseRequest() {}

    public Class<T> getEnumClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public ChangeStatusBaseRequest(String id, Integer status) {
        this.ids = Arrays.asList(id);
        this.status = status;
    }

    public ChangeStatusBaseRequest(List<String> ids, Integer status) {
        this.ids = ids;
        this.status = status;
    }

    public List<String> getIds() {
        return ids;
    }

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("ids", ids);
        params.put("status", status);
        return params;
    }

    public Integer getStatus() {
        return status;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

}
