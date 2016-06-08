package com.gqhmt.annotations;

/**
 * Filename:    com.gqhmt.annotations.AutoDateType
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/16 14:45
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/16  于泳      1.0     1.0 Version
 */
public enum AutoDateType {
    /**
     * jvava.util.Date()
     */
    DATE_TIME,
    /**
     * 生成8位数字表示的日期格式  20150101
     */
    DATE_CHAR_8,
    /**
     * 生成10位数字表示的日期格式  2015-01-01
     */
    DATE_CHAR_10,
    /**
     * 生成6位数字表示的时间格式 160505
     */
    TIME_CHAR_6,
    /**
     * 生成6位数字表示的时间格式 16:05:05
     */
    TIME_CHAR_8,
    /**
     * 生成14位表示的时间格式,20150101160505
     */
    DATE_TIME_14,
    /**
     * java.sql.Date() 仅仅日期格式
     */
    DATE,
    /**
     * java.sql.Time() 仅仅时间格式
     */
    TIME
}
