package com.money.framework.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 配合mybatis指定增删改查的默认sqlID
 * @version V1.0
 * @author song
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLContext {

	String value() default "";

	/**
	 * mybatis命名空间
	 */
	String nameSpace();

	/**
	 * 默认的insert mybatis Id
	 */
	String insert() default "";

	/**
	 * 默认的update mybatis Id
	 */
	String update() default "";

	/**
	 * 默认的delete mybatis Id
	 */
	String delete() default "";

	/**
	 * 默认的select mybatis Id
	 */
	String select() default "";
}
