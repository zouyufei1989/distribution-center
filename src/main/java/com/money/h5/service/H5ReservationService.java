package com.money.h5.service;

import com.money.custom.entity.Reservation;
import com.money.custom.entity.enums.ReservationStatusEnum;
import com.money.custom.entity.request.ChangeReservationStatusRequest;
import com.money.custom.service.ReservationService;
import com.money.framework.base.service.impl.BaseServiceImpl;
import com.money.h5.entity.dto.WechatPhoneResponse;
import com.money.h5.entity.request.CancelReserveRequest;
import com.money.h5.entity.request.TransWechatInfo2CustomerRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class H5ReservationService extends BaseServiceImpl {

    @Autowired
    ReservationService reservationService;

    public void cancelReserve(CancelReserveRequest request) {
        Reservation reservation = reservationService.findById(request.getReservationId().toString());
        Assert.notNull(reservation,"预约不存在");
        Assert.isTrue(StringUtils.equals(request.getOpenId(),reservation.getCreator()),"非本人预约，不可取消");

        ChangeReservationStatusRequest changeReservationStatusRequest = new ChangeReservationStatusRequest(request.getReservationId().toString(), ReservationStatusEnum.CANCEL.getValue());
        changeReservationStatusRequest.copyOperationInfo(request);
        reservationService.changeStatus(changeReservationStatusRequest);
    }
}
