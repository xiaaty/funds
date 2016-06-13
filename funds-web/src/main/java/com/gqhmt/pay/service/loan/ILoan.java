package com.gqhmt.pay.service.loan;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.extServInter.dto.loan.MarginDto;

/**
 * Filename:    com.gqhmt.pay.service.loan.ILoan
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/3/6 22:38
 * Description: 开户
 * <p>开户
 * <p>流标接口
 * <p>放款给借款人
 * <p>抵押权人提现接口
 * <p>入账
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/3/6  柯禹来   1.0     1.0 Version
 */
public interface ILoan {
	/**
	 * 
	 * author:kyl
	 * time:2016年3月9日
	 * function：开户
	 */
	public String createLoanAccount(CreateLoanAccountDto dto) throws FssException;
	
	/**
	 * 保证金退还
	 * @param dto
	 * @return
	 * @throws FssException
	 */
	public boolean marginSendBack(MarginDto dto) throws FssException;
}
