package com.gqhmt.extServInter.service.p2p.impl;

import com.gqhmt.annotations.APISignature;
import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.p2p.FullBidApplyDto;
import com.gqhmt.extServInter.service.p2p.IFullBidApply;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月23日
 * Description:
 * <p>	冠e通对接 满标
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月23日  jhz      1.0     1.0 Version
 */
@Service
public class FullBidImpl  implements IFullBidApply {
    @Resource
    private FssLoanService loanService;

    //11090005，冠e通抵押标放款；11090004，冠e通信用标放款;11090006 冠e通抵押标满标
    @APITradeTypeValid(value = "11090004,11090005,11090006")
    @APISignature
    public Response execute(SuperDto dto) {
        Response response = new Response();
        try {
                loanService.insertFullBidApply((FullBidApplyDto)dto);
            response.setResp_code("0000");
        } catch (FssException e) {
            LogUtil.error(this.getClass(), e);
            response.setResp_code(e.getMessage());
        }
        return response;
    }

}
