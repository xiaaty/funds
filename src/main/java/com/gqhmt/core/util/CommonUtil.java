package com.gqhmt.core.util;

import java.util.Date;

/**
 * Filename:    com.gqhmt.core.util.CommonUtil
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/26 11:17
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/26  于泳      1.0     1.0 Version
 */
public class CommonUtil {

    private static CommonUtil commonUtil = new CommonUtil();



    private CommonUtil(){}


    public static String getAccountNo(String tradeType){
        return commonUtil.executeAcconutNo(tradeType);
    }

    public static String getCustNo(){
        return commonUtil.executeCustNo();
    }

    public static String getSeqNo(){
        return commonUtil.executeSeqNo();
    }

    public static String getApplyNo(String tradeType){
        return commonUtil.executeTradeApplyNo(tradeType);
    }

    public  String  executeAcconutNo(String tradeType){
        StringBuffer acc_no= new StringBuffer();
        acc_no.append(GlobalConstants.ACCOUNT_TYPE_MAPPING.get(tradeType));
        acc_no.append(this.getRandom(10));
        acc_no.append(this.getRandom(2)).toString();
        return acc_no.toString();
    }

    public String  executeCustNo(){
        StringBuffer custNo= new StringBuffer();
        custNo.append("KH");
        custNo.append(this.getRandom(4));
        custNo.append(this.getRandom(8));
        custNo.append(this.getRandom(2));
        return  custNo.toString();
    }

    public String executeTradeApplyNo(String tradeType){
        StringBuffer applyNo= new StringBuffer();
        applyNo.append(GlobalConstants.TRADE_APPLY_NO__MAPPING.get(tradeType));
        applyNo.append(this.getDate());
        applyNo.append(this.getRandom(8));
        return  applyNo.toString();
    }

    public String executeOrderNo(){
        StringBuffer orderNo= new StringBuffer();
        orderNo.append(this.getDate()).append(this.getRandom(10));
        return  orderNo.toString();
    }

    public String executeSeqNo(){
        StringBuffer orderNo= new StringBuffer();
        orderNo.append(this.getDate()).append(this.getRandom(10));
        return  orderNo.toString();
    }








    /**
     * 生成10位随机数
     * @return
     */
    public String getRandom(int length) {
        String random = String.valueOf(Math.random());
        int index = random.indexOf(".");
        String result = random.substring(index+1, index+1 + length);
        return result;
    }

    public String getDate(){
        Date date = new Date();
        String year  = String.format("%tY",date);
        String month = String.format("%tm",date);
        String dateString = String.format("%td",date);

        return year+month+dateString;
    }



}
