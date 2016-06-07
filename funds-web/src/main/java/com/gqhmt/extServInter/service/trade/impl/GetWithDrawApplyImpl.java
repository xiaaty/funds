package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.trade.GETWithholdAndDrawDto;
import com.gqhmt.extServInter.service.trade.IGetWithDrawApply;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月23日
 * Description:	冠E通对接 --后台 ---代付申请
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月23日   柯禹来     1.0     1.0 Version
 */
@Service
public class GetWithDrawApplyImpl implements IGetWithDrawApply{
	@Resource
	private FssTradeApplyService fssTradeApplyService;
	/**
	 * 借款人提现申请：11091001
	 * 冠E通借款人放款：11091002
	 * 抵押权人提现：11040007
	 * 委托出借提现：11040006
	 * 账户直接提现：11040012
	 */
	@APITradeTypeValid(value = "11091001,11091002,11040007,11040006,11040012")
    @Override
    public Response execute(SuperDto dto){
    	Response response = new Response();
    	try {
    		fssTradeApplyService.careateTradeApply((GETWithholdAndDrawDto)dto);
			 response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
