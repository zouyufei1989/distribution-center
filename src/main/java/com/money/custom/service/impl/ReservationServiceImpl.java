package com.money.custom.service.impl;

import com.money.custom.dao.ReservationDao;
import com.money.custom.entity.*;
import com.money.custom.entity.dto.ReservationCalendar;
import com.money.custom.entity.enums.*;
import com.money.custom.entity.request.*;
import com.money.custom.service.*;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import com.money.framework.util.DateUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl extends BaseServiceImpl implements ReservationService {

    @Autowired
    ReservationDao dao;
    @Autowired
    CustomerService customerService;
    @Autowired
    OrderService orderService;
    @Autowired
    GroupReservationPeriodService groupReservationPeriodService;
    @Autowired
    GroupService groupService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderConsumptionService orderConsumptionService;

    @Value("${group.reserve_days.start}")
    int RESERVE_DAYS_START;

    @Override
    public List<Reservation> selectSearchList(QueryReservationRequest request) {
        return dao.selectSearchList(request);
    }

    @Override
    public int selectSearchListCount(QueryReservationRequest request) {
        return dao.selectSearchListCount(request);
    }

    @Override
    public Reservation findById(String id) {
        return dao.findById(id);
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.RESERVATION)
    @Override
    public String add(Reservation item) {
        Assert.hasText(item.getOpenId(), "未知用户id");
        checkReservationParam(item);
        Order order = checkReservationOrder(item);
        Customer customer = checkReservationCustomer(item, order);
        checkReservationUnique(item, order, (items) -> items.size() == 0);
        checkReservationPeriod(item, order);

        item.setCustomerGroupId(customer.getCustomerGroup().getId());
        item.setStatus(ReservationStatusEnum.SUCCESS.getValue());
        dao.add(item);
        return item.getId().toString();
    }

    private void checkReservationPeriod(Reservation item, Order order) {
        QueryReservationCalenderByOrderIdRequest queryReservationCalenderRequest = new QueryReservationCalenderByOrderIdRequest();
        queryReservationCalenderRequest.setStartDate(item.getDate());
        queryReservationCalenderRequest.setEndDate(item.getDate());
        queryReservationCalenderRequest.setOrderId(order.getId());
        List<ReservationCalendar> reservationCalendars = queryReservationCalender(queryReservationCalenderRequest);
        Optional<ReservationCalendar> reservationPeriodOpt = reservationCalendars.stream().filter(i -> i.getStart().equals(item.getStartTime()) && i.getEnd().equals(item.getEndTime())).findAny();
        Assert.isTrue(reservationPeriodOpt.isPresent(), "预约时间段不存在");
        Assert.isTrue(reservationPeriodOpt.get().getAvailable() > 0, "选中时间段预约已满");
    }

    private void checkReservationUnique(Reservation item, Order order, Predicate<List<Reservation>> checkFunc) {
        QueryReservationRequest queryReservationRequest = new QueryReservationRequest();
        queryReservationRequest.setOrderId(order.getId());
        queryReservationRequest.setDate(item.getDate());
        queryReservationRequest.setStatus(ReservationStatusEnum.SUCCESS.getValue());
        List<Reservation> reservations = selectSearchList(queryReservationRequest);
        Assert.isTrue(checkFunc.test(reservations), "今日已预约该项目");
    }

    private Customer checkReservationCustomer(Reservation item, Order order) {
        QueryCustomerRequest queryCustomerRequest = new QueryCustomerRequest();
        queryCustomerRequest.setOpenId(item.getOpenId());
        queryCustomerRequest.setGroupId(order.getGroupId());
        List<Customer> customers = customerService.selectSearchList(queryCustomerRequest);
        Assert.notEmpty(customers, "用户不存在");
        Customer customer = customers.get(0);
        Assert.isTrue(customer.getCustomerGroup().getStatus().equals(CommonStatusEnum.ENABLE.getValue()), "用户状态非法");
        Assert.isTrue(order.getCustomerGroupId().equals(customer.getCustomerGroup().getId()), "订单与用户信息不匹配");
        return customer;
    }

    private Order checkReservationOrder(Reservation item) {
        Order order = orderService.findById(item.getOrderId().toString());
        Assert.notNull(order, "订单不存在");
        Assert.isTrue(order.getStatus().equals(OrderStatusEnum.USING.getValue()), "订单状态非法");
        Assert.isTrue(order.getGoodsTypeId().equals(GoodsTypeEnum.PACKAGE.getValue()) || order.getGoodsTypeId().equals(GoodsTypeEnum.ACTIVITY.getValue()), "仅支持套餐/活动预约");
        return order;
    }

    private void checkReservationParam(Reservation item) {
        Assert.notNull(item.getOrderId(), "未知订单");
        Assert.hasText(item.getDate(), "预约日期不可为空");
        Assert.hasText(item.getStartTime(), "预约开始时间不可为空");
        Assert.hasText(item.getEndTime(), "预约结束时间不可为空");
        Assert.isTrue(item.getStartTime().compareTo(item.getEndTime()) < 0, "结束时间不可早于开始时间");
        if (DateUtils.nowDate().equals(item.getDate())) {
            Assert.isTrue(DateUtils.timeCompareNow(item.getEndTime()) > 0, "预约时间不可早于当前时间");
        }
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.RESERVATION)
    @Override
    public String edit(Reservation item) {
        Reservation reservation = findById(item.getId().toString());
        Assert.notNull(reservation, "预约不存在");
        Assert.isTrue(reservation.getStatus().equals(ReservationStatusEnum.SUCCESS.getValue()), "当前状态不可修改");

        checkReservationParam(item);
        Order order = checkReservationOrder(item);
        checkReservationPeriod(item, order);
        checkReservationUnique(item, order, (items) -> items.size() == 0 || items.get(0).getId() == item.getId());
        dao.edit(item);
        return item.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.RESERVATION)
    @Override
    public List<String> changeStatus(ChangeReservationStatusRequest request) {
        dao.changeStatus(request);
        return request.getIds();
    }

    @Override
    public List<ReservationCalendar> queryReservationCalender(QueryReservationCalenderByOrderIdRequest request) {
        Order order = orderService.findById(request.getOrderId().toString());
        Assert.notNull(order, "订单不存在");

        QueryReservationCalenderByGoodsIdRequest req = new QueryReservationCalenderByGoodsIdRequest();
        req.setStartDate(request.getStartDate());
        req.setEndDate(request.getEndDate());
        req.setGoodsId(order.getGoodsId());

        return queryReservationCalender(req);
    }

    @Override
    public List<ReservationCalendar> queryReservationCalender(QueryReservationCalenderByGoodsIdRequest request) {
        Assert.hasText(request.getStartDate(), "开始日期不可为空");
        Assert.hasText(request.getEndDate(), "结束日期不可为空");
        Assert.notNull(request.getGoodsId(), "请指定商品id");
        Assert.isTrue(request.getStartDate().compareTo(request.getEndDate()) <= 0, "开始日期不可晚于结束日期");
        Assert.isTrue(request.getStartDate().compareTo(DateUtils.nextNDayStr(RESERVE_DAYS_START)) >= 0, "预约日期不可早于" + DateUtils.nextNDayStr(RESERVE_DAYS_START));

        List<ReservationCalendar> reservationCalendars = new ArrayList<>();

        Goods goods = goodsService.findById(request.getGoodsId().toString());
        Assert.notNull(goods, "商品不存在");

        Group group = groupService.findById(goods.getGroupId().toString());
        Assert.notNull(group, "未查询到门店信息");
        Assert.isTrue(GroupReserveFlagEnum.YES.getValue().equals(group.getReserveFlag()), "门店不可预约");
        if (Objects.nonNull(group.getReserveDays())) {
            Assert.isTrue(DateUtils.nextNDayStr(group.getReserveDays()).compareTo(request.getEndDate()) >= 0, "只可预约" + group.getReserveDays() + "天内到店");
        }

        QueryGroupReservationPeriodRequest queryGroupReservationPeriodRequest = new QueryGroupReservationPeriodRequest();
        queryGroupReservationPeriodRequest.setGoodsId(goods.getId());
        List<GroupReservationPeriod> groupReservationPeriods = groupReservationPeriodService.selectSearchList(queryGroupReservationPeriodRequest);
        Assert.notEmpty(groupReservationPeriods, "项目未设置预约时间段");
        int maxPerDay = groupReservationPeriods.stream().mapToInt(GroupReservationPeriod::getCnt).sum();

        QueryReservationRequest queryReservationRequest = new QueryReservationRequest();
        queryReservationRequest.setGoodsId(goods.getId());
        queryReservationRequest.setStartDate(request.getStartDate());
        queryReservationRequest.setEndDate(request.getEndDate());
        queryReservationRequest.setStatus(ReservationStatusEnum.SUCCESS.getValue());
        List<Reservation> reservations = selectSearchList(queryReservationRequest);
        getLogger().info("{}至{}共{}个预约", request.getStartDate(), request.getEndDate(), reservations.size());

        if (request.getStartDate().equals(request.getEndDate())) {
            for (GroupReservationPeriod period : groupReservationPeriods) {
                long usedCnt = reservations.stream()
                        .filter(r -> r.getStatus().equals(ReservationStatusEnum.SUCCESS.getValue()))
                        .filter(r -> r.getStartTime().compareTo(period.getStartTime()) >= 0 && r.getStartTime().compareTo(period.getEndTime()) <= 0).count();
                if (StringUtils.equals(request.getStartDate(), DateUtils.nowDate()) && DateUtils.timeCompareNow(period.getEndTime()) <= 0) {
                    usedCnt = period.getCnt();
                }
                ReservationCalendar calendar = new ReservationCalendar(period, usedCnt);
                reservationCalendars.add(calendar);
            }
        } else {
            Map<String, List<Reservation>> reservationsMap = reservations.stream().collect(Collectors.groupingBy(Reservation::getDate));
            String date = request.getStartDate();
            while (date.compareTo(request.getEndDate()) <= 0) {
                ReservationCalendar calendar = new ReservationCalendar(date, maxPerDay, reservationsMap.containsKey(date) ? reservationsMap.get(date).size() : 0);
                reservationCalendars.add(calendar);
                date = DateUtils.addDay(date, 1, "yyyy-MM-dd");
            }
        }

        return reservationCalendars;
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.RESERVATION)
    @Transactional
    @Override
    public String consumeReservation(ReservationConsumptionRequest request) {

        Reservation reservation = findById(request.getReservationId().toString());
        Assert.isTrue(StringUtils.equals(reservation.getDate(), DateUtils.nowDate()), "预约日期与当前日期不符");
        Assert.notNull(reservation, "预约不存在");
        Assert.isTrue(reservation.getStatus().equals(ReservationStatusEnum.SUCCESS.getValue()), "当前状态不可修改");
        reservation.setOrderId(request.getOrderId());
        reservation.setStatus(ReservationStatusEnum.ARRIVED.getValue());
        reservation.copyOperationInfo(request);
        dao.edit(reservation);

        ConsumeRequest consumeRequest = new ConsumeRequest(request);
        orderConsumptionService.consume(consumeRequest);
        getLogger().info("已到店使用预约{}的订单{} {}次", request.getReservationId(), request.getOrderId(), request.getCnt());

        if (CollectionUtils.isNotEmpty(request.getConsumeRequests())) {
            getLogger().info("预约有额外消费项目 :{}个", request.getConsumeRequests().size());
            request.getConsumeRequests().forEach(req -> {
                req.copyOperationInfo(request);
                req.setReservationId(request.getReservationId());
                orderConsumptionService.consume(req);
            });
        }

        if (Objects.nonNull(request.getPurchaseConsumeRequest())) {
            getLogger().info("预约有额外消费单品");
            request.getPurchaseConsumeRequest().copyOperationInfo(request);
            request.getPurchaseConsumeRequest().setReservationId(request.getReservationId());
            customerService.purchaseThenConsumeAll(request.getPurchaseConsumeRequest());
        }

        return request.getReservationId().toString();
    }

}
