package com.gqhmt.fss.pay.exception;


/**
 * Filename:    com.gq.core.exception
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/5/15 14:49
 * Description: 第三方系统错账异常，本异常抓取后，需手动调整操作结果成功or失败
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/5/15  于泳      1.0     1.0 Version
 */
public class ThirdpartyErrorAsyncException extends RuntimeException{

	public ThirdpartyErrorAsyncException(String message) {
		super(message);
	}

}
