package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.trade.UnFreezeDto;
import com.gqhmt.extServInter.service.trade.IUnFreeze;
import com.gqhmt.pay.service.trade.IFundsTrade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年7月7日
 * Description:  资金解冻
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 *  2016年7月7日 柯禹来      1.0     1.0 Version
 */
@Service
public class UnFreezeImpl implements IUnFreeze {
	@Resource
    private IFundsTrade fundsTrade;

    @APITradeTypeValid(value ="11080003")
    @Override
    public Response execute(SuperDto dto)  {

        Response response = new Response();
        try {
            UnFreezeDto cDto = (UnFreezeDto)dto;
            fundsTrade.unFroze(cDto.getMchn(),cDto.getSeq_no(),cDto.getTrade_type(),cDto.getCust_no(),cDto.getUser_no(),cDto.getAmt(),cDto.getBusi_type(),cDto.getSeq_no());
            response.setResp_code("0000");
        } catch (FssException e) {
            LogUtil.error(this.getClass(),e);
            response.setResp_code(e.getMessage());

        }
        return response;
    }
}
