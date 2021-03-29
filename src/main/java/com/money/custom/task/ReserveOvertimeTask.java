package com.money.custom.task;

import com.money.custom.entity.Reservation;
import com.money.custom.entity.Sms;
import com.money.custom.entity.enums.ReservationStatusEnum;
import com.money.custom.entity.request.ChangeReservationStatusRequest;
import com.money.custom.entity.request.QueryReservationRequest;
import com.money.custom.service.ReservationService;
import com.money.custom.service.SmsService;
import com.money.framework.util.DateUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ConditionalOnExpression("'${switch.task}'.equals('on')")
@Component
public class ReserveOvertimeTask extends ConfigurableTaskBase {

    @Autowired
    ReservationService reservationService;

    @Override
    void execute(Map<String, String> params) {
        int minutesOver = useIntParamsIfPresent(params, "minutesOver", 2 * 60);
        String datetime = DateUtils.nowDateTime();
        long timestamp = DateUtils.parse(datetime, "yyyy-MM-dd HH:mm").getTime();

        QueryReservationRequest queryReservationRequest = new QueryReservationRequest();
        queryReservationRequest.setStatus(ReservationStatusEnum.SUCCESS.getValue());
        queryReservationRequest.setDate(DateUtils.nowDate());
        List<Reservation> reservations = reservationService.selectSearchList(queryReservationRequest);
        getLogger().info("{}预约成功记录{}个", DateUtils.nowDate(), reservations.size());
        if (CollectionUtils.isEmpty(reservations)) {
            return;
        }

        List<String> reservationIds = new ArrayList<>();

        for (Reservation reservation : reservations) {
            long diff = (timestamp - reservation.getTimestamp()) / 1000 / 60;
            getLogger().info("预约 {} ：预约时间{} 当前时间{} 相差时间 {}分  基准差{}分", reservation.getId(), reservation.getDate() + " " + reservation.getStartTime(), datetime, diff, minutesOver);
            if (diff < minutesOver) {
                continue;
            }
            reservationIds.add(reservation.getId().toString());
            getLogger().info("预约{}已超时", reservation.getId());
        }

        getLogger().info("共{}个预约超时", reservationIds.size());
        if(CollectionUtils.isEmpty(reservationIds)){
            return;
        }

        ChangeReservationStatusRequest changeReservationStatusRequest = new ChangeReservationStatusRequest(reservationIds, ReservationStatusEnum.OVERTIME.getValue());
        changeReservationStatusRequest.setSrcStatus(ReservationStatusEnum.SUCCESS);
        changeReservationStatusRequest.ofTask(this);
        reservationService.changeStatus(changeReservationStatusRequest);
    }


}
