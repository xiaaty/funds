package com.gqhmt.pay.service.account.impl;

import org.springframework.stereotype.Service;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.funds.architect.customer.entity.BankEntity;
import com.gqhmt.funds.architect.customer.service.BankService;
import com.gqhmt.pay.service.account.IFundBank;
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
	private BankService bankService;
	
	@Override
	public List<BankEntity> getBankInfo() throws FssException {
		List<BankEntity> list=bankService.findAll();
		if(list==null && list.size()==0){
			throw new FssException("没有得到银行信息");
		}
		return list;
	}
	
	
}
