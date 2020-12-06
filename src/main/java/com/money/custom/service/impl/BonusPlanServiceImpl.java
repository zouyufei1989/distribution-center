package com.money.custom.service.impl;

import com.money.custom.dao.BannerDao;
import com.money.custom.dao.BonusPlanDao;
import com.money.custom.entity.Banner;
import com.money.custom.entity.BonusPlan;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryBonusPlanRequest;
import com.money.custom.entity.request.QueryGridRequestBase;
import com.money.custom.service.BannerService;
import com.money.custom.service.BonusPlanService;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BonusPlanServiceImpl extends BaseServiceImpl implements BonusPlanService {

    @Autowired
    BonusPlanDao dao;

    @Override
    public List<BonusPlan> selectSearchList(QueryBonusPlanRequest request) {
        return dao.selectSearchList(request);
    }

    @Override
    public int selectSearchListCount(QueryBonusPlanRequest request) {
        return dao.selectSearchListCount(request);
    }

    @Override
    public BonusPlan findById(String id) {
        return dao.findById(id);
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.BONUS_PLAN)
    @Override
    public String add(BonusPlan item) {
        dao.add(item);
        return item.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.BONUS_PLAN)
    @Override
    public String edit(BonusPlan item) {
        dao.edit(item);
        return item.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.BONUS_PLAN)
    @Override
    public List<String> changeStatus(ChangeStatusRequest request) {
        dao.changeStatus(request);
        return request.getIds();
    }

}
