package com.gqhmt.fss.architect.account.command;

import com.gqhmt.fss.pay.exception.CommandParmException;
import com.gqhmt.util.ThirdPartyType;

/**
 * Filename:    com.gq.thirdPartyPay.core.command
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 * 资金交易实现，本地数据库更新及远程第三方交互
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/17 20:36
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/17  于泳      1.0     1.0 Version
 */
public interface Command {

    public AccountCommandResponse command(CommandEnum commandEnum,ThirdPartyType thirdPartyType,Object ... objects) throws CommandParmException;

}
