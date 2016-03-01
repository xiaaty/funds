package com.gqhmt.pay.unionPay.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class Constants {

	private static Properties payConfig = new Properties();
	
	static{
		try {
			payConfig.load(new InputStreamReader(Constants.class.getResourceAsStream("/com/unionPay/pay.properties"),"utf-8"));
		} catch (IOException e) {
//			logger.error("Exception in loading the /config/server.properties",e);
		}	
	}

	/************************************ 银联接口相关常量 *******************************************/
	/**
	 * 压缩报文接口地址(压缩/非压缩)
	 */
	public static final String YL_PAY_SERVER_COM_URL = payConfig.getProperty("yl.payServer.comUrl");
	public static final String YL_PAY_SERVER_URL = payConfig.getProperty("yl.payServer.url");
	/**
	 * 签名证书(私钥/公钥)的地址和密码
	 */
	public static final String YL_PAY_PDSCERT_PFX_ADDR = payConfig.getProperty("yl.pdscert.pfx.addr");
	public static final String YL_PAY_PDSCERT_PFX_PASSWORD = payConfig.getProperty("yl.pdscert.pfx.passWord");
	public static final String YL_PAY_PDSCERT_CER_ADDR = payConfig.getProperty("yl.dscert.cer.adds");
	public static final String YL_PAY_PDSCERT_CER_PASSWORD = payConfig.getProperty("yl.dscert.cer.password");

	/**
	 * 用户名/用户密码
	 */
	public static final String YL_USER_NAME = payConfig.getProperty("yl.server.user.name");
	public static final String YL_USER_PASS = payConfig.getProperty("yl.server.user.password");

	/**
	 * 交易业务代码(代收/代付)
	 */
	public static final String YL_BUSINESS_CODE_DS = payConfig.getProperty("yl.business.code.ds");
	public static final String YL_BUSINESS_CODE_DF = payConfig.getProperty("yl.business.code.df");
	/**
	 * 银联网络商户ID
	 */
	public static final String YL_MERCHANT_ID_COMPANY = payConfig.getProperty("yl.merchant.id.company");

	/************************************ 银联接口相关常量 *******************************************/
}
