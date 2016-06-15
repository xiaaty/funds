package com.gqhmt.fss.architect.holiday;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.gqhmt.TestService;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.extServInter.fetchService.FetchDataService;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月23日
 * Description: 节假日测试类
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月23日  jhz      1.0     1.0 Version
 */
public class HolidayTest extends TestService {
	@Resource
    private FetchDataService fetchDataService;
    @Test
    public void fetchDataTest(){
        Map<String,String > map = new HashMap<>();
        map.put("date","2016-03-26");
        try {
            List<String> list  = fetchDataService.featchData(String.class,"holidayDate",map);
            assert list.size() == 1;
        } catch (FssException e) {
            e.printStackTrace();
            assert  false;
        }
    }
}