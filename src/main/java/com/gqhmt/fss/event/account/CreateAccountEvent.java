package com.gqhmt.fss.event.account;

import org.springframework.context.ApplicationEvent;

/**
 * Filename:    com.gqhmt.fss.event.CreateAccountEventCopyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/6 16:50
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/6  于泳      1.0     1.0 Version
 */
public class CreateAccountEvent extends ApplicationEvent {

    public CreateAccountEvent(Object source) {
        super(source);
    }
}
