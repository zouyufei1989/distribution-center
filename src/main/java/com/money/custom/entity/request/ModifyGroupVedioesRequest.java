package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

import javax.validation.constraints.NotBlank;

public class ModifyGroupVedioesRequest extends OperationalEntity {

    @NotBlank(message = "请至少上传一段介绍视频")
    private String videoList;

    public String getVideoList() {
        return videoList;
    }

    public void setVideoList(String videoList) {
        this.videoList = videoList;
    }
}
