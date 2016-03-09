package com.gqhmt.pay.service.loan.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.loan.MarginDto;
import com.gqhmt.pay.service.loan.IMarginReturn;
import org.springframework.stereotype.Service;
/**
 * Filename:    com.gqhmt.pay.service.loan.impl.LoanImpl
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/3/6 22:52
 * Description:保证金退还
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/3/6  于泳      1.0     1.0 Version
 */
@Service
public class MarginReturnImpl implements IMarginReturn {
	
	@Override
	public boolean marginSendBack(MarginDto dto) throws FssException {

		
		return true;
	}
  
}
