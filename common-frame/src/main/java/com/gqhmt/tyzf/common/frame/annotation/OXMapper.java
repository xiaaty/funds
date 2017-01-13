package com.gqhmt.tyzf.common.frame.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * dom报文转实体用注解，用于set方法上（支持父类set）
 * Created by luzhkun on 2016/11/7.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OXMapper {

    /** 报文路径*/
    String xpath() default "";

    /**方法参数类型，使用封装类型的参数, Date类型默认为java.util.Date*/
    String paraType() default "String";
}
