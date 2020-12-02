package com.money.custom.service.impl;

import com.money.custom.dao.BannerDao;
import com.money.custom.entity.Banner;
import com.money.custom.entity.enums.ChangeLogEntityEnum;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryGridRequestBase;
import com.money.custom.service.BannerService;
import com.money.framework.base.annotation.AddChangeLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl extends BaseServiceImpl implements BannerService {

    @Autowired
    BannerDao dao;

    @Override
    public List<Banner> selectSearchList(QueryGridRequestBase request) {
        return dao.selectSearchList(request);
    }

    @Override
    public int selectSearchListCount(QueryGridRequestBase request) {
        return dao.selectSearchListCount(request);
    }

    @Override
    public Banner findById(String id) {
        return dao.findById(id);
    }

    @AddChangeLog(changeLogEntity = ChangeLogEntityEnum.BANNER)
    @Override
    public String add(Banner item) {
        dao.add(item);
        return item.getId().toString();
    }

    @AddChangeLog(changeLogEntity = ChangeLogEntityEnum.BANNER)
    @Override
    public String edit(Banner item) {
        dao.edit(item);
        return item.getId().toString();
    }

    @AddChangeLog(changeLogEntity = ChangeLogEntityEnum.BANNER)
    @Override
    public List<String> changeStatus(ChangeStatusRequest request) {
        dao.changeStatus(request);
        return request.getIds();
    }

}
