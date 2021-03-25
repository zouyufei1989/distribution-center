package com.money.h5.entity.response;

import com.money.custom.entity.Reservation;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.util.DateUtils;
import com.money.h5.entity.dto.H5MyCustomer;
import io.swagger.annotations.ApiModel;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApiModel
public class QueryMyReservationResponse extends GridResponseBase {

    public QueryMyReservationResponse() {}

    public QueryMyReservationResponse(Integer total, Integer records, List<Reservation> items) {
        super(total, records, items.stream().map(H5Reservation::new).collect(Collectors.toList()));
    }

    public static class H5Reservation {

        private Integer id;
        private String reserveDate;
        private String cancelDate;
        private Integer status;
        private String statusName;
        private String goodsName;
        private String date;
        private String startTime;
        private String endTime;

        public H5Reservation() {}

        public H5Reservation(Reservation item) {
            this.id = item.getId();
            this.reserveDate = DateUtils.format(item.getCreateDate(), "yyyy-MM-dd HH:mm:ss");
            if(Objects.nonNull(item.getCancelDate())){
                this.cancelDate = DateUtils.format(item.getCancelDate(), "yyyy-MM-dd HH:mm:ss");
            }
            this.status = item.getStatus();
            this.statusName = item.getStatusName();
            this.goodsName = item.getGoodsName();
            this.date = item.getDate();
            this.startTime = item.getStartTime();
            this.endTime = item.getEndTime();
        }

        public String getDate() {
            return date;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public Integer getId() {
            return id;
        }

        public String getReserveDate() {
            return reserveDate;
        }

        public String getCancelDate() {
            return cancelDate;
        }

        public Integer getStatus() {
            return status;
        }

        public String getStatusName() {
            return statusName;
        }

        public String getGoodsName() {
            return goodsName;
        }
    }
}
