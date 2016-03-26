package com.gqhmt.extServInter.service.loan.impl;

import com.gqhmt.annotations.APISignature;
import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.loan.CardChangeDto;
import com.gqhmt.extServInter.dto.loan.ChangeCardResponse;
import com.gqhmt.extServInter.service.loan.IChangeCard;
import com.gqhmt.pay.service.account.IFundsAccount;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 出借系统--银行卡变更申请
 * @author 柯禹来
 */
@Service
public class ChangeCardImpl implements IChangeCard{
	@Resource
	private IFundsAccount fundsAccountImpl;
	
    @Override
	@APITradeTypeValid(value = "11029001,11093002")
	@APISignature
    public Response execute(SuperDto dto) throws APIExcuteErrorException {
    	ChangeCardResponse response = new ChangeCardResponse();
    	try {
    		fundsAccountImpl.bankCardChange((CardChangeDto)dto);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
