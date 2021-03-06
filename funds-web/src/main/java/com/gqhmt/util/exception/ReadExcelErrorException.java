package com.gqhmt.util.exception;

/**
 * Filename:    com.gqhmt.util.exception
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2014/12/10 19:55
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2014/12/10  于泳      1.0     1.0 Version
 */
public class ReadExcelErrorException extends Exception {
    public ReadExcelErrorException(String message) {
        super(message);
    }
}
