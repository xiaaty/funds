package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.trade.BondTransferDto;
import com.gqhmt.extServInter.service.trade.IBondTransfer;
import com.gqhmt.pay.service.trade.IFundsTrade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年6月29日
 * Description:  债权转让
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年6月29日  keyulai      1.0     1.0 Version
 */
@Service
public class BondTransferImpl implements IBondTransfer {
	@Resource
	private IFundsTrade fundsTradeImpl;

    /**
     * 11052001:web购买债权
     * 11052002:wap购买债权
     * 11052003:IOS购买债权
	 * 11052004:安卓购买债权
	 * 11052005:微信购买债权
     * 11052006:委托购买债权
     * 11080005:还款本息转账
     * @param dto
     * @return
     */
	@APITradeTypeValid(value = "11052001,11052002,11052003,11052004,11052005,11052006,11080005")
    @Override
    public Response execute(SuperDto dto) {
    	Response response = new Response();
    	try {
			int fundType=3007;
			BondTransferDto cDto = (BondTransferDto)dto;
			if(StringUtils.equals("11080005",cDto.getTrade_type())){
				fundType=3014;
			}
			fundsTradeImpl.bondTransfer(cDto.getMchn(),cDto.getSeq_no(),cDto.getTrade_type(), cDto.getBid_id(),cDto.getBusi_bid_no(),
                    cDto.getTender_no(),cDto.getCust_no(),cDto.getBusi_no(),cDto.getAmt(),
                    cDto.getO_tender_no(),cDto.getO_cust_no(),cDto.getO_busi_no(),cDto.getAcc_type(),cDto.getTo_acc_type(),fundType,8);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
