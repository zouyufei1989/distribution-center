package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

public class CancelTaskRequest extends OperationalEntity {

    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
