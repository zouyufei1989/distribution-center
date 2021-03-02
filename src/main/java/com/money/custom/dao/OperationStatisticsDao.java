package com.money.custom.dao;

import com.money.custom.entity.dto.GroupPerformance;
import com.money.custom.entity.dto.PerformanceSummary;
import com.money.custom.entity.request.StatisticsBaseRequest;
import com.money.custom.entity.request.StatisticsGoodsSellingRankRequest;
import com.money.custom.entity.request.StatisticsGroupPerformanceRequest;
import com.money.framework.base.dao.BaseDao;

import java.util.List;
import java.util.Map;

public interface OperationStatisticsDao extends BaseDao {

    PerformanceSummary summaryInfo(StatisticsBaseRequest request);

    List<GroupPerformance> groupPerformance(StatisticsGroupPerformanceRequest request);

    List<Map<String,Object>> goodsSellingRank(StatisticsGoodsSellingRankRequest request);

    Map<String,Object> groupPerformancePie(StatisticsBaseRequest request);

    Map<String,Object> bonusStatistics(StatisticsBaseRequest request);


}
