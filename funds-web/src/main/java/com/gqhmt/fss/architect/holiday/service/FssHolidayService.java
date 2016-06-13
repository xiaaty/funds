package com.gqhmt.fss.architect.holiday.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.holiday.entity.FssHolidayEntity;
import com.gqhmt.fss.architect.holiday.mapper.read.FssHolidayReaderMapper;
//import com.gqhmt.fss.architect.holiday.mapper.writer.FssHolidayWriterMapper;


/**
 *      
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月22日
 * Description:
 * <p>假日列表service层实现类
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月22日  jhz      1.0     1.0 Version
 */
@Service
public class FssHolidayService  {
	 
    private static final Logger LOGGER = LoggerFactory.getLogger(FssHolidayService.class);

    @Resource
    private FssHolidayReaderMapper fssHolidayReaderMapper;
//    @Resource
//    private FssHolidayWriterMapper fssHolidayWriterMapper;


    /**
     * 判断是否是工作日
     *
     * @param date 当前时间
     * @return true-工作日，false-节假日
     * guo jin ran
     * 2015-10-27
     */
    public Boolean isWeekday(String date) throws FssException{

        if (date == null) {
            return false;
        }

        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(new SimpleDateFormat("yyyyMMdd").parse(date));
        } catch (ParseException e) {
            LOGGER.error("日期格式非法,date:{}", date);
            throw new RuntimeException("日期格式非法");
        }
        Map<String,Object> params=new HashMap<>();
//        Params params = new Params();
        params.put("year", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
        params.put("order", "holiday_date");
        List<FssHolidayEntity> holidayList = fssHolidayReaderMapper.query(params);

        boolean isWeekday = true;

        /**
         * 判断是否为周末，在判断是否为周末工作日  返回true
         */
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

            if (holidayList != null && holidayList.size() > 0) {
                for (FssHolidayEntity holiday : holidayList) {
                    if (holiday.getHolidayDate().equals(new SimpleDateFormat("yyyy-MM-dd").format(date))) {
                        isWeekday = holiday.getHolidayType() == 2;
                        break;
                    }
                    isWeekday = false;// 周末日
                }
            } else {
                isWeekday = false;// 周末日
            }

            /**
             * 判断是否为法定节假日  返回false
             */
        } else {
            for (FssHolidayEntity holiday : holidayList) {
                if (holiday.getHolidayDate().equals(new SimpleDateFormat("yyyy-MM-dd").format(date)) && holiday.getHolidayType() == 1) {
                    isWeekday = false;// 节假日
                    break;
                }
            }
        }

        return isWeekday;
    }
    
    /**
     * 判断参数日期是否为节假日 
     * 非节假日返回参数日期
     * @param date 参数日期
     * @return date
     */
    public String isDate(String date)throws FssException {
    	
    	if(date == null){
    		return null;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (ParseException e) {
            LOGGER.error("日期格式非法,date:{}", date);
            throw new RuntimeException("日期格式非法");
        }
        
        /**
         * 判断是否为节假日或者为周末
         * 如果是周末或节假日，则参数日期加一天
         * 直到不是周末且不是节假日，返回参数日期
	     */
        Map<String, Object>  params = new HashMap<>();
        params.put("date", sdf.format(cal.getTime()));
        List<FssHolidayEntity> holidayList = fssHolidayReaderMapper.query(params);
        if(holidayList != null && holidayList.size() != 0){
        	for (FssHolidayEntity holiday : holidayList) {
        		while((holiday.getHolidayDate().equals(sdf.format(cal.getTime())) && holiday.getHolidayType() == 1) ||
        				((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY))){
        			if(holiday.getHolidayDate().equals(sdf.format(cal.getTime())) && holiday.getHolidayType() == 2){
        				return sdf.format(cal.getTime());
        			}
        			cal.add(Calendar.DAY_OF_MONTH, 1); //Calendar.DAY_OF_MONTH(天)+1
        			if((!holiday.getHolidayDate().equals(sdf.format(cal.getTime())) || holiday.getHolidayType() != 1)
        					&& ((cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)) || holiday.getHolidayType() == 2){
        				return sdf.format(cal.getTime());
        			}
        		}
        	}
        }else{
        	while(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            	cal.add(Calendar.DAY_OF_MONTH, 1); //Calendar.DAY_OF_MONTH(天)+1
            	if(cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
                	return sdf.format(cal.getTime());
            	}
            }
        }
        
    	return date;
    }
}
