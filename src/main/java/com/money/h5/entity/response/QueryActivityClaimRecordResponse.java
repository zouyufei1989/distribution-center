package com.money.h5.entity.response;

import com.money.custom.entity.ActivityClaimRecord;
import com.money.custom.entity.CustomerActivity;
import com.money.framework.base.entity.GridResponseBase;
import com.money.h5.entity.dto.H5Activity;
import com.money.h5.entity.dto.H5ActivityClaimRecord;
import io.swagger.annotations.ApiModel;

import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryActivityClaimRecordResponse extends GridResponseBase {

    public QueryActivityClaimRecordResponse() {}

    public QueryActivityClaimRecordResponse(Integer total, Integer records, List<ActivityClaimRecord> items) {
        super(total, records, items.stream().map(H5ActivityClaimRecord::new).collect(Collectors.toList()));
    }
}
