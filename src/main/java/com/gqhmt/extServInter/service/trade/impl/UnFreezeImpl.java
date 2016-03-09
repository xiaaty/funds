package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.trade.FreezeDto;
import com.gqhmt.extServInter.dto.trade.UnFreezeDto;
import com.gqhmt.extServInter.service.trade.IUnFreeze;
import com.gqhmt.pay.service.IFundsTrade;
import com.gqhmt.core.FssException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
 * Description:  资金解冻
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月20日  jhz      1.0     1.0 Version
 */
@Service
public class UnFreezeImpl implements IUnFreeze {
	@Resource
    private IFundsTrade fundsTrade;
    @Override
    public Response excute(SuperDto dto)  {

        Response response = new Response();
        try {
            fundsTrade.unFroze((UnFreezeDto) dto);
            response.setResp_code("0000000");
        } catch (FssException e) {
            LogUtil.error(this.getClass(),e);
            response.setResp_code(e.getMessage());

        }
        return response;
    }
}
