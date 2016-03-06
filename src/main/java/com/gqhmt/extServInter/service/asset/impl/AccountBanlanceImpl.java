package com.gqhmt.extServInter.service.asset.impl;

import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.asset.AssetDto;
import com.gqhmt.extServInter.dto.asset.BalanceResponse;
import com.gqhmt.extServInter.service.asset.IAccountBanlance;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.pay.service.account.IFundsAccount;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 账户余额接口---账户余额
 * @author 柯禹来
 */
@Service
public class AccountBanlanceImpl implements IAccountBanlance{
	@Resource
	private IFundsAccount fundsAccountImpl;
	
	/**
	 * 账户余额查询
	 */
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
		BalanceResponse response = new BalanceResponse();
    	try {
    		FundAccountEntity fundAccountEntity = fundsAccountImpl.getAccountBanlance((AssetDto) dto);
			response.setAmount(fundAccountEntity.getAmount());
			response.setFrozeAmount(fundAccountEntity.getFreezeAmount());
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
			response.setResp_code("90002003");
		}
    	return response;
    }
    
}
