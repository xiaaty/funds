package com.gqhmt.core.tag;

import com.gqhmt.core.util.LogUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Filename:    com.gqhmt.core.tag.MoneyTag
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/15 13:41
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/15  于泳      1.0     1.0 Version
 */
public class MoneyTag extends TagSupport {
    private Object money;

    private String  currency;

    private Integer precision;//0分,1角(定义,不使用,一般不会出现此种展示,不实现),2元,long Integer 类型默认0,其他默认2



    public void setMoney(Object money) {
        this.money = money;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public int doStartTag() throws JspException {

        NumberFormat nf = new DecimalFormat("#,##0.00");
        String value = "格式错误";
        BigDecimal moneyValue = precisionExcute();
        if(moneyValue != null){
            value = nf.format(moneyValue);
        }

        try {
            JspWriter out = pageContext.getOut();
            out.print(value);
            out.flush();

        } catch (IOException ex) {
            LogUtil.error(this.getClass(),ex);
        }
        return super.SKIP_BODY;
    }

    private BigDecimal precisionExcute(){
        if (precision == null){
            return precisionDefault(toBigDecimal());
        }else if (precision == 0){
            return precisionFen(toBigDecimal());
        }else if (precision == 2){
            return toBigDecimal();
        }

        return null;
    }

    private BigDecimal toBigDecimal(){
        BigDecimal moneyValue = null;
        if(money instanceof BigDecimal){
            moneyValue = (BigDecimal) money;
        }else if (money instanceof Long || money instanceof Integer){
            moneyValue = new BigDecimal(money.toString());
        }else if(money instanceof String){
            moneyValue = new BigDecimal(money.toString());
        }else if(money instanceof Double || money instanceof Float){
            moneyValue = new BigDecimal(money.toString());
        }

        return  moneyValue;
    }
    /**
     * 默认实现,long int  类型 默认按分,其他类型默认元
     * @return
     */
    private BigDecimal precisionDefault(BigDecimal moneyDec){
        BigDecimal moneyValue = moneyDec;
        if (money instanceof Long || money instanceof Integer){
            moneyValue =  precisionFen(moneyDec);
        }

        return moneyValue;
    }

    private BigDecimal precisionFen(BigDecimal moneyDec){
        if(moneyDec == null){
            return null;
        }
        return moneyDec.divide(new BigDecimal("100")).setScale(2,BigDecimal.ROUND_HALF_EVEN);
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }
}
