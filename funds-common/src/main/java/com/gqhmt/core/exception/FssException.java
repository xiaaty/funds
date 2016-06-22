package com.gqhmt.core.exception;/**
 * Created by yuyonf on 15/11/28.
 */

/**
 * Filename:    com.gqhmt.core.exception.FssException
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/11/28 15:10
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/11/28  于泳      1.0     1.0 Version
 */
public class FssException extends Exception {
    public FssException() {
        super();
    }

    public FssException(String message) {
        super(message);
    }

    public FssException(String message, Throwable cause) {
        super(message, cause);
    }

    public FssException(Throwable cause) {
        super(cause);
    }

    protected FssException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
