package com.gqhmt.pay.exception;/**
 * Created by yuyonf on 15/11/28.
 */

import com.gqhmt.core.FssException;

/**
 * Filename:    com.gqhmt.pay.exception.PayChannelNotSupports
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/11/28 17:11
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/11/28  于泳      1.0     1.0 Version
 */
public class PayChannelNotSupports extends FssException{
    public PayChannelNotSupports(String message) {
        super(message);
    }
}
