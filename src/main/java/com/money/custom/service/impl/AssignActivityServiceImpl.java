package com.money.custom.service.impl;

import com.money.custom.dao.AssignActivityDao;
import com.money.custom.entity.*;
import com.money.custom.entity.enums.*;
import com.money.custom.entity.request.*;
import com.money.custom.service.*;
import com.money.framework.base.entity.OperationalEntity;
import com.money.framework.base.service.impl.BaseServiceImpl;
import com.money.framework.util.DateUtils;
import com.money.framework.util.RedisUtils;
import com.money.h5.entity.H5GridRequestBase;
import org.apache.commons.collections4.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AssignActivityServiceImpl extends BaseServiceImpl implements AssignActivityService {

    @Autowired
    AssignActivityDao dao;
    @Autowired
    GoodsService goodsService;
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerGroupService customerGroupService;
    @Autowired
    OrderService orderService;
    @Autowired
    WalletService walletService;
    @Autowired
    BonusWalletService bonusWalletService;
    @Autowired
    ActivityClaimRecordService claimRecordService;
    @Autowired
    UtilsService utilsService;
    @Autowired
    RedisUtils redisUtils;

    @Override
    public List<AssignActivity> selectSearchList(QueryAssignActivityRequest request) {
        return dao.selectSearchList(request);
    }

    @Override
    public int selectSearchListCount(QueryAssignActivityRequest request) {
        return dao.selectSearchListCount(request);
    }

    @Override
    public List<CustomerActivity> selectCustomerActivityList(H5GridRequestBase request) {
        return dao.selectCustomerActivityList(request);
    }

    @Override
    public Integer selectCustomerActivityCount(H5GridRequestBase request) {
        return dao.selectCustomerActivityCount(request);
    }

    @Transactional
    @Override
    public String add(AssignActivityRequest request) {
        Goods activity = goodsService.findById(request.getActivityId().toString());
        Assert.notNull(activity, "???????????????");
        Assert.isTrue(activity.getStatus().equals(CommonStatusEnum.ENABLE.getValue()), "???????????????");
        Assert.isTrue(activity.getExpireDate().compareTo(DateUtils.nowDate()) >= 0, "???????????????");
        if (activity.getType().equals(GoodsTypeEnum.ACTIVITY.getValue())) {
            Assert.isTrue(CollectionUtils.isNotEmpty(activity.getItems()), "????????????????????????????????????");
        }

        QueryCustomerRequest queryCustomerRequest = new QueryCustomerRequest();
        queryCustomerRequest.setCustomerGroupIds(request.getItems().stream().map(AssignActivityRequest.AssignItem::getCustomerGroupId).collect(Collectors.toList()));
        List<Customer> customers = customerService.selectSearchList(queryCustomerRequest);
        Assert.isTrue(customers.size() == request.getItems().size(), "????????????????????????");
        Assert.isTrue(customers.stream().allMatch(i -> i.getCustomerGroup().getGroupId().equals(activity.getGroupId())), "???????????????????????????????????????");

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

    @Override
    public List<Map<String, Object>> querySummary() {
        return dao.querySummary();
    }

    @Transactional
    @Override
    public void claimActivity(String code, String receiverOpenId) {
        Assert.hasText(code, "????????????");
        Integer assignActivityId = redisUtils.getObject(code, Integer.class);

        Assert.notNull(assignActivityId, "???????????????");
        Assert.notNull(receiverOpenId, "openId???????????????");

        Customer newCustomer = customerService.findByOpenId(receiverOpenId);
        Assert.notNull(newCustomer, "?????????????????????");
        List<CustomerGroup> newCustomerGroups = customerGroupService.findByOpenId(receiverOpenId);

        AssignActivityItem activityAssign = dao.findAssignActivityItemById(assignActivityId.toString());
        Assert.notNull(activityAssign, "??????????????????");
        Assert.isTrue(activityAssign.getAvailableCnt() > 0, "????????????????????????");

        Customer parent = customerService.findById(activityAssign.getCustomerGroupId().toString());
        Assert.notNull(parent, "??????????????????????????????");

        Goods activity = goodsService.findById(activityAssign.getGoodsId().toString());
        Assert.notNull(activity, "??????????????????");
        Assert.isTrue(activity.getStatus().equals(CommonStatusEnum.ENABLE.getValue()), "???????????????");

        Integer groupId = activityAssign.getGroupId();
        String newCustomerGroupId = "";
        Optional<CustomerGroup> optionalCustomerGroup = newCustomerGroups.stream().filter(cg -> groupId.equals(cg.getGroupId())).findAny();
        if (optionalCustomerGroup.isPresent()) {
            if (activity.getScope().equals(ActivityScopeEnum.ONLY_NEW.getValue())) {
                Assert.isTrue(optionalCustomerGroup.get().getTotalNew().equals(CustomerTotalNewEnum.NEW.getValue()), "???????????????????????????");
            }

            QueryOrderRequest queryOrderRequest = new QueryOrderRequest();
            queryOrderRequest.setGoodsId(activity.getId());
            queryOrderRequest.getCustomer().setOpenId(receiverOpenId);
            int claimCnt = orderService.selectSearchListCount(queryOrderRequest);
            Assert.isTrue(claimCnt < activity.getMaxCntPerCus(), "???????????????????????????");

            newCustomerGroupId = optionalCustomerGroup.get().getId().toString();
        } else {
            newCustomerGroupId = addCustomerGroup(receiverOpenId, newCustomer, parent, groupId);
        }

        purchaseActivity(receiverOpenId, activity, newCustomerGroupId);
        addClaimRecord(assignActivityId, receiverOpenId, activityAssign, parent, activity, newCustomerGroupId);

        dao.claimActivity(assignActivityId);

        activityAssign = dao.findAssignActivityItemById(assignActivityId.toString());
        Assert.isTrue(activityAssign.getAvailableCnt() > -1, "????????????????????????");

        redisUtils.delete(code);
    }

    @Override
    public String getActivityDistributionUniqueCode(String assignActivityId) {
        Assert.notNull(assignActivityId, "??????id????????????");

        AssignActivityItem activityAssign = dao.findAssignActivityItemById(assignActivityId);
        Assert.notNull(activityAssign, "??????????????????");
        Assert.isTrue(activityAssign.getAvailableCnt() > 0, "????????????????????????");

        Goods activity = goodsService.findById(activityAssign.getGoodsId().toString());
        Assert.notNull(activity, "??????????????????");
        Assert.isTrue(activity.getStatus().equals(CommonStatusEnum.ENABLE.getValue()), "???????????????");

        String code = RedisKeyEnum.ACTIVITY_DISTRIBUTION.getName() + UUID.randomUUID().toString();
        redisUtils.setObject(code, assignActivityId, RedisKeyEnum.ACTIVITY_DISTRIBUTION.getTimeout());
        return code;
    }

    private String addCustomerGroup(String receiverOpenId, Customer newCustomer, Customer parent, Integer groupId) {
        OperationalEntity operationalEntity = new OperationalEntity();
        operationalEntity.ofH5(receiverOpenId);

        String newCustomerGroupId;
        CustomerGroup newCustomerGroup = new CustomerGroup();
        String walletId = walletService.add(Wallet.totalNew(operationalEntity));
        String bonusWalletId = bonusWalletService.add(BonusWallet.totalNew(operationalEntity));
        newCustomerGroup.setCustomerId(newCustomer.getId());
        newCustomerGroup.setWalletId(Integer.parseInt(walletId));
        newCustomerGroup.setBonusWalletId(Integer.parseInt(bonusWalletId));
        newCustomerGroup.setGroupId(groupId);
        newCustomerGroup.setTotalNew(CustomerTotalNewEnum.NEW.getValue());
        newCustomerGroup.setType(CustomerTypeEnum.NORMAL.getValue());
        newCustomerGroup.setSerialNumber(utilsService.generateSerialNumber(SerialNumberEnum.CS));
        newCustomerGroup.setStatus(CommonStatusEnum.ENABLE.getValue());
        newCustomerGroup.setParentId(parent.getCustomerGroup().getId());
        newCustomerGroup.ofH5(receiverOpenId);
        newCustomerGroupId = customerGroupService.add(newCustomerGroup);
        return newCustomerGroupId;
    }

    private void addClaimRecord(Integer assignActivityId, String receiverOpenId, AssignActivityItem activityAssign, Customer parent, Goods activity, String newCustomerGroupId) {
        ActivityClaimRecord claimRecord = new ActivityClaimRecord();
        claimRecord.setActivityAssignId(assignActivityId);
        claimRecord.setClaimCustomerGroupId(Integer.parseInt(newCustomerGroupId));
        claimRecord.setClaimOpenId(receiverOpenId);
        claimRecord.setGoodsId(activityAssign.getGoodsId());
        claimRecord.setGoodsName(activity.getName());
        claimRecord.setSrcCustomerGroupId(activityAssign.getCustomerGroupId());
        claimRecord.setSrcOpenId(parent.getOpenId());
        claimRecord.ofH5(receiverOpenId);
        claimRecordService.add(claimRecord);
    }

    private void purchaseActivity(String receiverOpenId, Goods activity, String newCustomerGroupId) {
        PurchaseRequest.Goods claimActivity = new PurchaseRequest.Goods();
        claimActivity.setId(activity.getId());
        claimActivity.setCnt(1);
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        purchaseRequest.setSumMoney(activity.getSumPrice());
        purchaseRequest.setActuallyMoney(0L);
        purchaseRequest.setCustomerGroupId(Integer.parseInt(newCustomerGroupId));
        purchaseRequest.setPayBonus(false);
        purchaseRequest.setPayMoney(false);
        purchaseRequest.setPayOffline(false);
        purchaseRequest.setGoodsChoosed(Lists.newArrayList(claimActivity));
        purchaseRequest.ofH5(receiverOpenId);
        customerService.purchase(purchaseRequest);
    }


}
