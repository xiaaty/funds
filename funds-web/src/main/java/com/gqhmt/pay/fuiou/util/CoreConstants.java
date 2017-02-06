package com.gqhmt.pay.fuiou.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 字符集常量类
 * com.gq.core.utils
 * @className CoreConstants<br/> 
 * @author yangfei.eng@gmail.com
 * @createDate 2015-1-13 上午10:52:07<br/>
 * @version 1.0 <br/>
 */
public class CoreConstants {
	public static final String ALL="all";
	public static final String ENCODING_UTF_8 = "UTF-8";
	public static final String ENCODING_GBK = "GBK";
	public static final String ENCODING_ISO8859_1 = "ISO8859-1";

	//session 验证码 key
    public static final String VERIFYING_CODE_KEY = "VERIFYING_CODE_KEY";


    private static Properties sysConfig = new Properties();

    static{
        try {
            sysConfig.load(new InputStreamReader(CoreConstants.class.getResourceAsStream("/config/appContext.properties"),"utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	/**
	 * 短信系统接口参数
	 */
   public static final String SYS_CODE=sysConfig.getProperty("funds.sysCode") ;//用到(之前使用的和冠E同相同的code)
   public static final String ZJ_SYS_CODE=sysConfig.getProperty("funds.zj.sysCode") ;
   public static final String BACKEND_NOTICE_URL = sysConfig.getProperty("backend_notice_url");//用到
   public static final String BACKEND_SMS_URL=sysConfig.getProperty("back_end_sms_url");//用到
   public static final String FUND_CHARGE_TEMPCODE = sysConfig.getProperty("fund_charge_tempCode");
   public static final String FUNDS_CHANGE_CARD_SUMBIT = sysConfig.getProperty("funds.change.card.submit");//用到
   public static final String FUNDS_CHANGE_CARD_SUCCES = sysConfig.getProperty("funds.change.card.success");//用到
   public static final String FUNDS_CHANGE_CARD_FAIL = sysConfig.getProperty("funds.change.card.fail");//用到
   public static final String FUNDS_SYS_CODE = sysConfig.getProperty("funds.sysCode");//用到
   public static final String FUND_UPDATE_BANKCARD_SUBMIT_TEMPCODE = sysConfig.getProperty("fund_update_bankcard_submit_tempCode");//用到
   public static final String FUND_WITHDRAW_TEMPCODE = sysConfig.getProperty("fund_withdraw_tempCode");
   public static final Integer SMS_DX=1;//用到
   public static final Integer SMS_NOTICE=2;//用到
   public static final String FUND_BOND_TEMPCODE = sysConfig.getProperty("fund_balance_tempCode");//对公红包余额

    //-----------统一支付商户号配置---------------------------
    public static final String TYZF_MERCHID = sysConfig.getProperty("tyzf_merchid");//商户号
    public static final String MQ_SEND_NAME = sysConfig.getProperty("mq_send_name");//发送报文名称
    public static final String MQ_RESIVE_NAME = sysConfig.getProperty("mq_resive_name");//接收报文名称


}
