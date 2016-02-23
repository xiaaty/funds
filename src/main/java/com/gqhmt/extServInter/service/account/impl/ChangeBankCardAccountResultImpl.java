package com.gqhmt.extServInter.service.account.impl;

import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.account.ChangeBankCardResultDto;
import com.gqhmt.extServInter.service.account.IChangeBankCardAccountResult;
import com.gqhmt.pay.service.IFundsAccount;
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
 * Create at:   2016年2月20日
 * Description:	银行卡变更结果查询
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月20日  jhz      1.0     1.0 Version
 */
@Service
public class ChangeBankCardAccountResultImpl implements IChangeBankCardAccountResult{
	
	@Resource
	private IFundsAccount fundsAccountImpl;
	
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	Response response = new Response();
    	try {
			fundsAccountImpl.changeCardResult((ChangeBankCardResultDto) dto);
			response.setResp_code("00000000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
