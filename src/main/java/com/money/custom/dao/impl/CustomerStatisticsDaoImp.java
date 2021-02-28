package com.money.custom.dao.impl;

import com.money.custom.dao.CustomerStatisticsDao;
import com.money.custom.entity.dto.CustomerStatistics;
import com.money.custom.entity.dto.ShareHolderStatistics;
import com.money.custom.entity.request.ShareHolderStatisticsRequest;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@SQLContext(nameSpace = "CustomerStatistics")
public class CustomerStatisticsDaoImp extends BaseDaoImpl implements CustomerStatisticsDao {

    @Override
    public List<ShareHolderStatistics> shareHolderStatistics(ShareHolderStatisticsRequest request) {
        return selectList("shareHolderStatistics", request.buildParams());
    }

    @Override
    public List<CustomerStatistics> customerStatistics(ShareHolderStatisticsRequest request) {
        return selectList("customerStatistics", request.buildParams());
    }

    @Override
    public Map<String, Object> customerSummaryStatistics(ShareHolderStatisticsRequest request) {
        return selectOne("customerSummaryStatistics", request.buildParams());
    }
}
