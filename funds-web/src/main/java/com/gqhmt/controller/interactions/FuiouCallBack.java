package com.gqhmt.controller.interactions;


import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.card.entiry.FssPosBackEntity;
import com.gqhmt.fss.architect.card.service.FssPosBackService;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.pay.fuiou.util.SecurityUtils;
import com.gqhmt.pay.service.TradeRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Filename:    com.gq.p2p.controller.interactions
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/06/16
 * Description:回调接口
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/06/16  柯禹来      1.0     1.0 Version
 */
@Controller
@RequestMapping("/interaction")
public class FuiouCallBack {

	@Resource
	private TradeRecordService tradeRecordService;

	@Resource
	private FssPosBackService fssPosBackService;

	@Resource
	private CustomerInfoService customerInfoService;

	/**
	 * 网页充值回调接口
	 *
	 * @param mchnt_cd
	 * @param mchnt_txn_ssn
	 * @param amt
	 * @param remark
	 * @param signature
	 * @return
	 */
	@RequestMapping("/webRechageFuiouCallback")
	@ResponseBody
	public String rechageForWeb(String resp_code, String mchnt_cd, String mchnt_txn_ssn, String login_id, String amt, String remark, String signature) {
		String signValue = amt + "|" + login_id + "|" + mchnt_cd + "|" + mchnt_txn_ssn + "|" + resp_code;
		boolean flag = SecurityUtils.verifySign(signValue, signature);
		System.out.println("fuiou callback webRechage:" + signValue);
		LogUtil.info(this.getClass(), "fuiou callback webRechage:" + signValue);
		StringBuffer result = new StringBuffer();
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<ap>");
		StringBuffer plain = new StringBuffer();
		plain.append("<plain>");
		if (flag) {
			if ("0001".equals(resp_code)) {
				plain.append("<resp_code>0000</resp_code>");
			} else {
				try {
//					AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_ASYN_VALID, ThirdPartyType.FUIOU, mchnt_txn_ssn, "0000".equals(resp_code) ? "success" : "failed", amt, login_id, CommandEnum.FundsCommand.FUNDS_CHARGE, "", remark);

					tradeRecordService.asynNotOrderCommand(mchnt_txn_ssn,"0000".equals(resp_code) ? "success" : "failed",amt,login_id);

					plain.append("<resp_code>0000</resp_code>");
				} catch (Exception e) {
					plain.append("<resp_code>9999</resp_code>");
				}
			}
		} else {
			plain.append("<resp_code>9999</resp_code>");
		}
		plain.append("<mchnt_cd>");
		plain.append(mchnt_cd);
		plain.append("</mchnt_cd>");
		plain.append("<mchnt_txn_ssn>");
		plain.append(mchnt_txn_ssn);
		plain.append("</mchnt_txn_ssn>");
		plain.append("</plain>");
		result.append(plain);
		result.append("<signature>");
		result.append(SecurityUtils.sign(plain.toString()));
		result.append("</signature>");

		result.append("</ap>");
		return result.toString();
	}

	/**
	 * 网页提现回调接口
	 *
	 * @param mchnt_cd
	 * @param mchnt_txn_ssn
	 * @param remark
	 * @param signature
	 * @return
	 */
	@RequestMapping("/webWithdrawFuiouCallback")
	@ResponseBody
	public String withdrawWeb(String resp_code, String mchnt_cd, String mchnt_txn_ssn, String login_id, String amt, String remark, String signature) {

		String signValue = amt + "|" + login_id + "|" + mchnt_cd + "|" + mchnt_txn_ssn + "|" + resp_code;
		boolean flag = SecurityUtils.verifySign(signValue, signature);
		System.out.println("fuiou callback webWithdraw:" + signValue);
		LogUtil.info(this.getClass(), "fuiou callback webWithdraw:" + signValue);
		StringBuffer result = new StringBuffer();
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<ap>");
		StringBuffer plain = new StringBuffer();
		plain.append("<plain>");
		if (flag) {
			if ("0001".equals(resp_code)) {
				plain.append("<resp_code>0000</resp_code>");
			} else {
				try {
//					AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_ASYN_VALID, ThirdPartyType.FUIOU, mchnt_txn_ssn, "0000".equals(resp_code) ? "success" : "failed", amt, login_id, CommandEnum.FundsCommand.FUNDS_WITHDRAW, "", remark);
					tradeRecordService.asynNotOrderCommand(mchnt_txn_ssn,"0000".equals(resp_code) ? "success" : "failed",amt,login_id);
					plain.append("<resp_code>0000</resp_code>");
				} catch (Exception e) {
					plain.append("<resp_code>9999</resp_code>");
				}
			}
		} else {
			plain.append("<resp_code>9999</resp_code>");
		}
		plain.append("<mchnt_cd>");
		plain.append(mchnt_cd);
		plain.append("</mchnt_cd>");
		plain.append("<mchnt_txn_ssn>");
		plain.append(mchnt_txn_ssn);
		plain.append("</mchnt_txn_ssn>");
		plain.append("</plain>");
		result.append(plain);
		result.append("<signature>");
		result.append(SecurityUtils.sign(plain.toString()));
		result.append("</signature>");

		result.append("</ap>");

		return result.toString();

	}

