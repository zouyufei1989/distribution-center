package com.money.custom.service.impl;

import com.money.custom.dao.GoodsTagDao;
import com.money.custom.entity.GoodsTag;
import com.money.custom.entity.enums.ChangeLogEntityEnum;
import com.money.custom.entity.enums.RedisKeyEnum;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryGoodsTagRequest;
import com.money.custom.service.GoodsTagService;
import com.money.framework.base.annotation.AddChangeLog;
import com.money.framework.base.annotation.RedisDel;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsTagServiceImpl extends BaseServiceImpl implements GoodsTagService {

    @Autowired
    GoodsTagDao dao;

    @Override
    public List<GoodsTag> selectSearchList(QueryGoodsTagRequest request) {
        return dao.selectSearchList(request);
    }

    @Override
    public int selectSearchListCount(QueryGoodsTagRequest request) {
        return dao.selectSearchListCount(request);
    }

    @Override
    public GoodsTag findById(String id) {
        return dao.findById(id);
    }

    @AddChangeLog(changeLogEntity = ChangeLogEntityEnum.GOODS_TAG)
    @RedisDel(redisKey = RedisKeyEnum.GROUPS)
    @Override
    public String add(GoodsTag item) {
        dao.add(item);
        return item.getId().toString();
    }

    @AddChangeLog(changeLogEntity = ChangeLogEntityEnum.GOODS_TAG)
    @RedisDel(redisKey = RedisKeyEnum.GROUPS)
    @Override
    public String edit(GoodsTag item) {
        dao.edit(item);
        return item.getId().toString();
    }

    @AddChangeLog(changeLogEntity = ChangeLogEntityEnum.GOODS_TAG)
    @Override
    public List<String> changeStatus(ChangeStatusRequest request) {
        dao.changeStatus(request);
        return request.getIds();
    }

}