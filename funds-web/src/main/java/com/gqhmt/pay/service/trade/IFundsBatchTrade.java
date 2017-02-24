package com.gqhmt.pay.service.trade;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.entity.TradeProcessEntity;

/**
 * Filename:    com.gqhmt.pay.service.trade.IFundsBatchTrade
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/28 15:56
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/28  于泳      1.0     1.0 Version
 */
public interface IFundsBatchTrade {

    public void batchTrade(FssTradeRecordEntity entity,String contractNo,int custType,String seqNo) throws FssException;

    void batchTrade(TradeProcessEntity entity) throws FssException;
}
