package com.gqhmt.quartz.job.callback;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.connection.UrlConnectUtil;
import com.gqhmt.core.util.FssBeanUtil;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.core.util.ResourceUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.fss.architect.backplate.entity.FssBackplateEntity;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.quartz.job.SupperJob;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;
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
@Component
public class CallbackJob extends SupperJob {

    @Resource
    private FssBackplateService fssBackplateService;

    private static boolean isRunning = false;

    @Resource
    private ApplicationContext context;
    @Scheduled(cron="0 0/1 *  * * * ")
    public void execute() throws JobExecutionException, FssException {
        if(!isIp("upload")){
            return;
        }

        if(isRunning) return;

        startLog("业务执行完成回盘");
        isRunning = true;
        List<FssBackplateEntity> backplateEntities = fssBackplateService.findBackAll();

        try {
            for(FssBackplateEntity entity:backplateEntities){
                this.callback(entity);
            }
        } catch (FssException e) {
            LogUtil.error(getClass(),e);
        }finally{
            isRunning = false;
        }

        endtLog();
    }


    public void callback(FssBackplateEntity entity) throws FssException {
        String  classMethodName = ResourceUtil.getValue("config.appContext",entity.getMchn()+"_"+entity.getTradeType()+"_className");

        //验证是否配置回盘参数
        if(classMethodName == null){
        	entity.setRepayCount(entity.getRepayCount()+1);
        	entity.setRepayResult(98060004);//无需回盘
        	fssBackplateService.update(entity);
        }else{
        	try {
        		int num = classMethodName.lastIndexOf(".");
        		String className = classMethodName.substring(0,num);
        		Class class1 = Class.forName(className);
        		Object obj = context.getBean(class1);
        		String methodName = classMethodName.substring(classMethodName.lastIndexOf(".")+1);
        		Method method = FssBeanUtil.findMethod(class1,methodName,String.class,String.class);
        		Object value = method.invoke(obj,entity.getMchn(),entity.getSeqNo());
        		Response response  = UrlConnectUtil.sendDataReturnAutoSingleObject(Response.class,entity.getMchn()+"_"+entity.getTradeType(),value);
        		//修改完成状态;
        		if("00000000".equals(response.getResp_code()) || "0000".equals(response.getResp_code())){//返回代码为00000000或0000表示回盘成功
        			entity.setRepayResult(98060001);//回盘成功
        		}else{
        			entity.setRepayResult(98060003);//回盘失败
        		}
                entity.setRepayCount(entity.getRepayCount()+1);
                entity.setModifyTime(new Date());
        		fssBackplateService.update(entity);
        	} catch (Exception e) {
        		LogUtil.error(getClass(),e);
        		throw new FssException("",e);
        	}
        }
       
    }


    @Override
    public boolean isRunning() {
        return isRunning;
    }
}



