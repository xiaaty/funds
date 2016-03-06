package com.gqhmt.pay.service.trade.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.extServInter.dto.trade.RechargeSuccessDto;
import com.gqhmt.extServInter.dto.trade.WithdrawSuccessDto;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.pay.service.trade.ISuccessAccount;
import com.gqhmt.pay.service.TradeRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
@Service
public class SuccessAccountImpl implements ISuccessAccount{
	@Resource
	private TradeRecordService tradeRecordService;
	
    @Resource
    private FundAccountService fundAccountService;
    
    @Resource
    private FundOrderService fundOrderService;
	/**
     * 
     * author:jhz
     * time:2016年2月27日
     * function：充值成功入账
     */
    public void recharge(RechargeSuccessDto rechargeSuccessDto) throws FssException {
    	FundAccountEntity entity=fundAccountService.getFundAccount(Integer.parseInt(rechargeSuccessDto.getCust_no()),  GlobalConstants.ACCOUNT_TYPE_LEND_ON);
		FundOrderEntity fundOrderEntity=fundOrderService.findfundOrder(rechargeSuccessDto.getOrder_no());
		tradeRecordService.recharge(entity, fundOrderEntity.getOrderAmount(), fundOrderEntity, 1001);
	}
    /**
     * 
     * author:jhz
     * time:2016年2月27日
     * function：提现成功入账
     */
    public void withdraw(WithdrawSuccessDto withdrawSuccessDto) throws FssException {
    	FundAccountEntity entity=fundAccountService.getFundAccount(Integer.parseInt(withdrawSuccessDto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
		FundOrderEntity fundOrderEntity=fundOrderService.findfundOrder(withdrawSuccessDto.getOrder_no());
		tradeRecordService.withdraw(entity, fundOrderEntity.getOrderAmount(), fundOrderEntity, 1003);

		//收取账户管理费

	}

}