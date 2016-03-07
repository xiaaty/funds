package com.gqhmt.pay.service.loan.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.loan.ILoan;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.pay.service.loan.impl.LoanImpl
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/3/6 22:52
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/3/6  于泳      1.0     1.0 Version
 */
public class LoanImpl implements ILoan {

    @Resource
    private PaySuperByFuiou paySuperByFuiou;

    @Override
    public void createAccount(CreateLoanAccountDto dto) throws FssException {
        //富友
        //创建customer  usr  bank


//        paySuperByFuiou.createAccountByPersonal(primaryAccount,"","");


        //fss_account


    }
}
