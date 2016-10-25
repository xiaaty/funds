package com.gqhmt.extServInter.callback.bonus;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.callback.loan.GetCallBack;
import com.gqhmt.extServInter.dto.bonus.BonusResponse;
import com.gqhmt.fss.architect.bonus.service.FssBonusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.fss.architect.trade.service.FssTradeApplyService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/9/24
 * Description:还款划扣
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/9/24  jhz     1.0     1.0 Version
 */
@Service
public class BonusCallBack implements GetCallBack {
    @Resource
    private FssBonusService fssBonusService;

    public BonusResponse getCallBack(String mchn, String seqNo) throws FssException {
        BonusResponse response = new BonusResponse();
        try {
            response = fssBonusService.getBonusResponse(mchn,seqNo);
        } catch (FssException e) {
            LogUtil.info(this.getClass(), e.getMessage());
            response.setResp_code(e.getMessage());
        }
        return response;
    }
}
