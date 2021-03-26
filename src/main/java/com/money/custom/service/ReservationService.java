package com.money.custom.service;

import com.money.custom.entity.Reservation;
import com.money.custom.entity.dto.ReservationCalendar;
import com.money.custom.entity.request.*;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface ReservationService extends BaseService {

    List<Reservation> selectSearchList(QueryReservationRequest request);

    int selectSearchListCount(QueryReservationRequest request);

    Reservation findById(String id);

    String add(Reservation item);

    String edit(Reservation item);

    List<String> changeStatus(ChangeReservationStatusRequest request);

    List<ReservationCalendar> queryReservationCalender(QueryReservationCalenderByOrderIdRequest request);

    List<ReservationCalendar> queryReservationCalender(QueryReservationCalenderByGoodsIdRequest request);

    String consumeReservation(ReservationConsumptionRequest request);
}
