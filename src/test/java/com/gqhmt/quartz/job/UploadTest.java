package com.gqhmt.quartz.job;

import com.gqhmt.TestService;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.quartz.job.bid.UploadFileJob;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.quartz.job.UploadTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/4/14 15:20
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/4/14  于泳      1.0     1.0 Version
 */
public class UploadTest extends TestService {

    @Resource
    UploadFileJob uploadFileJob;

    @Test
    public void test()  {
        try {
            uploadFileJob.execute();
            assert true;
        } catch (PayChannelNotSupports payChannelNotSupports) {
            assert false;
        }
    }
}
