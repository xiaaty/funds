package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.trade.TransferDto;
import com.gqhmt.extServInter.service.trade.ITransefer;

import javax.annotation.Resource;

import com.gqhmt.pay.service.trade.IFundsTrade;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年2月20日
 * Description:  转账
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月20日  柯禹来      1.0     1.0 Version
 */
@Service
public class TranseferImpl implements ITransefer{
	
	@Resource
	private IFundsTrade fundsTrade;

	/**
	 * 11080001：遗留转账
	 * 11080004:个人账户之间转账
	 * @param dto
	 * @return
     */
	@APITradeTypeValid(value = "11080001,11080004")
	@Override
    public Response execute(SuperDto dto) {
    	Response response = new Response();
    	try {
			TransferDto cDto=(TransferDto)dto;
			String contract_no=null;
			String loan_type=null;
			if(!"11080004".equals(cDto.getTrade_type())){
				contract_no=cDto.getContract_no();
				loan_type=cDto.getLoan_type();
			}
			fundsTrade.transfer(cDto.getMchn(),cDto.getSeq_no(),cDto.getTrade_type(),cDto.getFrom_cust_no(),cDto.getFrom_user_no(),cDto.getFrom_cust_type(),cDto.getTo_cust_no(),
								cDto.getTo_user_no(),cDto.getTo_cust_type(),cDto.getAmt(),cDto.getFunds_type(),cDto.getBusi_type(),cDto.getBusi_id(),3,contract_no,loan_type,cDto.getTransfer_flag());
			 response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