	/**
	 * 充值回调接口
	 *
	 * @param mchnt_cd
	 * @param mchnt_txn_ssn
	 * @param mchnt_txn_dt
	 * @param mobile_no
	 * @param amt
	 * @param remark
	 * @param signature
	 * @return
	 */
	@RequestMapping("/rechageFuiouCallback")
	@ResponseBody
	public String rechage(String mchnt_cd, String mchnt_txn_ssn, String mchnt_txn_dt, String mobile_no, String amt, String remark, String signature) {
		String signValue = amt + "|" + mchnt_cd + "|" + mchnt_txn_dt + "|" + mchnt_txn_ssn + "|" + mobile_no + "|" + remark;
		boolean flag = SecurityUtils.verifySign(signValue, signature);
		System.out.println("fuiou callback rechage:" + signValue);
		LogUtil.info(this.getClass(), "fuiou callback rechage:" + signValue);
		StringBuffer result = new StringBuffer();
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<ap>");
		StringBuffer plain = new StringBuffer();
		plain.append("<plain>");
		if (flag) {
			try {
//				AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_ASYN_VALID_NOT_ORDER, ThirdPartyType.FUIOU, mchnt_txn_ssn,
//						"success", amt, mobile_no, CommandEnum.FundsCommand.FUNDS_CHARGE, remark);

				tradeRecordService.asynNotOrderCommand(mchnt_txn_ssn, "success",amt,mobile_no);
				plain.append("<resp_code>0000</resp_code>");
			} catch (Exception e) {
				plain.append("<resp_code>9999</resp_code>");
			}
		} else {
			plain.append("<resp_code>9999</resp_code>");
		}
		plain.append("<mchnt_cd>");
		plain.append(mchnt_cd);
		plain.append("</mchnt_cd>");
		plain.append("<mchnt_txn_ssn>");
		plain.append(mchnt_txn_ssn);
		plain.append("</mchnt_txn_ssn>");
		plain.append("</plain>");
		result.append(plain);
		result.append("<signature>");
		result.append(SecurityUtils.sign(plain.toString()));
		result.append("</signature>");

		result.append("</ap>");
		return result.toString();

	}

	/**
	 * 提现回调接口
	 *
	 * @param mchnt_cd
	 * @param mchnt_txn_ssn
	 * @param mchnt_txn_dt
	 * @param mobile_no
	 * @param amt
	 * @param remark
	 * @param signature
	 * @return
	 */
	@RequestMapping("/withdrawFuiouCallback")
	@ResponseBody
	public String withraw(String mchnt_cd, String mchnt_txn_ssn, String mchnt_txn_dt, String mobile_no, String amt, String remark, String signature) {
		String signValue = amt + "|" + mchnt_cd + "|" + mchnt_txn_dt + "|" + mchnt_txn_ssn + "|" + mobile_no + "|" + remark;
		boolean flag = SecurityUtils.verifySign(signValue, signature);
		System.out.println("fuiou callback withdraw:" + signValue);
		LogUtil.info(this.getClass(), "fuiou callback withdraw:" + signValue);
		StringBuffer result = new StringBuffer();
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<ap>");
		StringBuffer plain = new StringBuffer();
		plain.append("<plain>");
		if (flag) {
			try {
//				AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_ASYN_VALID_NOT_ORDER, ThirdPartyType.FUIOU, mchnt_txn_ssn,
//						"success", amt, mobile_no, CommandEnum.FundsCommand.FUNDS_WITHDRAW, mchnt_txn_dt, remark);
				tradeRecordService.asynNotOrderCommand(mchnt_txn_ssn, "success",amt,mobile_no);

				plain.append("<resp_code>0000</resp_code>");
			} catch (Exception e) {
				plain.append("<resp_code>9999</resp_code>");
			}
		}
		plain.append("<mchnt_cd>");
		plain.append(mchnt_cd);
		plain.append("</mchnt_cd>");
		plain.append("<mchnt_txn_ssn>");
		plain.append(mchnt_txn_ssn);
		plain.append("</mchnt_txn_ssn>");
		plain.append("</plain>");
		result.append(plain);
		result.append("<signature>");
		result.append(SecurityUtils.sign(plain.toString()));
		result.append("</signature>");

		result.append("</ap>");

		return result.toString();

	}

