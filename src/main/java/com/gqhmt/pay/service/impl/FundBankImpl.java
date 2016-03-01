package com.gqhmt.pay.service.impl;

import org.springframework.stereotype.Service;
import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.fund.BankDto;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.BankEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import com.gqhmt.pay.service.IFundBank;
import java.util.List;

import javax.annotation.Resource;

/**
 * 银行卡列表
 * @author 
 *
 */
@Service
public class FundBankImpl implements IFundBank {

	@Resource
	private BankCardInfoService bankCardInfoService;
	
	@Override
	public List<BankEntity> getBankInfo(BankDto bank) throws FssException {
		List<BankEntity> list=bankCardInfoService.getBankList(null);
		if(list==null && list.size()==0){
			throw new FssException("没有得到银行信息");
		}
		return list;
	}

	
	
}
