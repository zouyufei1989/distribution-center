package com.money.custom.service.impl;

import com.money.custom.dao.AssignActivityDao;
import com.money.custom.dao.BannerDao;
import com.money.custom.entity.*;
import com.money.custom.entity.enums.CommonStatusEnum;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.request.*;
import com.money.custom.service.AssignActivityService;
import com.money.custom.service.BannerService;
import com.money.custom.service.CustomerService;
import com.money.custom.service.GoodsService;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import com.money.framework.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.management.Query;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignActivityServiceImpl extends BaseServiceImpl implements AssignActivityService {

    @Autowired
    AssignActivityDao dao;
    @Autowired
    GoodsService goodsService;
    @Autowired
    CustomerService customerService;

    @Override
    public List<AssignActivity> selectSearchList(QueryAssignActivityRequest request) {
        return dao.selectSearchList(request);
    }

    @Override
    public int selectSearchListCount(QueryAssignActivityRequest request) {
        return dao.selectSearchListCount(request);
    }

    @Transactional
    @Override
    public String add(AssignActivityRequest request) {
        Goods activity = goodsService.findById(request.getActivityId().toString());
        Assert.notNull(activity, "活动不存在");
        Assert.isTrue(activity.getStatus().equals(CommonStatusEnum.ENABLE.getValue()), "活动已失效");
        Assert.isTrue(activity.getExpireDate().compareTo(DateUtils.nowDate()) >= 0, "活动已过期");

        QueryCustomerRequest queryCustomerRequest = new QueryCustomerRequest();
        queryCustomerRequest.setCustomerGroupIds(request.getItems().stream().map(AssignActivityRequest.AssignItem::getCustomerGroupId).collect(Collectors.toList()));
        List<Customer> customers = customerService.selectSearchList(queryCustomerRequest);
        Assert.isTrue(customers.size() == request.getItems().size(), "不可分配失效股东");
        Assert.isTrue(customers.stream().allMatch(i -> i.getCustomerGroup().getGroupId().equals(activity.getGroupId())), "活动和股东属于非同一家门店");

        AssignActivity assignActivity = new AssignActivity(request);
        assignActivity.setGoodsName(activity.getName());
        assignActivity.setSumPrice(activity.getSumPrice());
        assignActivity.setContainGoodsCnt(activity.getItems().size());
        assignActivity.setGroupId(activity.getGroupId());
        dao.add(assignActivity);

        List<AssignActivityItem> assignItems = request.getItems().stream().map(i -> {
            AssignActivityItem item = new AssignActivityItem(request, i);
            item.setAssignActivityId(assignActivity.getId());
            return item;
        }).collect(Collectors.toList());
        dao.addBatch(assignItems);


        return assignActivity.getId().toString();
    }
}
