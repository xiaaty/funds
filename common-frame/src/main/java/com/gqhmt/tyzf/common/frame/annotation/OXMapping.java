package com.gqhmt.tyzf.common.frame.annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * dom报文转实体用注解
 * @author gudalei
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME) 
public @interface OXMapping {
	String xpath(); 
}
