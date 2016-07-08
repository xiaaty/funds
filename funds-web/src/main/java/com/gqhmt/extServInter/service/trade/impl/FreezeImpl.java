package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.trade.FreezeDto;
import com.gqhmt.extServInter.service.trade.IFreeze;
import com.gqhmt.core.exception.FssException;

import com.gqhmt.pay.service.trade.IFundsTrade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年7月7日
 * Description:  资金冻结
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年7月7日  柯禹来      1.0     1.0 Version
 */
@Service
public class FreezeImpl implements IFreeze {

    @Resource
    private IFundsTrade fundsTrade;
    @APITradeTypeValid(value ="11080002")
    @Override
    public Response execute(SuperDto dto) {
    	Response response = new Response();
        try {
            FreezeDto cdto=(FreezeDto)dto;
            fundsTrade.froze(Long.valueOf(cdto.getCust_no()),cdto.getBusi_type(),cdto.getAmt());
            response.setResp_code("0000");
        } catch (FssException e) {
            LogUtil.error(this.getClass(),e);
            response.setResp_code(e.getMessage());

        }
        return response;
    }
}
