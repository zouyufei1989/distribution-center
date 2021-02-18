package com.money.h5.entity.response;

import com.money.custom.entity.Reservation;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.util.DateUtils;
import com.money.h5.entity.dto.H5MyCustomer;
import io.swagger.annotations.ApiModel;

import java.util.List;
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

        public H5Reservation() {}

        public H5Reservation(Reservation item) {
            this.id = item.getId();
            this.reserveDate = DateUtils.format(item.getCreateDate(), "yyyy-MM-dd HH:mm:ss");
            this.cancelDate = DateUtils.format(item.getUpdateDate(), "yyyy-MM-dd HH:mm:ss");
            this.status = item.getStatus();
            this.statusName = item.getStatusName();
            this.goodsName = item.getGoodsName();
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
