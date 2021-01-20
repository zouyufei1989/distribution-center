package com.money.h5.entity.response;

import com.money.custom.entity.OrderPay;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.util.DateUtils;
import com.money.h5.entity.dto.PayInfoMonth;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@ApiModel
public class QueryOrderPayGroupByMonthResponse extends GridResponseBase {

    public QueryOrderPayGroupByMonthResponse() {}

    public QueryOrderPayGroupByMonthResponse(Integer monthInterval, List<OrderPay> orderPays) {
        List<PayInfoMonth> items = new ArrayList<>();

        for (int i = 0; i < monthInterval; i++) {
            String month = DateUtils.nextMonth(i * -1);
            PayInfoMonth payInfoMonth = new PayInfoMonth(month, orderPays);
            items.add(payInfoMonth);
        }

        super.total = monthInterval;
        super.records = monthInterval;
        super.rows = items;

    }

}
