package com.gqhmt.extServInter.service.account.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.APIExcuteErrorException;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.account.UpdateBankCardDto;
import com.gqhmt.extServInter.service.account.IAddBankCardInfo;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import com.gqhmt.pay.service.account.IFundsAccount;
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
 * Create at:   2016年7月20日
 * Description:	银行卡添加
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年7月20日  jhz      1.0     1.0 Version
 */
@Service
public class AddBankCardInfoImpl implements IAddBankCardInfo{
	
	@Resource
	private BankCardInfoService bankCardInfoService;
	
	@APITradeTypeValid(value = "11028001,11028002,11028003,11028004,11028005,11028006,11028007")//互联网账户银行卡变更
//	@APISignature
    @Override
    public Response execute(SuperDto dto) throws APIExcuteErrorException {
    	Response response = new Response();
    	try {
			UpdateBankCardDto uDto=(UpdateBankCardDto) dto;
			bankCardInfoService.addCardInfo(uDto.getCust_no(),uDto.getBank_card(),uDto.getBank_id(), uDto.getBankAddr(), uDto.getCity_id(), uDto.getFile_path(),uDto.getSeq_no(),uDto.getTrade_type(),uDto.getMchn());
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
