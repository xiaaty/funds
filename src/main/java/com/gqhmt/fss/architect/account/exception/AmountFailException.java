package com.gqhmt.fss.architect.account.exception;


/**
 * Filename:    com.gq.p2p.account.command
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/27 22:11
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/27  于泳      1.0     1.0 Version
 */
public class AmountFailException extends RuntimeException {

	public AmountFailException(String message) {
        super(message);
    }
}
