package com.gqhmt.extServInter.fetchService;

import com.gqhmt.TestService;
import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.core.exception.FssException;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.extServInter.fetchService.FetchDataServiceTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/16 14:39
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/16  于泳      1.0     1.0 Version
 */

public class FetchDataServiceTest extends TestService{

    @Resource
    private FetchDataService fetchDataService;
    @Test
    public void fetchDataTest(){
        Map<String,String > map = new HashMap<>();
        map.put("id","46");
        map.put("type","1");
        try {
            List<Tender> list  = fetchDataService.featchData(Tender.class,"tenderList",map);

            assert list.size() == 157;
        } catch (FssException e) {
            e.printStackTrace();
            assert  false;
        }
    }
}
