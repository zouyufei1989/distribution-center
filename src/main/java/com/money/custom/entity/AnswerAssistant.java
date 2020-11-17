package com.money.custom.entity;

import com.money.custom.entity.enums.CommonStatusEnum;
import com.money.framework.base.entity.BaseEntity;
import com.money.framework.util.EnumUtils;

import java.lang.reflect.InvocationTargetException;

public class AnswerAssistant extends BaseEntity {

    private Integer id;
    private String name;
    private String question;
    private String answer;
    private Integer status;

    private String keywords;

    public String getStatusName() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return EnumUtils.getNameByValue(CommonStatusEnum.class, this.status);
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
