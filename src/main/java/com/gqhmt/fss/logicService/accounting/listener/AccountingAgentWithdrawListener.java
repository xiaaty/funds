package com.gqhmt.fss.logicService.accounting.listener;

import com.gqhmt.fss.logicService.pay.event.trade.AgentWithdrawEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Filename:    com.gqhmt.fss.logicService.accounting.listener.AccountingAgentWithdrawListener
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/31 17:11
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/31  于泳      1.0     1.0 Version
 */
@Component
public class AccountingAgentWithdrawListener implements ApplicationListener<AgentWithdrawEvent> {
    @Override
    public void onApplicationEvent(AgentWithdrawEvent agentWithdrawEvent) {

    }
}
