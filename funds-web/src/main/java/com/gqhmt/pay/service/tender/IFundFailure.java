package com.gqhmt.pay.service.tender;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.extServInter.dto.tender.FailureBidDto;
/**
 * Filename:    com.gq.funds.interaction.IFundsTender
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/11/19 下午6:13
 * Description:
 * <p>
 *     <ol>
 *         <li>流标</li>
 *     </ol>
 * </p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/11/19  柯禹来      1.0     1.0 Version
 */
public interface IFundFailure {
    
    public void abort(FailureBidDto failurebid) throws FssException;

}