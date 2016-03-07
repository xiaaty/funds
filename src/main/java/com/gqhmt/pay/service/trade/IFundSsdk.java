package com.gqhmt.pay.service.trade;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.trade.SsdkDto;

/**
 * Filename:    com.gq.funds.interaction.IFundsAsset
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/11/21 下午2:09
 * Description:
 * <p>
 *    实时代扣
 * </p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/11/21  于泳      1.0     1.0 Version
 */
public interface IFundSsdk {

	public boolean ssdkBusiness (SsdkDto ssdk) throws FssException;
    

}
