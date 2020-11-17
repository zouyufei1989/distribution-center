package com.money.custom.entity;


import com.money.framework.base.entity.BaseEntity;

public class StsVisitLog extends BaseEntity {

    private String resourceName;
    private String moduleName;
    private String userName;
    private Integer count4Read;
    private Integer count4Edit;
    private Integer count4Import;
    private Integer count4Export;
    private Integer maxDuration;
    private Integer minDuration;
    private Integer avgDuration;

    public Integer getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(Integer maxDuration) {
        this.maxDuration = maxDuration;
    }

    public Integer getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(Integer minDuration) {
        this.minDuration = minDuration;
    }

    public Integer getAvgDuration() {
        return avgDuration;
    }

    public void setAvgDuration(Integer avgDuration) {
        this.avgDuration = avgDuration;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCount4Read() {
        return count4Read;
    }

    public void setCount4Read(Integer count4Read) {
        this.count4Read = count4Read;
    }

    public Integer getCount4Edit() {
        return count4Edit;
    }

    public void setCount4Edit(Integer count4Edit) {
        this.count4Edit = count4Edit;
    }

    public Integer getCount4Import() {
        return count4Import;
    }

    public void setCount4Import(Integer count4Import) {
        this.count4Import = count4Import;
    }

    public Integer getCount4Export() {
        return count4Export;
    }

    public void setCount4Export(Integer count4Export) {
        this.count4Export = count4Export;
    }
}
