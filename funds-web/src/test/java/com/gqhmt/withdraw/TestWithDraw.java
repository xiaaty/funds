package com.gqhmt.withdraw;

import com.gqhmt.TestService;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.callback.loan.PaymentCallback;
import com.gqhmt.fss.architect.accounting.service.FssCheckAccountingService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestWithDraw extends TestService {
    @Resource
    private PaymentCallback paymentCallback;
    @Resource
    private FundAccountService fundAccountService;
    @Resource
    private FssCheckAccountingService fssCheckAccountingService;

    @Test
    public void downBidback() throws Exception {
        FundAccountEntity entity= fundAccountService.getFundsAccount(618633L,0);
        String startTime= "2017-02-11";
        String endTime=DateUtil.dateToString(new Date());
        List<Map<String,String>> listMap=fssCheckAccountingService.getFuiouTradeResult(entity,startTime,endTime,"PWTX");
        for (Map<String,String> map: listMap ) {
                 map.get("txn_rsp_cd");
        }
    }


}