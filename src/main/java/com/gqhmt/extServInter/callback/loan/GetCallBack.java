package com.gqhmt.extServInter.callback.loan;

import com.gqhmt.core.FssException;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年4月6日
 * Description:
 * <p> 标记service,无任何定义方法
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年4月6日  jhz      1.0     1.0 Version
 */
public interface GetCallBack {

    public Object getCallBack(String mchn,String seqNo) throws FssException;

	
}
