package com.money.custom.service.impl;

import com.money.custom.dao.OperationStatisticsDao;
import com.money.custom.entity.dto.GroupPerformance;
import com.money.custom.entity.dto.PerformanceSummary;
import com.money.custom.entity.request.StatisticsBaseRequest;
import com.money.custom.entity.request.StatisticsGoodsSellingRankRequest;
import com.money.custom.entity.request.StatisticsGroupPerformanceRequest;
import com.money.custom.entity.response.GroupPerformanceStatisticsResponse;
import com.money.custom.service.OperationStatisticsService;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OperationStatisticsServiceImpl extends BaseServiceImpl implements OperationStatisticsService {

    @Autowired
    OperationStatisticsDao dao;

    @Override
    public PerformanceSummary summaryInfo(StatisticsBaseRequest request) {
        return dao.summaryInfo(request);
    }

    @Override
    public GroupPerformanceStatisticsResponse groupPerformance(StatisticsGroupPerformanceRequest request) {
        List<GroupPerformance> groupPerformances = dao.groupPerformance(request);
        return new GroupPerformanceStatisticsResponse(groupPerformances, request);
    }

    @Override
    public List<Map<String, Object>> goodsSellingRank(StatisticsGoodsSellingRankRequest request) {
        return dao.goodsSellingRank(request);
    }

    @Override
    public Map<String, Object> groupPerformancePie(StatisticsBaseRequest request) {
        return dao.groupPerformancePie(request);
    }

    @Override
    public Map<String, Object> bonusStatistics(StatisticsBaseRequest request) {
        return dao.bonusStatistics(request);
    }
}
