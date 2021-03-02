package com.money.custom.service;

import com.money.custom.entity.dto.PerformanceSummary;
import com.money.custom.entity.request.StatisticsBaseRequest;
import com.money.custom.entity.request.StatisticsGoodsSellingRankRequest;
import com.money.custom.entity.request.StatisticsGroupPerformanceRequest;
import com.money.custom.entity.response.GroupPerformanceStatisticsResponse;
import com.money.framework.base.service.BaseService;

import java.util.List;
import java.util.Map;

public interface OperationStatisticsService extends BaseService {

   PerformanceSummary summaryInfo(StatisticsBaseRequest request);

   GroupPerformanceStatisticsResponse groupPerformance(StatisticsGroupPerformanceRequest request);

   List<Map<String, Object>> goodsSellingRank(StatisticsGoodsSellingRankRequest request);

   Map<String, Object> groupPerformancePie(StatisticsBaseRequest request);

   Map<String, Object> bonusStatistics(StatisticsBaseRequest request);
}
