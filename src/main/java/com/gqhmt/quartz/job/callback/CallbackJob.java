package com.gqhmt.quartz.job.callback;

import com.gqhmt.fss.architect.backplate.entity.FssBackplateEntity;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.quartz.job.SupperJob;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

import java.util.*;

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

        }

    }
}
