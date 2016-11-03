package com.gqhmt.quartz.job.account;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.account.bean.FssMappingBean;
import com.gqhmt.fss.architect.account.service.FssMappingService;
import com.gqhmt.funds.architect.account.service.NoticeService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.pay.fuiou.util.CoreConstants;
import com.gqhmt.pay.fuiou.util.HttpClientUtil;
import com.gqhmt.quartz.job.SupperJob;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Filename:    com.fuiou.quartz.ChangeCardJob
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/10/26 10:24
 * Description:红包账户发送短信
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/10/26  keyulai      1.0     1.0 Version
 */
@Component
public class BondAccountSmsJob extends SupperJob {

    @Resource
    private FssMappingService fssMappingService;
    @Resource
    private NoticeService noticeService;
    @Resource
    private CustomerInfoService customerInfoService;

    private static boolean isRunning = false;
    @Scheduled(cron="0 0 9 * * ?")
    public void execute() throws PayChannelNotSupports {
        if(!isIp("upload")){
            return;
        }
        if(isRunning) return;
        startLog(" 红包账户余额短信通知 ");
        isRunning = true;

        try {
            BigDecimal sumAmount = fssMappingService.getBondSumAmount("10010006");//查询红包总额
            List<FssMappingBean> list=fssMappingService.getMobileList("12020001");//发送通知给手机号列表
            List<Map<String, String>> balist = new ArrayList<Map<String, String>>();
            Map<String, String> baMap = new HashMap<String, String>();
            baMap.put("sysCode", CoreConstants.ZJ_SYS_CODE);	//商户系统编码，在平台系统查看
            balist.add(baMap);
            if(list!=null && list.size()>0){
                for (FssMappingBean bean:list){
                    Map<String, String> map = new HashMap<String, String>();
                    if(bean!=null && bean.getMobile()!=null){
                        map.put("phoneNo", bean.getMobile());	//手机号，多个用","分开
                        map.put("0", sumAmount.movePointLeft(4).setScale(2, BigDecimal.ROUND_HALF_UP).toString());     //模板内容账户余额（万元）
                        map.put("tempCode", CoreConstants.FUND_BOND_TEMPCODE);	//商户模板编码，在平台系统查看
                        map.put("sendType", "1");     //1-短信
                        balist.add(map);
                    }
                }
                String result = HttpClientUtil.sendBody(CoreConstants.BACKEND_NOTICE_URL,balist);
                System.out.print("返回结果："+result);
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
