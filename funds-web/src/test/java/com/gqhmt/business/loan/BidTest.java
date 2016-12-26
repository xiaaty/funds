package com.gqhmt.business.loan;

import com.gqhmt.TestService;
import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.service.BidService;
import com.gqhmt.core.util.LogUtil;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Filename:    com.gqhmt.business.loan.BidTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/11/12 17:03
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/11/12  于泳      1.0     1.0 Version
 */
public class BidTest extends TestService{

    @Resource
    private BidService bidService;

    @Test
    public void queryBidByCustId(){
        int res = bidService.queryBidByCustId(612101);

        assert res>0;
    }

    @Test
    public void queryBidByDateTest(){
        List<Bid> bids = bidService.queryBidByDate("20160912");
        LogUtil.info(this.getClass(),bids.toString());
        assert bids.size()>0;
    }
}
