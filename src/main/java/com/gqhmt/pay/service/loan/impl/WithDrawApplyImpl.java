package com.gqhmt.pay.service.loan.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.loan.LoanWithDrawApplyDto;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.pay.service.loan.IWithDrawApply;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Filename:    com.gqhmt.pay.service.loan.impl.LoanImpl
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/3/6 22:52
 * Description:借款人提现
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/3/6  柯禹来      1.0     1.0 Version
 */
@Service
public class WithDrawApplyImpl implements IWithDrawApply {

	@Resource
	private FundAccountService fundAccountService;
	@Resource
	private FssTradeApplyService fssTradeApplyService;
	
	/**
	 * 借款人提现
	 */
	@Override
	public void createWithDrawApply(LoanWithDrawApplyDto wthDrawApplyDto) throws FssException {
		FssTradeApplyEntity fssTradeApplyEntity=null;
		//1.根据acc_no查询借款人账户信息
	 	FssAccountEntity fssAccountEntity= fundAccountService.getFssFundAccountInfo(wthDrawApplyDto.getAcc_no());
	 	if(fssAccountEntity==null){
	 		throw new FssException("90004006");
	 	}else{//账户余额小于提现金额
	 		if(wthDrawApplyDto.getPay_amt().compareTo(fssAccountEntity.getAccBalance())<0){
	 			throw new FssException("90004007");
	 		}else{
	 			//创建提现申请信息
	 			try {
	 			    fssTradeApplyEntity = fssTradeApplyService.createTreadeApplyEntity(fssAccountEntity,wthDrawApplyDto);
					fssTradeApplyService.createTradeApply(fssTradeApplyEntity);
				} catch (FssException e) {
					throw new FssException("90099005");
				}
	 		}
	 	}
	}
	
	/**
	 * 完成抵押标借款人提现后，通知借款系统
	 */
	@Override
	public FssTradeApplyEntity withDrasApplyCallBack(String seqNo, String mchn) throws FssException {
		FssTradeApplyEntity fssTradeApplyEntity=null;
		fssTradeApplyEntity=fssTradeApplyService.getTradeApplyByParam(seqNo,mchn);
		if(fssTradeApplyEntity==null){
			throw new FssException("90004002");
		}
		return fssTradeApplyEntity;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
