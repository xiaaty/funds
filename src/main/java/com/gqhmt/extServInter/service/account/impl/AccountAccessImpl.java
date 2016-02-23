package com.gqhmt.extServInter.service.account.impl;

import com.gqhmt.annotations.API;
import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.account.AssetDto;
import com.gqhmt.extServInter.service.account.IAccountAccess;
import com.gqhmt.pay.service.IFundsAccount;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 账户资产接口--账户资产
 * @author 57627
 */
@Service
public class AccountAccessImpl implements IAccountAccess{
	@Resource
	private IFundsAccount fundsAccountImpl;
	
	/**
	 * 账户资产查询
	 */
    @Override
	@API(value = "")
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	Response response = new Response();
    	try {
    		fundsAccountImpl.getAccountAsset((AssetDto)dto);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code("90002003");
		}
    	return response;
    }
    
}
