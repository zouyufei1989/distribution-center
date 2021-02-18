package com.money.custom.service.impl;

import com.money.custom.dao.ReservationDao;
import com.money.custom.entity.Customer;
import com.money.custom.entity.GroupReservationPeriod;
import com.money.custom.entity.Order;
import com.money.custom.entity.Reservation;
import com.money.custom.entity.dto.ReservationCalendar;
import com.money.custom.entity.enums.*;
import com.money.custom.entity.request.*;
import com.money.custom.service.CustomerService;
import com.money.custom.service.GroupReservationPeriodService;
import com.money.custom.service.OrderService;
import com.money.custom.service.ReservationService;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import com.money.framework.util.DateUtils;
import org.apache.http.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;
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
        Asserts.notBlank(item.getOpenId(), "未知用户id");
        Asserts.notNull(item.getOrderId(), "未知订单");
        Asserts.notBlank(item.getDate(), "预约日期不可为空");
        Asserts.notBlank(item.getStartTime(), "预约开始时间不可为空");
        Asserts.notBlank(item.getEndTime(), "预约结束时间不可为空");
        Assert.isTrue(item.getDate().compareTo(DateUtils.nowDate()) >= 0, "预约日期不可早于" + DateUtils.nowDate());
        Assert.isTrue(item.getStartTime().compareTo(item.getEndTime()) < 0, "结束时间不可早于开始时间");
        if(DateUtils.nowDate().equals(item.getDate())){
            Assert.isTrue(item.getEndTime().compareTo(DateUtils.nowTime() + ":00") >= 0, "预约时间不可早于当前时间");
        }

        Order order = orderService.findById(item.getOrderId().toString());
        Asserts.notNull(order, "订单不存在");
        Assert.isTrue(order.getStatus().equals(OrderStatusEnum.USING.getValue()), "订单状态非法");
        Assert.isTrue(order.getGoodsTypeId().equals(GoodsTypeEnum.PACKAGE.getValue()), "仅支持套餐预约");

        QueryCustomerRequest queryCustomerRequest = new QueryCustomerRequest();
        queryCustomerRequest.setOpenId(item.getOpenId());
        queryCustomerRequest.setGroupId(order.getGroupId());
        List<Customer> customers = customerService.selectSearchList(queryCustomerRequest);
        Assert.notEmpty(customers, "用户不存在");
        Customer customer = customers.get(0);
        Assert.isTrue(customer.getCustomerGroup().getStatus().equals(CommonStatusEnum.ENABLE.getValue()), "用户状态非法");
        Assert.isTrue(order.getCustomerGroupId().equals(customer.getCustomerGroup().getId()), "订单与用户信息不匹配");

        QueryReservationCalenderRequest queryReservationCalenderRequest = new QueryReservationCalenderRequest();
        queryReservationCalenderRequest.setStartDate(item.getDate());
        queryReservationCalenderRequest.setEndDate(item.getDate());
        queryReservationCalenderRequest.setGroupId(order.getGroupId());
        List<ReservationCalendar> reservationCalendars = queryReservationCalender(queryReservationCalenderRequest);
        Optional<ReservationCalendar> reservationPeriodOpt = reservationCalendars.stream().filter(i -> i.getStart().equals(item.getStartTime()) && i.getEnd().equals(item.getEndTime())).findAny();
        Assert.isTrue(reservationPeriodOpt.isPresent(), "预约时间段非法");
        Assert.isTrue(reservationPeriodOpt.get().getAvailable() > 0, "选中时间段预约已满");

        item.setCustomerGroupId(customer.getCustomerGroup().getId());
        item.setStatus(ReservationStatusEnum.SUCCESS.getValue());
        dao.add(item);
        return item.getId().toString();
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.RESERVATION)
    @Override
    public List<String> changeStatus(ChangeReservationStatusRequest request) {
        dao.changeStatus(request);
        return request.getIds();
    }

    @Override
    public List<ReservationCalendar> queryReservationCalender(QueryReservationCalenderRequest request) {
        List<ReservationCalendar> reservationCalendars = new ArrayList<>();

        Integer groupId = request.getGroupId();
        if (Objects.isNull(groupId)) {
            Assert.notNull(request.getOrderId(), "无法定位到所属门店");
            Order order = orderService.findById(request.getOrderId().toString());
            Assert.notNull(order, "订单不存在");
            groupId = order.getGroupId();
        }

        QueryGridRequestBase queryGridRequestBase = new QueryGridRequestBase();
        queryGridRequestBase.setGroupId(groupId);
        List<GroupReservationPeriod> groupReservationPeriods = groupReservationPeriodService.selectSearchList(queryGridRequestBase);
        Assert.notEmpty(groupReservationPeriods, "门店尚未设置预约时间段");
        int maxPerDay = groupReservationPeriods.stream().mapToInt(GroupReservationPeriod::getCnt).sum();

        QueryReservationRequest queryReservationRequest = new QueryReservationRequest();
        queryReservationRequest.setGroupId(groupId);
        queryReservationRequest.setStartDate(request.getStartDate());
        queryReservationRequest.setEndDate(request.getEndDate());
        queryReservationRequest.setStatus(ReservationStatusEnum.SUCCESS.getValue());
        List<Reservation> reservations = selectSearchList(queryReservationRequest);
        getLogger().info("{}至{}共{}个预约", request.getStartDate(), request.getEndDate(), reservations.size());

        if (request.getStartDate().equals(request.getEndDate())) {
            for (GroupReservationPeriod period : groupReservationPeriods) {
                long usedCnt = reservations.stream().filter(r -> r.getStartTime().compareTo(period.getStartTime()) >= 0 && r.getStartTime().compareTo(period.getEndTime()) <= 0).count();
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

}
