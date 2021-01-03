package com.money.custom.service;

import com.money.custom.entity.ActivityClaimRecord;
import com.money.custom.entity.AssignActivity;
import com.money.custom.entity.request.QueryAssignActivityRequest;
import com.money.custom.entity.request.QueryClaimActivityRequest;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface ActivityClaimRecordService extends BaseService {

    List<ActivityClaimRecord> selectSearchList(QueryClaimActivityRequest request);

    int selectSearchListCount(QueryClaimActivityRequest request);

    String add(ActivityClaimRecord item);


}
