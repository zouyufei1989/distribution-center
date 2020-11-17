package com.money.custom.service;


import com.money.custom.entity.StsVisitLog;
import com.money.custom.entity.request.QueryVisitLogRequest;

import java.util.List;

public interface StsVisitLogService {

    List<StsVisitLog> visitLogStsByResource(QueryVisitLogRequest request);

    List<StsVisitLog> visitLogStsByUser(QueryVisitLogRequest request);

}
