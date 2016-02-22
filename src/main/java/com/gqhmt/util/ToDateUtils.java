package com.gqhmt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @author jhz
 * time:2016年2月19日
 * function:把String类型转换为Date
 */
public class ToDateUtils {
	
	public static Date toDate(String stringDate) throws ParseException{
		
		String pattern = "yyy-MM-dd HH:mm:ss"; //首先定义时间格式
		SimpleDateFormat format = new SimpleDateFormat(pattern);//然后创建一个日期格式化类
		Date date = format.parse(stringDate);
		return date;
		
	}
}
