package com.gqhmt.pay.service;

import com.gqhmt.TestService;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.CommonUtil;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.trade.WithdrawDto;
import com.gqhmt.pay.service.trade.IFundsTrade;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Filename:    com.gqhmt.pay.service.PayWithdrawTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/10/25 15:28
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/10/25  于泳      1.0     1.0 Version
 */
public class PayWithdrawTest extends TestService{

    private final long cust_id = 611295;

    @Resource
    private IFundsTrade iFundTrade;


    @Test
    public void withdraw(){

        try {
            Thread.sleep(1000*6);
            LogUtil.info(getClass(),"睡眠6秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WithdrawDto dto = new WithdrawDto();
        dto.setCust_no(String.valueOf(cust_id));
        dto.setTrade_type("11040002");
        dto.setMchn("39491645EHNJ");
        dto.setSeq_no(CommonUtil.getSeqNo());
        dto.setAmt(new BigDecimal("983494.00"));
        dto.setCharge_amt(new BigDecimal("2.00"));
        try {
            iFundTrade.withdraw(dto);
        } catch (FssException e) {
            e.printStackTrace();
        }
        //1000000.00
    }

    public static void  main (String args){
//        ThreadExecutor.execute(getRunable());
//        ThreadExecutor.execute(getRunable());
        PayWithdrawTest t = new PayWithdrawTest();
        Thread thread = new Thread(t.getRunable());
        Thread thread1 = new Thread(t.getRunable());
        thread.start();
        thread1.start();
    }

    public Runnable getRunable(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                withdraw();
            }
        };

        return runnable;
    }

}
