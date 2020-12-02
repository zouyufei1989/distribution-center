package com.money.custom.entity;

import com.money.framework.base.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Banner extends BaseEntity {

    private Integer id;
    @Length(max = 20,message = "标题不可超过20个字符")
    @NotBlank(message = "请输入标题")
    private String title;
    @Length(max = 100,message = "链接不可超过100个字符")
    private String href;
    @NotBlank(message = "请上传图片")
    private String url;
    @Length(max = 500,message = "描述不可超过500个字符")
    private String desc;
    @NotNull(message = "请输入顺序")
    private Integer index;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }


}
