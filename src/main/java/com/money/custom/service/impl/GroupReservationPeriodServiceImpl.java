package com.money.custom.service.impl;

import com.money.custom.dao.GroupReservationPeriodDao;
import com.money.custom.entity.Goods;
import com.money.custom.entity.Group;
import com.money.custom.entity.GroupReservationPeriod;
import com.money.custom.entity.enums.CommonStatusEnum;
import com.money.custom.entity.enums.GroupReserveFlagEnum;
import com.money.custom.entity.request.*;
import com.money.custom.service.GoodsService;
import com.money.custom.service.GroupReservationPeriodService;
import com.money.custom.service.GroupService;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupReservationPeriodServiceImpl extends BaseServiceImpl implements GroupReservationPeriodService {

    @Autowired
    GroupReservationPeriodDao dao;
    @Autowired
    GroupService groupService;
    @Autowired
    GoodsService goodsService;

    @Override
    public List<GroupReservationPeriod> selectSearchList(QueryGroupReservationPeriodRequest request) {
        Assert.notNull(request.getGoodsId(), "请指项目id");
        Goods goods = goodsService.findById(request.getGoodsId().toString());
        Assert.notNull(goods, "项目不存在");

        Group group = groupService.findById(goods.getGroupId().toString());
        Assert.notNull(group, "门店不存在");
        Assert.isTrue(group.getReserveFlag().equals(GroupReserveFlagEnum.YES.getValue()), "门店不支持预约");

        return dao.selectSearchList(request);
    }

    @Override
    public int selectSearchListCount(QueryGroupReservationPeriodRequest request) {
        return dao.selectSearchListCount(request);
    }

    @Transactional
    @Override
    public List<String> add(SaveGroupReservationPeriodsRequest request) {
        List<GroupReservationPeriod> periods = request.getPeriods();
        Assert.notEmpty(periods, "要添加的预约时间段为空");

        Goods goods = goodsService.findById(periods.get(0).getGoodsId().toString());
        Assert.notNull(goods, "项目不存在");

        Group byId = groupService.findById(goods.getGroupId().toString());
        Assert.notNull(byId, "门店不存在");
        Assert.isTrue(byId.getReserveFlag().equals(GroupReserveFlagEnum.YES.getValue()), "门店不支持预约");

        periods.sort(Comparator.comparing(GroupReservationPeriod::getStartTime));
        for (int i = 0; i < periods.size() - 1; i++) {
            Assert.isTrue(periods.get(i).getEndTime().compareTo(periods.get(i + 1).getStartTime()) <= 0, "预约时间段存在重复");
        }

        expireOldPeriods(goods);
        return addNewPeriods(request, periods, goods);
    }

    private List<String> addNewPeriods(SaveGroupReservationPeriodsRequest request, List<GroupReservationPeriod> periods, Goods goods) {
        periods.forEach(p -> {
            p.setGroupId(goods.getGroupId());
            p.setStatus(CommonStatusEnum.ENABLE.getValue());
            p.copyOperationInfo(request);
        });
        dao.addBatch(periods);
        getLogger().info("添加新的预约时间段设置 {}", periods.size());
        return periods.stream().map(i -> i.getId().toString()).collect(Collectors.toList());
    }

    private void expireOldPeriods(Goods goods) {
        QueryGroupReservationPeriodRequest queryGroupReservationPeriodRequest = new QueryGroupReservationPeriodRequest();
        queryGroupReservationPeriodRequest.setGoodsId(goods.getId());
        List<GroupReservationPeriod> groupReservationPeriods = selectSearchList(queryGroupReservationPeriodRequest);
        if (CollectionUtils.isNotEmpty(groupReservationPeriods)) {
            List<String> ids = groupReservationPeriods.stream().map(i -> i.getId().toString()).collect(Collectors.toList());
            ChangeStatusRequest changeStatusRequest = new ChangeStatusRequest(ids, CommonStatusEnum.DISABLE.getValue());
            dao.changeStatus(changeStatusRequest);
            getLogger().info("禁用门店现有预约时间段设置");
        }
    }

}
