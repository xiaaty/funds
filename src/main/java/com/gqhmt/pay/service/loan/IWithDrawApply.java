package com.gqhmt.pay.service.loan;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.loan.LoanWithDrawApplyDto;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;

/**
 * Filename:    com.gqhmt.pay.service.loan.ILoan
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/3/6 22:38
 * Description:借款人提现
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/3/6  柯禹来      1.0     1.0 Version
 */
public interface IWithDrawApply {

    public void createWithDrawApply(LoanWithDrawApplyDto dto) throws FssException;

    public FssTradeApplyEntity withDrasApplyCallBack(String seqNo,String mchn) throws FssException;

}
