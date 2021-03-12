package com.money.custom.task;

import com.money.custom.entity.Reservation;
import com.money.custom.entity.Sms;
import com.money.custom.entity.enums.ReservationStatusEnum;
import com.money.custom.entity.request.QueryReservationRequest;
import com.money.custom.rabbitmq.QueueConsts;
import com.money.custom.service.ReservationService;
import com.money.custom.service.SmsService;
import com.money.custom.utils.RabbitMqUtils;
import com.money.framework.util.DateUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@ConditionalOnExpression("'${switch.task}'.equals('on')")
@Component
public class ReserveRemindTask extends ConfigurableTaskBase {

    @Autowired
    ReservationService reservationService;
    @Autowired
    SmsService smsService;

    @Override
    void execute(Map<String, String> params) {
        int minutesAdvanced = useIntParamsIfPresent(params, "minutesAdvanced", 24 * 60);
        String datetime = DateUtils.nowDateTime();
        long timestamp = DateUtils.parse(datetime, "yyyy-MM-dd HH:mm").getTime();

        QueryReservationRequest queryReservationRequest = new QueryReservationRequest();
        queryReservationRequest.setStatus(ReservationStatusEnum.SUCCESS.getValue());
        List<Reservation> reservations = reservationService.selectSearchList(queryReservationRequest);
        getLogger().info("预约成功记录{}个", reservations.size());
        if (CollectionUtils.isEmpty(reservations)) {
            return;
        }

        for (Reservation reservation : reservations) {
            long diff = (reservation.getTimestamp() - timestamp) / 1000 / 60;
            getLogger().info("预约 {} ：预约时间{} 当前时间{} 相差时间 {}分  基准差{}分", reservation.getId(), reservation.getDate() + " " + reservation.getStartTime(), datetime, diff, minutesAdvanced);
            if (diff != minutesAdvanced) {
                continue;
            }
            smsService.addSms(Sms.reservationNotify(reservation.getPhone()));
            getLogger().info("预约{}短信通知已下发", reservation.getId());
        }
    }


}
