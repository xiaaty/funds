package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.annotations.APISignature;
import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.account.QueryAccountDto;
import com.gqhmt.extServInter.dto.trade.QueryWithDrawCountResponse;
import com.gqhmt.extServInter.dto.trade.WithholdDto;
import com.gqhmt.extServInter.service.trade.IQueryWithDrawCount;
import com.gqhmt.extServInter.service.trade.IRecharge;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.pay.service.trade.IFundsTrade;
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
 * Create at:   2016年11月20日
 * Description:  查询提现次数接口
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年11月20日  jhz      1.0     1.0 Version
 */
@Service
public class QueryWithDrawCountImpl implements IQueryWithDrawCount {
	@Resource
	private FundSequenceService fundSequenceService;

	@Resource
	private FundAccountService fundAccountService;

	/**
	 * jhz
	 * 查询线上账户提现次数
	 * 11021101:查询线上账户提现次数
	 * @param dto
	 * @return
     */
	@APITradeTypeValid(value = "11021101")
    @Override
    public Response execute(SuperDto dto) {
		QueryWithDrawCountResponse response=new QueryWithDrawCountResponse();
    	try {
			QueryAccountDto qDto=(QueryAccountDto)dto;
			FundAccountEntity fundAccountEntity=fundAccountService.getFundsAccount(Long.valueOf(qDto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
    		if (fundAccountEntity==null){
				response.setResp_code("90002003");
				return response;
			}
			int count =fundSequenceService.queryWithDrawCount(fundAccountEntity.getId());
			response.setCount(count);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
