package com.gqhmt.callback;

import com.gqhmt.TestService;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.quartz.job.callback.CallbackJob;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.callback.CallbackTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/4/6 14:14
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/4/6  于泳      1.0     1.0 Version
 */
public class CallbackTest  extends TestService{

    @Resource
    private  CallbackJob callbackJob;

    @Resource
    private FssBackplateService backplateService;

    @Test
    public void test(){
//    	try {
//        FssBackplateEntity entity = backplateService.get(85l);
//
//            callbackJob.callback(entity);
//            assert true;
//        } catch (FssException e) {
//            assert  false;
//        }


    }
}
