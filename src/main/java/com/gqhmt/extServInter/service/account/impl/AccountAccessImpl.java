package com.gqhmt.extServInter.service.account.impl;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.account.AssetDto;
import com.gqhmt.extServInter.service.account.ICreateAccount;
import com.gqhmt.pay.service.IFundsAccount;
import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 账户资产接口--账户资产
 * @author 57627
 */
@Service
public class AccountAccessImpl implements ICreateAccount{
	@Resource
	private IFundsAccount fundAccountImpl;
	
	/**
	 * 账户资产查询
	 */
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	Response response = new Response();
    	try {
    		fundAccountImpl.getAccountAsset((AssetDto)dto);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code("90002003");
		}
    	return response;
    }
    
}
