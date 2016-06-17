package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.trade.GETWithholdAndDrawDto;
import com.gqhmt.extServInter.service.trade.IWithHoldApply;
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
 * Description:	冠E通对接 --后台 ---代扣申请
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月23日   柯禹来     1.0     1.0 Version
 */
@Service
public class WithHoldApplyImpl implements IWithHoldApply{
	@Resource
	private FssTradeApplyService fssTradeApplyService;
	
	/**
	 * 委托出借充值:11030006
	 * 借款人充值:11030007
	 * 抵押权人充值:11030008
	 * 直接代扣:11030014
	 */
	@APITradeTypeValid(value = "11030006,11030007,11030008,11030014,11093001")
    @Override
    public Response execute(SuperDto dto) {
    	Response response = new Response();
		GETWithholdAndDrawDto getWithholdAndDrawDto = 	(GETWithholdAndDrawDto)dto;
    	try {
    		fssTradeApplyService.whithholdingApply(null,null,getWithholdAndDrawDto.getTrade_type(),getWithholdAndDrawDto.getAmt(),getWithholdAndDrawDto.getMchn(),getWithholdAndDrawDto.getSeq_no(),Long.parseLong(getWithholdAndDrawDto.getCust_no())
						,Integer.parseInt(getWithholdAndDrawDto.getCust_type()),getWithholdAndDrawDto.getContract_no(),getWithholdAndDrawDto.getBusi_no(),false);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
