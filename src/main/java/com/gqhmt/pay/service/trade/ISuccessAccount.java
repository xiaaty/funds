package com.gqhmt.pay.service.trade;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.trade.RechargeSuccessDto;
import com.gqhmt.extServInter.dto.trade.WithdrawSuccessDto;
/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年2月27日
 * Description:	成功入账
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月27日  jhz      1.0     1.0 Version
 */
public interface ISuccessAccount {
    /**
     * 
     * author:jhz
     * time:2016年2月27日
     * function：充值成功入账
     */
    public void recharge(RechargeSuccessDto rechargeSuccessDto) throws FssException;
    /**
     * 
     * author:jhz
     * time:2016年2月27日
     * function：提现成功入账
     */
    public void withdraw(WithdrawSuccessDto withdrawSuccessDto) throws FssException;

}