package com.gqhmt.quartz.job.callback;

import com.gqhmt.core.connection.UrlConnectUtil;
import com.gqhmt.core.util.FssBeanUtil;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.core.util.ResourceUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.fss.architect.backplate.entity.FssBackplateEntity;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.quartz.job.SupperJob;
import com.gqhmt.util.ServiceLoader;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Filename:    com.gqhmt.quartz.fuiouFtp.bid.CallbackJob
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/14 16:12
 * Description:
 * <p>ftp执行结果回调,满标,回款,其他ftp转账</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/14  于泳      1.0     1.0 Version
 */
public class CallbackJob extends SupperJob {

    @Resource
    private FssBackplateService fssBackplateService;


    public void execute() throws JobExecutionException {
        List<FssBackplateEntity> backplateEntities = fssBackplateService.findBackAll();
        for(FssBackplateEntity entity:backplateEntities){
            this.callback(entity);
        }

    }


    public void callback(FssBackplateEntity entity){
        String  className = ResourceUtil.getValue("config.appContext",entity.getMchn()+"_"+entity.getTradeType()+"_className");

        //验证是否配置回盘参数
        if(className == null){
//                entity.set
        }
        try {
            Class class1 = Class.forName(className.substring(className.lastIndexOf("\\.")));
            Object obj = ServiceLoader.get(class1);
            String methodName = className.substring(className.lastIndexOf("\\.")+1);
            Method method = FssBeanUtil.findMethod(class1,methodName,String.class,String.class);
            Object value = method.invoke(obj,entity.getMchn(),entity.getSeqNo());
            Response response  = UrlConnectUtil.sendDataReturnAutoSingleObject(Response.class,entity.getMchn()+"_"+entity.getTradeType(),value);
            //修改完成状态;


        } catch (Exception e) {
            LogUtil.error(getClass(),e);
        }
    }
}



