package com.gqhmt.extServInter.service.account.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.APIExcuteErrorException;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.account.BankCardDto;
import com.gqhmt.extServInter.dto.account.BankCardLisyResponse;
import com.gqhmt.extServInter.dto.account.UpdateBankCardDto;
import com.gqhmt.extServInter.service.account.IAddBankCardInfo;
import com.gqhmt.extServInter.service.account.IFindBankCardList;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年7月20日
 * Description:	银行卡列表查询
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年7月20日  jhz      1.0     1.0 Version
 */
@Service
public class FindBankCardListImpl implements IFindBankCardList{
	
	@Resource
	private BankCardInfoService bankCardInfoService;
	
	@APITradeTypeValid(value = "11021001")//查询银行卡列表
//	@APISignature
    @Override
    public BankCardLisyResponse execute(SuperDto dto) throws APIExcuteErrorException {
		BankCardLisyResponse response = new BankCardLisyResponse();
    	try {
			BankCardDto bDto=(BankCardDto) dto;
			List<BankCardInfoEntity> list=bankCardInfoService.getBankCardByCustNo(bDto.getCust_no());
			response.setCard_list(list);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
