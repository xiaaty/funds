package com.gqhmt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.trade.service.FssTradeApplyService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/13
 * Description:还款划扣
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/13  jhz     1.0     1.0 Version
 */
public class DateUtil {

    /**
     * 时间格式转换(Date to String“yyyy-MM-dd”)
     * @return
     */
    public static String dateToString(Date date){

        if (date == null || date.equals("")) {
            return "";
        }

        String pioDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return pioDate;
    }
    /**
     * 时间格式转换(Date to String"yyyyMMdd")
     * @return
     */
    public static String dateTostring(Date date){
        if (date == null || date.equals("")) {
            return "";
        }
        String pioDate = new SimpleDateFormat("yyyyMMdd").format(date);
        return pioDate;
    }
    /**
     *
     * author:jhz
     * time:2016年5月18日
     * function：把时间返回string HHmm
     */
    public static String timeToString(Date date){
        if (date == null || date.equals("")) {
            return "";
        }
        String pioDate = new SimpleDateFormat("HHmm").format(date);
        return pioDate;
    }

    public static String dateToMonthDay(Date date){
        if (date == null || date.equals("")) {
            return "";
        }
        String pioDate = new SimpleDateFormat("MM-dd").format(date);
        return pioDate;
    }

    public static String dateToYearMonth(Date date){
        if (date == null || date.equals("")) {
            return "";
        }
        String pioDate = new SimpleDateFormat("yyyy-MM").format(date);
        return pioDate;
    }

    /**
     * 24小时制
     * @param date
     * @return
     */
    public static String dateToString_24(Date date){
        if (date == null || date.equals("")) {
            return "";
        }
        String pioDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return pioDate;
    }


    /**
     * 时间格式转换(String to Date)
     * @param dateString
     * @return
     */
    public static Date stringToDate(String dateString){
        if (dateString == null  || dateString.equals("")) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @Title: stringToDate
     * @author 王晓鹏
     * @Description: 日期转换
     * @date 2014年8月26日 下午6:19:57
     * @param dateString
     * @param formatString
     * @return
     */
    public static Date stringToDate(String dateString,String formatString){
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }




    /**
     *
     * @Title: getYearMonth
     * @Description: 出借提成的年月
     * @author lidaqing
     * @date 2014-11-5 下午5:11:53
     * @return
     * String
     */
    public static String getYearMonth(){
        String dateStr = dateToYearMonth(new Date());
        int dateYear = Integer.parseInt(dateStr.substring(0,4));
        String dateMonth = dateStr.substring(5,6);
        if(dateMonth.equals("0")){
            dateMonth = dateStr.substring(6,7);
        }else{
            dateMonth = dateStr.substring(5,7);
        }
        int dateMonths = Integer.parseInt(dateMonth);
        if(dateMonths<2){
            dateMonths = 12;
            dateYear-=1;
        }else{
            dateMonths-=1;
        }
        dateStr = dateYear+"-"+dateMonths;
        return dateStr;
    }

    /**
     * @Title: getYieldDay
     * @Description: 返回两日期相差的天数 (赎回适用,出借需+1)
     * @author lidaqing
     * @date 2014-6-25 上午10:29:39
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return int 相差天数
     * @throws java.text.ParseException
     */
    public static int getDifferDay(String startDate,String endDate) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(startDate));
        long t1 = c.getTimeInMillis();
        c.setTime(sdf.parse(endDate));
        long t2 = c.getTimeInMillis();
        return Integer.parseInt(String.valueOf((t2-t1)/(24*60*60*1000)));
    }


    /**
     * @Title: getYieldDay
     * @Description: 返回两日期相差的天数 (赎回适用,出借需+1)
     * @author lidaqing
     * @date 2014-6-25 上午10:29:39
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return int 相差天数
     * @throws java.text.ParseException
     */
    public static int getDifferDay(Date startDate,Date endDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        long t1 = c.getTimeInMillis();
        c.setTime(endDate);
        long t2 = c.getTimeInMillis();
        return Integer.parseInt(String.valueOf((t2-t1)/(24*60*60*1000)));
    }




    /**
     * @Title:  getContractEndDate
     * @Description: 根据开始日期 和 相差天数 计算结束日期
     * @author guofu
     * @date 2014-6-25 上午10:29:39
     * @param startDate 开始时间
     * @param amount 相差天数
     * @return endDate 结束时间
     */
    public static Date getContractEndDate(Date startDate,int amount) {

        if (startDate == null ) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DAY_OF_MONTH, amount);
        c.getTime();

        return c.getTime();
    }

    /**
     *
     * @param stringDate
     * @return 把String类型转换成日期类型"yyy-MM-dd HH:mm:ss"
     * @throws ParseException
     */
    public static Date toDate(String stringDate) throws ParseException{

        String pattern = "yyy-MM-dd HH:mm:ss"; //首先定义时间格式
        SimpleDateFormat format = new SimpleDateFormat(pattern);//然后创建一个日期格式化类
        Date date = format.parse(stringDate);
        return date;

    }

}
