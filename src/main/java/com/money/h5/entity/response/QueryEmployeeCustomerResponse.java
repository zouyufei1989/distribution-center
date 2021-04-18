package com.money.h5.entity.response;

import com.money.custom.entity.dto.TreeNodeDto;
import com.money.framework.base.entity.ResponseBase;
import io.swagger.annotations.ApiModel;

import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryEmployeeCustomerResponse extends ResponseBase {

    public QueryEmployeeCustomerResponse() {}

    public QueryEmployeeCustomerResponse(List<TreeNodeDto> items) {
        this.setData(items.stream().map(H5CustomerOfEmployee::new).collect(Collectors.toList()));
    }

    public static class H5CustomerOfEmployee {
        private Integer id;
        private String name;
        private String openId;
        private Integer childrenCnt;
        private String phone;
        private String avatar;

        public H5CustomerOfEmployee(TreeNodeDto item) {
            id = item.getCustomer().getCustomerGroup().getId();
            name = item.getCustomer().getName();
            openId = item.getCustomer().getOpenId();
            phone = item.getCustomer().getPhone();
            avatar = item.getCustomer().getHeadCover();
            childrenCnt = item.getChildren().size();
        }

        public String getAvatar() {
            return avatar;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public Integer getChildrenCnt() {
            return childrenCnt;
        }

        public void setChildrenCnt(Integer childrenCnt) {
            this.childrenCnt = childrenCnt;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

}
