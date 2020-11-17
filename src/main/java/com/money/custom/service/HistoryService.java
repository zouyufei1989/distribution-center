package com.money.custom.service;


import com.money.custom.entity.History;
import com.money.custom.entity.request.QueryHistoryRequest;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface HistoryService extends BaseService {

    List<Object> selectSearchList(QueryHistoryRequest request);

    int selectSearchListCount(QueryHistoryRequest request);

    void addBatch(List<History> list);

}
