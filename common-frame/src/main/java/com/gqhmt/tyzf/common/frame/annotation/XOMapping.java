package com.gqhmt.tyzf.common.frame.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 实体转dom报文用注解
 * Created by luzhkun on 2016/11/9.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface XOMapping {

    /**
     * 上级节点
     * @return
     */
    String preNode() default "";

    /**
     * 路径
     * @return
     */
    String xpath() default "";

}
