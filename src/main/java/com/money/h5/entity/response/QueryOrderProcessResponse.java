package com.money.h5.entity.response;

import com.money.custom.entity.OrderConsumption;
import com.money.custom.entity.OrderPay;
import com.money.custom.entity.enums.GoodsCombineEnum;
import com.money.custom.entity.enums.GoodsTypeEnum;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.dto.H5OrderProcess;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.Assert;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryOrderProcessResponse extends ResponseBase {

    @Valid
    @ApiModelProperty(value = "项目购买、消费记录")
    private List<H5OrderProcess> items = new ArrayList<>();

    public QueryOrderProcessResponse() {}


    public QueryOrderProcessResponse(List<OrderConsumption> consumptions, List<OrderPay> pays) {
        if (CollectionUtils.isNotEmpty(consumptions)) {
            consumptions.forEach(c -> {
                if (c.getOrder().getGoodsCombine().equals(GoodsCombineEnum.COMBINE.getValue())) {
                    items.add(new H5OrderProcess(c.getItems().get(0)));
                } else {
                    items.addAll(c.getItems().stream().map(H5OrderProcess::new).collect(Collectors.toList()));
                }
            });
        }

        items.addAll(pays.stream().map(H5OrderProcess::new).collect(Collectors.toList()));
    }


    public List<H5OrderProcess> getItems() {
        return items;
    }
}
