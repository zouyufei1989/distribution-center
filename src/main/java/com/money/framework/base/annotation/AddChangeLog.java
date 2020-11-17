package com.money.framework.base.annotation;

import com.money.custom.entity.enums.ChangeLogEntityEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AddChangeLog {

    ChangeLogEntityEnum changeLogEntity();

}
