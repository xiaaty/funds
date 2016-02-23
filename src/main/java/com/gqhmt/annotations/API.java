package com.gqhmt.annotations;

import java.lang.annotation.*;

/**
 * Filename:    com.gqhmt.annotations.API
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/6 16:40
 * Description:
 * <p>api注解,用于api校验,验签等公共操作</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/6  于泳      1.0     1.0 Version
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface API {
    String value();
}
