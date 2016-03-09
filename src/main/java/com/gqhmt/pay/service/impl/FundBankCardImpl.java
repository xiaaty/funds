package com.gqhmt.pay.service.impl;

import org.springframework.stereotype.Service;
import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.account.BankCardDto;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import com.gqhmt.pay.service.IFundBankCard;
import java.util.List;
import javax.annotation.Resource;

/**
 * 银行卡列表
 * @author 
 *
 */
@Service
public class FundBankCardImpl implements IFundBankCard {

	@Resource
	private BankCardInfoService bankCardInfoService;
	/**
	 * 查询银行卡信息
	 */
	@Override
	public List<BankCardInfoEntity> getBankCardInfo(BankCardDto bankcard) throws FssException {
		List<BankCardInfoEntity> list=bankCardInfoService.findAllbankCards(null);
		if(list==null && list.size()==0){
			throw new FssException("没有查到到银卡行信息");
		}
		return list;
	}

	
	
}
