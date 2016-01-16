package com.gqhmt.annotations;

import java.lang.annotation.*;

/**
 * Filename:    com.gqhmt.annotations.AutoMapping
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/16 19:30
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/16  于泳      1.0     1.0 Version
 */
@Documented
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoMapping {
    String value() default "";
    boolean isParent() default false;
}
