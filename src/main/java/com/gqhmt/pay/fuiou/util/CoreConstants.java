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

    public static final String DEFAULT_FTP_USER = sysConfig.getProperty("server.ftp.username");
    public static final String DEFAULT_FTP_PASS = sysConfig.getProperty("server.ftp.userpwd");
    public static final String DEFAULT_FTP_IP = sysConfig.getProperty("server.ftp.addr");
    public static final String CH_SERVICES_ADDR = sysConfig.getProperty("server.webservice");
    
    /**新版短信接口参数（麦讯通）  **/
	public static final String MXT_SMS_URL = sysConfig.getProperty("mxt.sms.url");
	public static final String MXT_SMS_USERID = sysConfig.getProperty("mxt.sms.userID");
	public static final String MXT_SMS_ACCOUNTID = sysConfig.getProperty("mxt.sms.accountID");
	public static final String MXT_SMS_PASSWORD = sysConfig.getProperty("mxt.sms.password");
    
	/**
	 * 短信系统接口参数
	 */
   public static final String SYS_CODE=sysConfig.getProperty("funds.sysCode") ;
   public static final String FULL_BID_TEMP_CODE=sysConfig.getProperty("fullBid_tempCode");
   public static final String REPAYMENT_TEMP_CODE=sysConfig.getProperty("repayMent_tempCode");
   public static final String TENDERSUCCESS_TEMP_CODE=sysConfig.getProperty("tenderSuccess_tempCode");
   public static final String TENDERFAILURE_TEMP_CODE=sysConfig.getProperty("tenderFailure_tempCode");
   public static final String BACKEND_NOTICE_URL = sysConfig.getProperty("backend_notice_url");
   public static final String BACKEND_SMS_URL=sysConfig.getProperty("back_end_sms_url");

   public static final String MARKETING_BASEURL = sysConfig.getProperty("marketing.baseUrl");
   public static final String FUNDS_SUMBYDAY = sysConfig.getProperty("funds.sumDay");
   public static final String FUNDS_CHANGE_CARD_SUMBIT = sysConfig.getProperty("funds.change.card.submit");
   public static final String FUNDS_CHANGE_CARD_SUCCES = sysConfig.getProperty("funds.change.card.success");
   public static final String FUNDS_CHANGE_CARD_FAIL = sysConfig.getProperty("funds.change.card.fail");
   public static final String FUNDS_SYS_CODE = sysConfig.getProperty("funds.sysCode");
   public static final String FUND_UPDATE_BANKCARD_SUBMIT_TEMPCODE = sysConfig.getProperty("fund_update_bankcard_submit_tempCode");
   public static final Integer SMS_DX=1;
   public static final Integer SMS_NOTICE=2;
}
