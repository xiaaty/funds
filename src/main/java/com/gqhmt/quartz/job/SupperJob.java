package com.gqhmt.quartz.job;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.core.configer.Config;
import com.gqhmt.pay.core.factory.ConfigFactory;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.util.LocalIPUtil;

import java.util.Calendar;
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

//    protected boolean isRunning = false ;

    public abstract boolean isRunning();
    private ThreadLocal<Long> longThreadLocalTime = new ThreadLocal<>();
    private ThreadLocal<String> longThreadLocalmsg = new ThreadLocal<>();


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
            LogUtil.info(this.getClass(),":not allowed execute");
            return isSame;
        }


        String  value = (String) config.getValue("job."+type+".value");
        isSame = new Boolean(value);
        return isSame;
    }


    public void startLog(String mes){
        LogUtil.info(getClass(),mes+"跑批开始");
        longThreadLocalmsg.set(mes);
        longThreadLocalTime.set(Calendar.getInstance().getTimeInMillis());

    }

    public void endtLog(){
        String msg = longThreadLocalmsg.get();
        long startTime = longThreadLocalTime.get();
        Long endTime = Calendar.getInstance().getTimeInMillis();
        LogUtil.info(getClass(),msg+"跑批完成,共耗时:"+(endTime-startTime));

    }
}
