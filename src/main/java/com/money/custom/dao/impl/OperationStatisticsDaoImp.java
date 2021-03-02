package com.money.custom.dao.impl;

import com.money.custom.dao.OperationStatisticsDao;
import com.money.custom.entity.dto.GroupPerformance;
import com.money.custom.entity.dto.PerformanceSummary;
import com.money.custom.entity.request.StatisticsBaseRequest;
import com.money.custom.entity.request.StatisticsGoodsSellingRankRequest;
import com.money.custom.entity.request.StatisticsGroupPerformanceRequest;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@SQLContext(nameSpace = "OperationStatistics")
public class OperationStatisticsDaoImp extends BaseDaoImpl implements OperationStatisticsDao {

    @Override
    public PerformanceSummary summaryInfo(StatisticsBaseRequest request) {
        return selectOne("summaryInfo", request.buildParams());
    }

    @Override
    public List<GroupPerformance> groupPerformance(StatisticsGroupPerformanceRequest request) {
        return selectList("groupPerformance", request.buildParams());
    }

    @Override
    public List<Map<String, Object>> goodsSellingRank(StatisticsGoodsSellingRankRequest request) {
        return selectList("goodsSellingRank", request.buildParams());
    }

    @Override
    public Map<String, Object> groupPerformancePie(StatisticsBaseRequest request) {
        return selectOne("groupPerformancePie", request.buildParams());
    }

    @Override
    public Map<String, Object> bonusStatistics(StatisticsBaseRequest request) {
        return selectOne("bonusStatistics", request.buildParams());
    }
}
