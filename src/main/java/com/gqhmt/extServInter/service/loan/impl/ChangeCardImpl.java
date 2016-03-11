package com.gqhmt.extServInter.service.loan.impl;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.loan.CardChangeDto;
import com.gqhmt.extServInter.dto.loan.ChangeCardResponse;
import com.gqhmt.extServInter.service.loan.IChangeCard;
import com.gqhmt.pay.service.account.IFundsAccount;
import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 出借系统--银行卡变更申请
 * @author 柯禹来
 */
@Service
public class ChangeCardImpl implements IChangeCard{
	@Resource
	private IFundsAccount fundsAccountImpl;
	
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
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
