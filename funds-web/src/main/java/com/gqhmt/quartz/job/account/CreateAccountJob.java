package com.gqhmt.quartz.job.account;

import com.gqhmt.core.util.GenerateBeanUtil;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.account.entity.FssAccountMappingEntity;
import com.gqhmt.fss.architect.account.service.FssAccountMappingService;
import com.gqhmt.funds.architect.account.bean.FundAccountCustomerBean;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.quartz.job.SupperJob;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.*;

/**
 * Filename:    com.fuiou.quartz.ChangeCardJob
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at: 2016/11/4 10:24
 * Description:异步调用统一支付开户
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/11/4  keyulai      1.0     1.0 Version
 */
@Component
public class CreateAccountJob extends SupperJob {

    @Resource
    private FundAccountService fundAccountService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private FssAccountMappingService fssAccountMappingService;

    private static boolean isRunning = false;
    @Scheduled(cron="0 0 9 * * ?")
    public void execute() throws PayChannelNotSupports {
        if(!isIp("upload")){
            return;
        }
        if(isRunning) return;
        startLog("统一支付开户 ");
        isRunning = true;

        try {
            //查询所有在资金已开户但未在统一支付平台开户的账户信息
            Map map=new HashMap();
            List<FundAccountCustomerBean> list=fundAccountService.findAllFundAcountList();
            if(list!=null && list.size()>0){
                for (FundAccountCustomerBean bean:list){
                    //单条发送需要开户的信息（走MQ）
                    fssAccountMappingService.createFssAccountMapping(bean.getId(),bean.getBusiType());
                }
            }
        } catch (Exception e) {
            LogUtil.error(getClass(),e);
        }finally{
            isRunning = false;
        }
        endtLog();
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }
}
