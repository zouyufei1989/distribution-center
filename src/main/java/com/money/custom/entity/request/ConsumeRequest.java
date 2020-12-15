package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ConsumeRequest extends OperationalEntity {

    @NotEmpty(message = "请选择要消费的订单")
    private List<Order> orderChoosed;

    public List<Order> getOrderChoosed() {
        return orderChoosed;
    }

    public void setOrderChoosed(List<Order> orderChoosed) {
        this.orderChoosed = orderChoosed;
    }

    @Valid
    public static class Order {
        @NotNull(message = "订单id不可为空")
        private Integer id;
        @Min(value = 1, message = "消费数量最小为1")
        @NotNull(message = "请输入要消费的数量")
        private Integer cnt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getCnt() {
            return cnt;
        }

        public void setCnt(Integer cnt) {
            this.cnt = cnt;
        }
    }
}
