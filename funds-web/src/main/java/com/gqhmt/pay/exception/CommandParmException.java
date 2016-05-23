package com.gqhmt.pay.exception;/**
 * Created by yuyonf on 15/11/28.
 */

/**
 * Filename:    com.gqhmt.pay.exception.CommandParmException
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/11/28 15:11
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/11/28  于泳      1.0     1.0 Version
 */
public class CommandParmException extends RuntimeException {
    public CommandParmException(String message) {
        super(message);
    }
    public CommandParmException(String message, Throwable cause) {
        super(message, cause);
    }
}
