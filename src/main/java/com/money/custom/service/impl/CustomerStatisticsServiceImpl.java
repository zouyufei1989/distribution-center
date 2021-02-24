package com.money.custom.service.impl;

import com.money.custom.dao.CustomerStatisticsDao;
import com.money.custom.entity.dto.ShareHolderStatistics;
import com.money.custom.entity.request.ShareHolderStatisticsRequest;
import com.money.custom.service.CustomerStatisticsService;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerStatisticsServiceImpl extends BaseServiceImpl implements CustomerStatisticsService {

    @Autowired
    CustomerStatisticsDao dao;

    @Override
    public List<ShareHolderStatistics> shareHolderStatistics(ShareHolderStatisticsRequest request) {
        return dao.shareHolderStatistics(request);
    }
}
