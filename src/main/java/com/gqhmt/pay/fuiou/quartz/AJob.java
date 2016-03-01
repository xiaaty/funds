package com.gqhmt.pay.fuiou.quartz;

import com.gqhmt.util.LocalIPUtil;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.pay.fuiou.util.Config;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.core.factory.ConfigFactory;
import com.gqhmt.pay.exception.PayChannelNotSupports;

import java.util.List;

/**
 * Filename:    com.fuiou.quartz.AJob
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/4 13:48
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/4  于泳      1.0     1.0 Version
 */
public abstract class AJob {
    protected final boolean isIp(String type) throws PayChannelNotSupports{
        Config config = (Config) ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU);
        config.getValue("job."+type+".value");
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
            LogUtil.debug(this.getClass(),"job:not allowed execute");
            return isSame;
        }


        String  value = (String) config.getValue("job."+type+".value");
        isSame = new Boolean(value);
        return isSame;
    }

}
