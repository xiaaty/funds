package com.gqhmt.tyzf.common.frame.annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * 对于Bean属性为日期的定义其格式
 * @author gudalei
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME) 
public @interface DFormat {
	String format(); 
}
