package com.gqhmt.withdraw;

import com.gqhmt.TestService;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.callback.loan.PaymentCallback;
import com.gqhmt.util.DateUtil;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

public class TestWithDraw extends TestService {
    @Resource
    private PaymentCallback paymentCallback;

    @Test
    public void downBidback() throws Exception {
        Calendar calendar=Calendar.getInstance();
        Date a=new Date();
        calendar.setTime(a);
        calendar.add(calendar.DATE,-3);
        String b=DateUtil.dateTostring(calendar.getTime());
        LogUtil.info(this.getClass(),"------------"+b);
    }


}