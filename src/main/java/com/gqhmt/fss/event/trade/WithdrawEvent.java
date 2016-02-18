package com.gqhmt.fss.event.trade;

import org.springframework.context.ApplicationEvent;

/**
 * Filename:    com.gqhmt.fss.event.trade.WithdrawEvent
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/31 16:39
 * Description:
 * <p>web网银提现事件</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/31  于泳      1.0     1.0 Version
 */
public class WithdrawEvent extends ApplicationEvent {

    public WithdrawEvent(Object source) {
        super(source);
    }
}
