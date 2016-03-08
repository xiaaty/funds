package com.gqhmt.pay.service.loan;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.loan.LoanWithDrawApplyDto;

/**
 * Filename:    com.gqhmt.pay.service.loan.ILoan
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/3/6 22:38
 * Description:借款人提现
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/3/6  于泳      1.0     1.0 Version
 */
public interface IWithDrawApply {

    public void createWithDrawApply(LoanWithDrawApplyDto dto) throws FssException;

    public Response withDrasApplyCallBack(String seqNo,String mchn) throws FssException;

}
