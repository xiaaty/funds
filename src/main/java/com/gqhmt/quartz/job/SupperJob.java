package com.gqhmt.quartz.job;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.core.configer.Config;
import com.gqhmt.pay.core.factory.ConfigFactory;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.util.LocalIPUtil;

import java.util.List;

/**
 * Filename:    com.gqhmt.quartz.job.SupperJob
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/25 10:05
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/25  于泳      1.0     1.0 Version
 */
public abstract class SupperJob{

    protected boolean isRunning ;

    public final boolean isRunning() {
        return isRunning;
    }


    protected final boolean isIp(String type) throws PayChannelNotSupports {

    Config config= ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU);
    String apachIp = (String)config.getValue("job.ip.value");
    List<String> localIpList = LocalIPUtil.getLocalIpList();
    boolean isSame = false;
    for (String localIp : localIpList) {
        if (apachIp.indexOf(localIp) > -1) {
            isSame = true;
            break;
        }
    }
    if (!isSame){
        LogUtil.debug(this.getClass(),"fuiouFtp:not allowed execute");
        return isSame;
    }


    String  value = (String) config.getValue("fuiouFtp."+type+".value");
    isSame = new Boolean(value);
    return isSame;
}
}
