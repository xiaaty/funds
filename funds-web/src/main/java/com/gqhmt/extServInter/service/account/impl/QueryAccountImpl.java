package com.gqhmt.extServInter.service.account.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.account.QueryAccountDto;
import com.gqhmt.extServInter.dto.account.QueryAccountResponse;
import com.gqhmt.extServInter.service.account.IQueryAccount;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.extServInter.service.account.impl.CreateAccountImpl
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/11/19 16:02
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/11/19  jhz      1.0     1.0 Version
 */
@Service
public class QueryAccountImpl implements IQueryAccount{
	@Resource
	private FundAccountService fundAccountService;
	

	/**
	 * 11021100:查询账户开户状态

	 */
	@APITradeTypeValid(value = "11021100")
    @Override
    public Response execute(SuperDto dto) {
		QueryAccountResponse response = new QueryAccountResponse();
    	try {
			QueryAccountDto cDto = (QueryAccountDto)dto;
			FundAccountEntity fundAccountEntity=fundAccountService.getAccountBanlance(Long.valueOf(cDto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
			if(fundAccountEntity==null){
				response.setResp_code("90002003");
				return response;
			}
			if(fundAccountEntity.getHasThirdAccount()==2){
				response.setState("已开户");
			}else{
				response.setState("未开户");
			}
			response.setResp_code("0000");

		} catch (FssException e) {
			LogUtil.debug(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }

}
