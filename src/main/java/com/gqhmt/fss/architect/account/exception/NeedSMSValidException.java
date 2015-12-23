package com.gqhmt.fss.architect.account.exception;


/**
 * Filename:    com.gq.p2p.interactions.account
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/3/24 9:55
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/3/24  于泳      1.0     1.0 Version
 */
public class NeedSMSValidException  extends RuntimeException {
    public NeedSMSValidException(String message) {
        super(message);
    }

    public NeedSMSValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
