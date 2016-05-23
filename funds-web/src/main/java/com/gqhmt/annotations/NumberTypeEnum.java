package com.gqhmt.annotations;

import java.text.DecimalFormat;

/**
 * Filename:    com.gqhmt.annotations
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2014/12/9 17:48
 * Description:
 * <p>数字类型，用于excel导入时数字转换</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2014/12/9  于泳      1.0     1.0 Version
 */
public enum NumberTypeEnum {
    MONEY,
    DOUBLE,
    LONG,
    INT,
    FLOAT,
    SHORT,
    DECIMAL
}
