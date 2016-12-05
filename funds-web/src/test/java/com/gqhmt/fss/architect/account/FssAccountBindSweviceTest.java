package com.gqhmt.fss.architect.account;

import com.gqhmt.TestService;
import com.gqhmt.fss.architect.account.entity.FssAccountBindEntity;
import com.gqhmt.fss.architect.account.service.FssAccountBindService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Filename:    com.gqhmt.fss.architect.account.FssAccountBindSweviceTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/12/3 17:29
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/12/3  于泳      1.0     1.0 Version
 */
public class FssAccountBindSweviceTest  extends TestService{

    @Resource
    private FssAccountBindService fssAccountBindService ;

    @Test
    public void queryBindAccountLimitTest(){
        List<FssAccountBindEntity> list = fssAccountBindService.queryBindAccountLimit();
        assert list.size()>0;
    }
}
