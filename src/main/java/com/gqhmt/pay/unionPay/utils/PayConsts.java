package com.gqhmt.pay.unionPay.utils;

/**
 * @Title: PayConsts.java
 * @Package com.gq.crm.cust.util
 * @Description: 交易常量类
 * @author liuyang
 * @date 2015-1-5 下午01:39:18
 * @version V1.0
 */
public class PayConsts {
	/**
	 * 交易代码
	 */
	public static final String YL_TRX_CODE_DS = "100001"; // 批量代收
	public static final String YL_TRX_CODE_DF = "100002"; // 批量代付
	public static final String YL_TRX_CODE_QUERY = "200001"; // 交易结果查询

	public static final String YL_VERSION = "04";
	public static final String YL_DATA_TYPE = "2";
	public static final String YL_LEVEL = "5";

	/**
	 * 金额类型
	 */
	public static final String YL_CURRENCY_TYPE_CNY = "CNY"; // 人民币
	public static final String YL_CURRENCY_TYPE_HKD = "HKD"; // 港币
	public static final String YL_CURRENCY_TYPE_USD = "USD"; // 美元

	/**
	 * 账号类型：私人/公司账号
	 */
	public static final String YL_ACCOUNT_PROP_PERSONAL = "0"; // 私人账号
	public static final String YL_ACCOUNT_PROP_COMPANY = "1"; // 公司账号

	/**
	 * 账户类型
	 */
	public static final String YL_ACCOUNT_TYPE_BANKCARD = "00"; // 银行卡
	public static final String YL_ACCOUNT_TYPE_PASSBOOK = "01"; // 存折
	public static final String YL_ACCOUNT_TYPE_CREDITCARD = "02"; // 信用卡

	/**
	 * 交易类型前缀
	 */
	public static final String PAYMENT_PREFIX_DS = "DS_";
	public static final String PAYMENT_PREFIX_DF = "DF_";
	public static final String PAYMENT_PREFIX_QU = "QU_";

	/**
	 * 交易类型
	 */
	public static final String YL_PAYMENT_TYPE_DS = "1"; // 代收
	public static final String YL_PAYMENT_TYPE_DF = "2"; // 代付
	public static final String YL_PAYMENT_TYPE_QU = "3"; // 查询

	/**
	 * 交易状态
	 */
	public static final String YL_DEAL_STATUS_NO_DEAL = "1"; // 未交易
	public static final String YL_DEAL_STATUS_DEALING = "2"; // 交易中
	public static final String YL_DEAL_STATUS_DEAL_SUCCESS = "3"; // 交易成功
	public static final String YL_DEAL_STATUS_DEAL_FAIL = "4"; // 交易失败
	public static final String YL_DEAL_STATUS_DEAL_CANCLE = "5"; // 交易撤销
	public static final String YL_DEAL_STATUS_DEAL_APPOINT_FAIL = "6"; // 交易预约失败
	public static final String YL_DEAL_STATUS_DEAL_PART_SUCCESS = "7"; // 部分成功

	/**
	 * 交易响应代码
	 */
	public static final String YL_DEAL_RET_CODE_SUCCESS = "0000"; // 交易成功
	public static final String YL_DEAL_RET_CODE_APPOINT_FAIL = "G004"; // 交易预约失败

	/**
	 * 交易最大限额（元）
	 */
	public static final String YL_DEAL_LIMIT_AMOUNT = "500000";

	/**
	 * 业务类型
	 */
	public static final String YL_BUSINESS_TYPE_CJ = "1";
	public static final String YL_BUSINESS_TYPE_SH = "2";
	public static final String YL_BUSINESS_TYPE_YYT = "3";

	/**
	 * 金额
	 */
	public static final String YL_NOAMOUNT = "0";
}