	/**
	 * 代扣回调接口
	 *
	 * @param mchnt_cd
	 * @param mchnt_txn_ssn
	 * @param mchnt_txn_dt
	 * @param resp_code
	 * @param amt
	 * @param signature
	 * @return
	 */
	@RequestMapping("/withholdingFuiouCallback")
	@ResponseBody
	public String withholding(String mchnt_cd, String mchnt_txn_ssn, String mchnt_txn_dt, String resp_code, String amt, String signature) {
		String signValue = amt + "|" + mchnt_cd + "|" + mchnt_txn_dt + "|" + mchnt_txn_ssn + "|" + resp_code;
		boolean flag = SecurityUtils.verifySign(signValue, signature);
		System.out.println("fuiou callback withholding:" + signValue);
		LogUtil.info(this.getClass(), "fuiou callback withholding:" + signValue);
		StringBuffer result = new StringBuffer();
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<ap>");
		StringBuffer plain = new StringBuffer();
		plain.append("<plain>");
		if (flag) {
			if ("0001".equals(resp_code)) {
				plain.append("<resp_code>0000</resp_code>");
			} else {
				try {
//					AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_ASYN_VALID, ThirdPartyType.FUIOU, mchnt_txn_ssn, "0000".equals(resp_code) ? "success" : "failed", amt, CommandEnum.FundsCommand.FUNDS_WITHHOLDING);
					tradeRecordService.asynNotOrderCommand(mchnt_txn_ssn,"0000".equals(resp_code) ? "success" : "failed",amt,null);
					plain.append("<resp_code>0000</resp_code>");
				} catch (Exception e) {
					plain.append("<resp_code>9999</resp_code>");
				}
			}
		} else {
			plain.append("<resp_code>9999</resp_code>");
		}
		plain.append("<mchnt_cd>");
		plain.append(mchnt_cd);
		plain.append("</mchnt_cd>");
		plain.append("<mchnt_txn_ssn>");
		plain.append(mchnt_txn_ssn);
		plain.append("</mchnt_txn_ssn>");
		plain.append("</plain>");
		result.append(plain);
		result.append("<signature>");
		result.append(SecurityUtils.sign(plain.toString()));
		result.append("</signature>");

		result.append("</ap>");

		return result.toString();
	}

	/**
	 * 代付回调接口
	 *
	 * @param mchnt_cd
	 * @param mchnt_txn_ssn
	 * @param mchnt_txn_dt
	 * @param amt
	 * @param signature
	 * @return
	 */
	@RequestMapping("/agentWithdrawFuiouCallback")
	@ResponseBody
	public String agentWithdraw(String mchnt_cd, String mchnt_txn_ssn, String mchnt_txn_dt, String amt, String resp_code, String signature) {
		String signValue = amt + "|" + mchnt_cd + "|" + mchnt_txn_dt + "|" + mchnt_txn_ssn + "|" + resp_code;
		boolean flag = SecurityUtils.verifySign(signValue, signature);
		System.out.println("fuiou callback agentWithdraw:" + signValue);
		LogUtil.info(this.getClass(), "fuiou callback agentWithdraw:" + signValue);
		StringBuffer result = new StringBuffer();
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<ap>");
		StringBuffer plain = new StringBuffer();
		plain.append("<plain>");
		if (flag) {
			if ("0001".equals(resp_code)) {
				plain.append("<resp_code>0000</resp_code>");
			} else {
				try {
//					AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_ASYN_VALID, ThirdPartyType.FUIOU, mchnt_txn_ssn, "0000".equals(resp_code) ? "success" : "failed", amt, "", CommandEnum.FundsCommand.FUNDS_AGENT_WITHDRAW);

					tradeRecordService.asynNotOrderCommand(mchnt_txn_ssn,"0000".equals(resp_code) ? "success" : "failed",amt,null);
					plain.append("<resp_code>0000</resp_code>");
				} catch (Exception e) {
					plain.append("<resp_code>9999</resp_code>");
				}
			}
		} else {
			plain.append("<resp_code>9999</resp_code>");
		}
		plain.append("<mchnt_cd>");
		plain.append(mchnt_cd);
		plain.append("</mchnt_cd>");
		plain.append("<mchnt_txn_ssn>");
		plain.append(mchnt_txn_ssn);
		plain.append("</mchnt_txn_ssn>");
		plain.append("</plain>");
		result.append(plain);
		result.append("<signature>");
		result.append(SecurityUtils.sign(plain.toString()));
		result.append("</signature>");
		result.append("</ap>");
		return result.toString();
	}


