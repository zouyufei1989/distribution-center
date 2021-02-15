package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

import javax.validation.constraints.NotBlank;

public class ModifyGroupDetailImgsRequest extends OperationalEntity {

    @NotBlank(message = "请至少上传一张详情图片")
    private String detailCoverImg;

    public String getDetailCoverImg() {
        return detailCoverImg;
    }

    public void setDetailCoverImg(String detailCoverImg) {
        this.detailCoverImg = detailCoverImg;
    }
}
