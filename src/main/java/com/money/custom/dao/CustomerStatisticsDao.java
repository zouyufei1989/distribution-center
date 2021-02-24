package com.money.custom.dao;

import com.money.custom.entity.dto.ShareHolderStatistics;
import com.money.custom.entity.request.ShareHolderStatisticsRequest;
import com.money.framework.base.dao.BaseDao;

import java.util.List;

public interface CustomerStatisticsDao extends BaseDao {

    List<ShareHolderStatistics> shareHolderStatistics(ShareHolderStatisticsRequest request);

}
