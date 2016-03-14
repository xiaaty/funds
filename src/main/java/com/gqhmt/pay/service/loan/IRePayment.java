package com.gqhmt.pay.service.loan;

import java.util.List;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.loan.RepaymentDto;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentEntity;
/**
 * Filename:    com.gqhmt.pay.service.loan.ILoan
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/3/6 22:38
 * Description:还款划扣
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/3/6  柯禹来      1.0     1.0 Version
 */
public interface IRePayment {

    public boolean createRefundDraw(RepaymentDto repaymentdto) throws FssException;

    public List<FssRepaymentEntity> rePaymentCallBack(String seqNo,String mchn) throws FssException;

}
