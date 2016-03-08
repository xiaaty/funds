package com.gqhmt.pay.service.loan;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;

/**
 * Filename:    com.gqhmt.pay.service.loan.ILoan
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/3/6 22:38
 * Description:开户
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/3/6  于泳      1.0     1.0 Version
 */
public interface ILoan {

    public String createLoanAccount(CreateLoanAccountDto dto) throws FssException;


}
