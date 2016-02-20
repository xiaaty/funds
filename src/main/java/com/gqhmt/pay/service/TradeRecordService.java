package com.gqhmt.pay.service;

import com.gqhmt.core.FssException;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.trade.service.FundTradeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Filename:    com.gqhmt.pay.service.TradeRecordService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/17 09:28
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/17  于泳      1.0     1.0 Version
 */

@Service
public class TradeRecordService {

    private final String  thirdPartyType = "2";

    @Resource
    private FundSequenceService sequenceService;

    @Resource
    private FundTradeService fundTradeService;

    public void recharge(final FundAccountEntity entity,final BigDecimal amount,final FundOrderEntity fundOrderEntity,final int  fundType) throws FssException {
        sequenceService.charge(entity, fundType, amount, thirdPartyType, fundOrderEntity);
        // this.fundTradeService.createFundTrade(entity, amount, BigDecimal.ZERO, fundType, "充值成功，充值金额 " + amount + "元");
        //super.sendNotice(NoticeService.NoticeType.FUND_CHARGE, entity, amount,BigDecimal.ZERO);
    }

}
