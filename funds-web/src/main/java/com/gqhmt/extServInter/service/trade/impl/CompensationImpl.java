package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.trade.CompensationDto;
import com.gqhmt.extServInter.service.trade.ICompensation;
import com.gqhmt.pay.service.cost.ICost;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年6月28日
 * Description:  代偿接口
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年6月28日  keyulai      1.0     1.0 Version
 */
@Service
public class CompensationImpl implements ICompensation {
	@Resource
	private ICost costImpl;

	/**
	 * -------------与代偿相关交易类型-----------------
	 * 11070001:借款人逾期代偿
	 * 11070002:借款人逾期代偿资金退回
	 * 11070003:委托出借人代偿
	 * 11070004:委托出借代偿退回
	 * ------------红包返现劵返现---------------
	 * 11130001:web返现红包入账
	 * 11130002:wap返现红包入账
	 * 11130003:安卓返现红包入账
	 * 11130004:ios返现红包入账
	 * 11130005:微信返现红包入账
	 * -----------费用相关交易类型---------------------
	 *11060001:提现手续费
	 *11060002:账户管理费
	 *11060003:服务费
	 *11060004:咨询费
	 *11060005:收风险保证金
	 *11060006:退风险保证金
	 *11060007:逆服务费（补差额）
	 *11060008:逆服务费（垫付利息）
	 * 21992105：收风险备用金
	 * @param dto
	 * @return
     */
	@APITradeTypeValid(value = "11070001,11070002,11070003,11070004,11130001,11130002,11130003,11130004,11130005,11060001,11060002,11060003,11060004,11060005,11060006,11060007,11060008,21992105")
    @Override
    public Response execute(SuperDto dto) {
    	Response response = new Response();
    	try {
			CompensationDto cDto = (CompensationDto)dto;
			costImpl.compensation(cDto.getTrade_type(),cDto.getCust_no(),cDto.getBusi_type(),cDto.getAmt(),cDto.getBusi_no(),cDto.getPlatform(),cDto.getAccounts_type(),cDto.getSeq_no(),cDto.getMemo());
			 response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
