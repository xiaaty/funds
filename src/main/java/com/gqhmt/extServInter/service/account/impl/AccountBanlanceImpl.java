package com.gqhmt.extServInter.service.account.impl;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.account.AccountAccessDto;
import com.gqhmt.extServInter.service.account.ICreateAccount;
import com.gqhmt.pay.service.IFundsAccount;
import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 账户余额接口---账户余额
 * @author 柯禹来
 */
@Service
public class AccountBanlanceImpl implements ICreateAccount{
	@Resource
	private IFundsAccount fundsAccountImpl;
	
	/**
	 * 账户余额查询
	 */
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	Response response = new Response();
    	try {
    		fundsAccountImpl.getAccountAccByCustId((AccountAccessDto)dto);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code("90002003");
		}
    	return response;
    }
    
}
