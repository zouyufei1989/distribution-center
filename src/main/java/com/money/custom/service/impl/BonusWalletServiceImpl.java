package com.money.custom.service.impl;

import com.money.custom.dao.BonusWalletDao;
import com.money.custom.entity.*;
import com.money.custom.entity.enums.BonusChangeTypeEnum;
import com.money.custom.entity.enums.CustomerTypeEnum;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.enums.OrderStatusEnum;
import com.money.custom.entity.request.*;
import com.money.custom.service.*;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import com.money.h5.entity.response.QueryBonusDetailResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class BonusWalletServiceImpl extends BaseServiceImpl implements BonusWalletService {

    @Autowired
    BonusWalletDao dao;
    @Autowired
    CustomerGroupService customerGroupService;
    @Autowired
    CustomerService customerService;
    @Autowired
    SmsService smsService;
    @Autowired
    OrderService orderService;

    @Override
    public List<BonusWallet> selectSearchList(QueryBonusWalletRequest request) {
        return dao.selectSearchList(request);
    }

    @Override
    public Integer selectSearchListCount(QueryBonusWalletRequest request) {
        return dao.selectSearchListCount(request);
    }

    @Override
    public List<BonusWalletDetail> selectSearchList(QueryBonusWalletDetailRequest request) {
        return dao.selectSearchList4Detail(request);
    }

    @Override
    public Integer selectSearchListCount(QueryBonusWalletDetailRequest request) {
        return dao.selectSearchListCount4Detail(request);
    }

    @Override
    public BonusWallet findById(String id) {
        return dao.findById(id);
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.BONUS_WALLET)
    @Override
    public String add(BonusWallet item) {
        dao.add(item);
        return item.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.BONUS_WALLET)
    @Override
    public String edit(BonusWallet item) {
        dao.edit(item);
        return item.getId().toString();
    }

    @Transactional
    @Override
    public String recharge(BonusRechargeRequest request) {
        CustomerGroup customer = customerGroupService.findById(request.getCustomerGroupId().toString());
        Assert.notNull(customer, "????????????????????????");
        Assert.notNull(customer.getBonusWalletId(), "?????????????????????????????????");

        BonusWallet wallet = findById(customer.getBonusWalletId().toString());
        if (!customer.getType().equals(CustomerTypeEnum.SHARE_HOLDER.getValue())) {
            return wallet.getId().toString();
        }

        BonusWalletDetail detail = new BonusWalletDetail(request, wallet);
        dao.addDetail(detail);

        wallet.recharge(request);
        edit(wallet);
        return wallet.getId().toString();
    }

    @Transactional
    @Override
    public String deduction(DeductionRequest request) {
        CustomerGroup customer = customerGroupService.findById(request.getCustomerGroupId().toString());
        Assert.notNull(customer, "????????????????????????");
        Assert.notNull(customer.getBonusWalletId(), "?????????????????????????????????");

        BonusWallet wallet = findById(customer.getBonusWalletId().toString());

        BonusWalletDetail detail = new BonusWalletDetail(request, wallet);
        dao.addDetail(detail);

        wallet.deduction(request);
        edit(wallet);

        return wallet.getId().toString();
    }

    @Transactional
    @Override
    public void distribution(DistributeBonusRequest request) {
        Boolean distributeAll = request.getCustomerGroupIds().size() > 1;
        request.getCustomerGroupIds().forEach(customerGroupId -> {
            Customer customer = customerService.findById(customerGroupId.toString());

            Assert.notNull(customer, "????????????????????????");
            Assert.isTrue(customer.getCustomerGroup().getType().equals(CustomerTypeEnum.SHARE_HOLDER.getValue()), "??????????????????????????????");

            BonusWallet bonusWallet = customer.getBonusWallet();
            if (distributeAll) {
                request.setAmount(bonusWallet.getAvailableBonus());
            }
            Assert.isTrue(request.getAmount() > 0, "???????????????0");
            Assert.isTrue(bonusWallet.getAvailableBonus() >= request.getAmount(), "???????????????????????????");

            BonusWalletDetail detail = new BonusWalletDetail(request, bonusWallet);
            dao.addDetail(detail);

            bonusWallet.distribution(request);
            dao.edit(bonusWallet);
        });
    }

    @Override
    public void sendSms4BonusGained(String batchId) {
        QueryBonusWalletDetailRequest request = new QueryBonusWalletDetailRequest();
        request.setBatchId(batchId);
        List<BonusWalletDetail> bonusWalletDetails = selectSearchList(request);
        if (CollectionUtils.isEmpty(bonusWalletDetails)) {
            getLogger().warn("??????{}?????????????????????", batchId);
            return;
        }

        bonusWalletDetails.stream()
                .map(i -> Sms.bonusNotify(i.getCustomer().getPhone(), i.getBonusChange()))
                .forEach(i -> smsService.addSms(i));
    }

    @Transactional
    @Override
    public void orderRefund(OrderRefundRequest refundRequest) {
        OrderRefundParams orderRefundParams = orderService.queryOrderInfo4Refund(refundRequest.getOrderId());
        Assert.notNull(orderRefundParams, "???????????????");
        Assert.isTrue(orderRefundParams.getOrderStatus().equals(OrderStatusEnum.REFUND.getValue()), "????????????????????????");

        Assert.isTrue(!orderRefundParams.getOrderStatus().equals(OrderStatusEnum.REFUND_BONUS.getValue()),"????????????????????????");

        Assert.isTrue(refundRequest.getRefundAmount() <= orderRefundParams.getBonusGenerated(), "?????????????????????????????????????????????");
        Assert.isTrue(refundRequest.getRefundAmount() <= orderRefundParams.getAvailableBonus(), "??????????????????????????????????????????");


        CustomerGroup customer = customerGroupService.findById(orderRefundParams.getParentCustomerGroupId().toString());
        Assert.notNull(customer, "????????????????????????");
        Assert.notNull(customer.getBonusWalletId(), "?????????????????????????????????");

        BonusWallet wallet = findById(customer.getBonusWalletId().toString());

        BonusWalletDetail detail = new BonusWalletDetail(refundRequest, wallet);
        dao.addDetail(detail);

        wallet.deduction(refundRequest);
        edit(wallet);

        ChangeOrderStatusRequest changeOrderStatusRequest = new ChangeOrderStatusRequest(refundRequest.getOrderId().toString(), OrderStatusEnum.REFUND_BONUS.getValue());
        changeOrderStatusRequest.copyOperationInfo(refundRequest);
        orderService.changeStatus(changeOrderStatusRequest);

    }

}
