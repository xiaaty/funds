package com.gqhmt.annotations;

import java.lang.annotation.*;

/**
 * Filename:    com.gqhmt.annotations.APIValid
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/19 15:02
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/19  于泳      1.0     1.0 Version
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface APIValid {

    APIValidType type() default APIValidType.DEFAULT;

    String errorCode();

}
