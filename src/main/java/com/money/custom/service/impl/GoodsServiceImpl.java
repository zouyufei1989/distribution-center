package com.money.custom.service.impl;

import com.money.custom.dao.GoodsDao;
import com.money.custom.entity.Goods;
import com.money.custom.entity.enums.ChangeLogEntityEnum;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryGoodsRequest;
import com.money.custom.service.GoodsService;
import com.money.framework.base.annotation.AddChangeLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl extends BaseServiceImpl implements GoodsService {

    @Autowired
    GoodsDao dao;

    @Override
    public List<Goods> selectSearchList(QueryGoodsRequest request) {
        return dao.selectSearchList(request);
    }

    @Override
    public int selectSearchListCount(QueryGoodsRequest request) {
        return dao.selectSearchListCount(request);
    }

    @Override
    public Goods findById(String id) {
        return dao.findById(id);
    }

    @AddChangeLog(changeLogEntity = ChangeLogEntityEnum.GOODS)
    @Override
    public String add(Goods item) {
        dao.add(item);
        return item.getId().toString();
    }

    @AddChangeLog(changeLogEntity = ChangeLogEntityEnum.GOODS)
    @Override
    public String edit(Goods item) {
        dao.edit(item);
        return item.getId().toString();
    }

    @AddChangeLog(changeLogEntity = ChangeLogEntityEnum.GOODS)
    @Override
    public List<String> changeStatus(ChangeStatusRequest request) {
        dao.changeStatus(request);
        return request.getIds();
    }

}