	/**
	 * @param mchnt_cd
	 * @param mchnt_txn_ssn
	 * @param user_id_from
	 * @param mobile_no
	 * @param cust_nm
	 * @param certif_id
	 * @param email
	 * @param city_id
	 * @param parent_bank_id
	 * @param bank_nm
	 * @param capAcntNo
	 * @param resp_code
	 * @param signature
	 * @return String    返回类型
	 * @throws
	 * @Title: upCustInfo
	 * @Description: 富友更改用户信息通知回调
	 */
	@RequestMapping("/upCustInfoFuiouCallback")
	@ResponseBody
	public String upCustInfo(String mchnt_cd, String mchnt_txn_ssn, String user_id_from, String mobile_no, String cust_nm, String certif_id, String email, String city_id, String parent_bank_id, String bank_nm, String capAcntNo, String resp_code, String signature) {
		//回调明文
		String reqSign = bank_nm + "|" + capAcntNo + "|" + certif_id + "|" + city_id + "|" + cust_nm
				+ "|" + email + "|" + mchnt_cd + "|" + mchnt_txn_ssn + "|" + mobile_no + "|"
				+ parent_bank_id + "|" + resp_code + "|" + user_id_from;
		//验签
		boolean flag = SecurityUtils.verifySign(reqSign, signature);
		System.out.println("fuiou callback upCustInfo:" + reqSign);
		LogUtil.info(this.getClass(), "fuiou callback upCustInfo:" + reqSign);
		//返回富友接收结果
		StringBuffer result = new StringBuffer();
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<ap>");
		StringBuffer plain = new StringBuffer();
		plain.append("<plain>");
		if (flag) {
//            try {
//                changeCardService.fuiouCallback(mchnt_txn_ssn,certif_id,capAcntNo,resp_code);
//                result.append("<resp_code>0000</resp_code>");
//                } catch (Exception e) {
//
//                    LogUtil.error(this.getClass(),e);
//                    result.append("<resp_code>9999</resp_code>");
//                }
//            }

			//更新成功
			if ("0000".equals(resp_code)) {
				Map<String, String> retMap = new HashMap<String, String>();
				retMap.put("mchnt_cd", mchnt_cd);
				retMap.put("mchnt_txn_ssn", mchnt_txn_ssn);
				retMap.put("mobile_no", mobile_no);
				retMap.put("cust_nm", cust_nm);
				retMap.put("certif_id", certif_id);
				retMap.put("email", email);
				retMap.put("city_id", city_id);
				retMap.put("parent_bank_id", parent_bank_id);
				retMap.put("bank_nm", bank_nm);
				retMap.put("capAcntNo", capAcntNo);
				try {
					// 更新用户信息
//					customerInfoService.updateCustomerInfoCallBack(retMap);
					plain.append("<resp_code>0000</resp_code>");
				} catch (Exception e) {
					plain.append("<resp_code>9999</resp_code>");
				}
			}
		} else {
			plain.append("<resp_code>9999</resp_code>");
		}
		plain.append("<mchnt_cd>");
		plain.append(mchnt_cd);
		plain.append("</mchnt_cd>");
		plain.append("<mchnt_txn_ssn>");
		plain.append(mchnt_txn_ssn);
		plain.append("</mchnt_txn_ssn>");
		plain.append("</plain>");
		result.append(plain);
		result.append("<signature>");
		result.append(SecurityUtils.sign(plain.toString()));
		result.append("</signature>");
		result.append("</ap>");
		return result.toString();
	}


