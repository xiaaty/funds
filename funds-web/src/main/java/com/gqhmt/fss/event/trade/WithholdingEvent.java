package com.gqhmt.fss.event.trade;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * Filename:    com.gqhmt.fss.event.trade.WithholdingEvent
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/31 16:48
 * Description:
 * <p>代扣充值事件</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/31  于泳      1.0     1.0 Version
 */
@Component
public class WithholdingEvent extends ApplicationEvent{


    public WithholdingEvent(Object source) {
        super(source);
    }
}
