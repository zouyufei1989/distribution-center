package com.money.custom.service;

import com.money.custom.entity.dto.CustomerStatistics;
import com.money.custom.entity.dto.ShareHolderStatistics;
import com.money.custom.entity.request.ShareHolderStatisticsRequest;
import com.money.framework.base.service.BaseService;

import java.util.List;
import java.util.Map;

public interface CustomerStatisticsService extends BaseService {

    List<ShareHolderStatistics> shareHolderStatistics(ShareHolderStatisticsRequest request);

    List<CustomerStatistics> customerStatistics(ShareHolderStatisticsRequest request);

    Map<String, Object> customerSummaryStatistics(ShareHolderStatisticsRequest request);

}
