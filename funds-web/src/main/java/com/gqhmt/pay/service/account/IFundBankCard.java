package com.gqhmt.pay.service.account;

import java.util.List;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.extServInter.dto.account.BankCardDto;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
/**
 * Filename:    com.gq.funds.interaction.IFundsTender
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/11/19 下午6:13
 * Description:银行卡信息
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/11/19  柯禹来      1.0     1.0 Version
 */
public interface IFundBankCard {
    
    public List<BankCardInfoEntity> getBankCardInfo(BankCardDto bank) throws FssException;

}