	@RequestMapping("/returnWithdraw")
	@ResponseBody
	public String returnWithdraw(String mchnt_cd, String mchnt_txn_ssn, String mobile_no, String mchnt_txn_dt, String amt, String remark, String signature) {
		//回调明文
		String signValue = amt + "|" + mchnt_cd + "|" + mchnt_txn_dt + "|" + mchnt_txn_ssn + "|" + mobile_no + "|" + remark;
		//验签
		boolean flag = SecurityUtils.verifySign(signValue, signature);
		System.out.println("fuiou callback returnWithdraw:" + signValue);
		LogUtil.info(this.getClass(), "fuiou callback returnWithdraw:" + signValue);
		//返回富友接收结果
		String result = "SUCCESS";


		if (flag) {
			try {
//				AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_RETRUN_WITHDRAW, ThirdPartyType.FUIOU, mchnt_txn_ssn, mobile_no, new BigDecimal(amt));
			} catch (Exception e) {
				LogUtil.error(this.getClass(), e);
				result = "FAIL";
			}
		} else {
			result = "FAIL SIGNVALUE";
		}

		return result;
	}

	/**
	 * 根据据富有有返回结果进行处理
	 * @param resp_code
	 * @param mchnt_cd
	 * @param mchnt_txn_ssn
	 * @param in_cust_no
	 * @param amt
	 * @param remark
	 * @param signature
     * @throws FssException
     */
	@RequestMapping("/returnOfflineRechargeResult")
	@ResponseBody
	public String returnOfflineRechargeResult(String resp_code,String mchnt_cd,String mchnt_txn_ssn,String in_cust_no,String amt,String remark,String signature) throws FssException{
		//回调明文
		String signValue = amt+"|"+ in_cust_no + "|"+ mchnt_cd +"|" +mchnt_txn_ssn+"|"+remark+"|"+resp_code;
		//验签
		boolean flag = true;//SecurityUtils.verifySign(signValue, signature);
		LogUtil.info(this.getClass(), "fuiou callback returnWithhold:"+flag+":" + signValue+":"+signature);
		//返回富友接收结果
		String result = "SUCCESS";
		if (flag) {
			try {
//				AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_ASYN_VALID, ThirdPartyType.FUIOU, mchnt_txn_ssn, mobile_no, new BigDecimal(amt));
				tradeRecordService.asynNotOrderCommand(mchnt_txn_ssn,"0000".equals(resp_code) ? "success" : "failed",amt,in_cust_no);
			} catch (Exception e) {
				LogUtil.error(this.getClass(), e);
				result = "FAIL";
			}
		} else {
			result = "FAIL SIGNVALUE";
		}
		return  result;
	}
	/**
	 * 根据据富有有返回结果进行处理
	 * @param mchntCd
	 * @param mchntNm
	 * @param userNm
	 * @param mobileNo
	 * @param acntNo
	 * @param credtNo
	 * @param contract_st
	 * @param acntIsVerif1
	 * @param acntIsVerif2
	 * @param acntIsVerif3
	 * @param acntIsVerif4
     * @return
     * @throws FssException
     */
	@RequestMapping("/returnPosContractResult")
	@ResponseBody
	public String returnPosContractResult(String mchntCd,String mchntNm,String userNm,String mobileNo,String acntNo,String credtNo,String contract_st,String acntIsVerif1,String acntIsVerif2,String acntIsVerif3,String acntIsVerif4) throws FssException{
		//回调明文
		//验签
		LogUtil.info(this.getClass(), "pos签约回调："+"客户名："+userNm+"；手机号:"+mobileNo+"；身份证号："+credtNo+";银行卡号："+acntNo+";协议状态:"+contract_st+";卡号户名验证结果:"+acntIsVerif1+";卡号密码验证结果:"+acntIsVerif2+";户名证件号验证结果:"+acntIsVerif3+";手机号验证结果:"+acntIsVerif4);
		//返回富友接收结果
		String result = "0";
		try {
			FssPosBackEntity entity=fssPosBackService.createPosBack(userNm,mobileNo,acntNo,credtNo,contract_st,acntIsVerif1,acntIsVerif2,acntIsVerif3,acntIsVerif4);
			Integer a=fssPosBackService.insert(entity);
			customerInfoService.updateCustomerState(entity,mobileNo,contract_st,acntNo);
			result=a.toString();
		} catch (Exception e) {
			LogUtil.error(this.getClass(), e);
			result = "0";
		}

		return  result;
	}
}