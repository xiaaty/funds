package com.gqhmt.fss.architect.holiday;

import java.text.ParseException;
import java.util.Date;

import com.gqhmt.TestService;
import com.gqhmt.util.CommonUtil;

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
public class DateTest extends TestService {
    public static void main(String[] args) throws ParseException {
		
    	String date="2016-03-23 12:25:30";
    	Date d=new Date();
    	Date date2 = CommonUtil.stringToDate(date);
    	if(date2.before(d)){
    		System.out.println(d+"------"+date2);
    	}else{
    		System.out.println(date2);
    	}
	}
}