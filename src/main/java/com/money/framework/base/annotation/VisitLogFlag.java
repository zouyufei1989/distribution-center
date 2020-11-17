package com.money.framework.base.annotation;

import com.money.framework.base.entity.VisitLogTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VisitLogFlag {

    VisitLogTypeEnum type() default VisitLogTypeEnum.NONE;

    String module() default "";

    String resource() default "";
}
