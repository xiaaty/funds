package com.gqhmt.extServInter.service.account.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.APIExcuteErrorException;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.account.BankCardResponse;
import com.gqhmt.extServInter.dto.account.FindBankCardDto;
import com.gqhmt.extServInter.dto.account.soleBankCardResponse;
import com.gqhmt.extServInter.service.account.IFindBankCardAnotherById;
import com.gqhmt.fss.architect.customer.service.FssChangeCardService;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年07月26日
 * Description:	银行卡变更
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年07月26日  xdw      1.0     1.0 Version
 */
@Service
public class FindBankCardAnotherByIdImpl implements IFindBankCardAnotherById {
	
	@Resource
	private FssChangeCardService fssChangeCardService;

	@APITradeTypeValid(value = "11029003")//互联网账户银行卡变更
    @Override
    public soleBankCardResponse execute(SuperDto dto) throws APIExcuteErrorException {
		soleBankCardResponse response = new soleBankCardResponse();
			if(dto instanceof FindBankCardDto){
				FindBankCardDto findBankCardDto=(FindBankCardDto) dto;
				BankCardInfoEntity bankCardInfoEntity = fssChangeCardService.queryBankCardinfoById(new Integer(findBankCardDto.getBank_id()));
				response.setBankcard(bankCardInfoEntity);
			}
        return response;
    }
}
