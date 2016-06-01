package com.gqhmt.sftp.txt;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.sftp.entity.FssAccountFileEntity;
import com.gqhmt.sftp.entity.FssBusinessTradeEntity;
import com.gqhmt.sftp.entity.FssFinanceSumEntity;
import com.gqhmt.sftp.entity.FssProjectInfoEntity;
import com.gqhmt.util.CommonUtil;

/**
 *
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年5月11日
 * Description:
 * <p>生成csv文件
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月11日  jhz      1.0     1.0 Version
 */
@Service
public class CreateTXT  {
	/**
	 *
	 * author:jhz
	 * time:2016年5月11日
	 * function：生成项目信息txt文件返回文件生成路径
	 * @throws FssException
	 */
	public String createProjectInfoTXT(List<FssProjectInfoEntity> queryItemsInfos) throws FssException{
		String path = getClassPath();
		File filepath  = new File(path+"/txt/reject");
		String fileName=filepath+"/"+"P2P_PWXM_"+CommonUtil.dateTostring(new Date())+"_"+CommonUtil.timeToString(new Date())+".txt";
		BufferedOutputStream buff = null;
		StringBuffer write = new StringBuffer();
		String enter = "\r\n";
		OutputStream outSTr = null;
		try {
			outSTr = new FileOutputStream(fileName); // 建立
			buff = new BufferedOutputStream(outSTr);
			//把内容写入文件
			if(queryItemsInfos.size()>0){
//		    	write.append("商户号"+"|");
//				    write.append("平台流水号"+"|");
//				    write.append("项目编号"+"|");
//				    write.append("借款类型"+"|");
//				    write.append("借款标题"+"|");
//				    write.append("推荐机构"+"|");
//				    write.append("借款用途"+"|");
//				    write.append("借款金额"+"|");
//				    write.append("预期收益"+"|");
//				    write.append("产品名称"+"|");
//				    write.append("还款方式"+"|");
//				    write.append("借款期限"+"|");
//				    write.append("筹标起始日"+"|");
//				    write.append("每份投标金额"+"|");
//				    write.append("最低投标份数"+"|");
//				    write.append("最多投标金额"+"|");
//				    write.append("借款人平台用户"+"|");
//				    write.append("借款人金账户用户名"+"|");
//				    write.append("借款人项目概述"+"|");
//				    write.append("费用项"+"|");
//				    write.append("筹集情况"+"|");
//				    write.append("还款期数"+"|");
//				    write.append("备用金额"+"|");
//				    write.append("第三方支付公司ID"+"|");
//				    write.append("发标年化利率"+"|");
//				    write.append("借款人名称"+"|");
//				    write.append( "借款人证件类型"+"|");
//				    write.append( "借款人证件号码"+"|");
//				    write.append(enter);
				for (FssProjectInfoEntity info : queryItemsInfos) {
					write.append(info.getMchn()+"|");
					write.append(info.getSeqNo()+"|");
					write.append(info.getItemNo()+"|");
					write.append(info.getLoanType()+"|");
					write.append(info.getLoanTittle()+"|");
					write.append(info.getOrganization()+"|");
					write.append(info.getDescription()+"|");
					write.append(info.getLoanAmt()*100+"|");
					write.append(info.getExpectedReturn()+"|");
					write.append(info.getProductName()+"|");
					write.append(info.getRepaymentType()+"|");
					write.append(info.getLoanTime()+"|");
					write.append(info.getStartDate()+"|");
					write.append(info.getEachBidAmount()*100+"|");
					write.append(info.getMinNum()+"|");
					write.append(info.getMaxAmount()*100+"|");
					write.append(info.getAccNo()+"|");
					write.append(info.getAccGoldNo()+"|");
					write.append(info.getLoanItemDescription()+"|");
					write.append(info.getFeeType()*100+"|");
					write.append(info.getStatus()+"|");
					write.append(info.getPeriod()+"|");
					write.append(info.getPrepareAmount()*100+"|");
					write.append(info.getPayChannel()+"|");
					write.append(info.getBidYearIrr()+"|");
					write.append(info.getCustName()+"|");
					write.append(info.getCertType()+"|");
					write.append(info.getCertNo());
					write.append(enter);
				}
			}
			buff.write(write.toString().getBytes("GBK"));
			buff.flush();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buff.close();
				outSTr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fileName;
	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月11日
	 * function：标的财务汇总文件 txt文件
	 * @throws FssException
	 */
	public String createFinanceSumTXT(List<FssFinanceSumEntity> queryFinaSum) throws FssException{
		String path = getClassPath();
		File filepath  = new File(path+"/txt/reject/"+CommonUtil.dateTostring(new Date()));
		String fileName=filepath+"/"+"sum.txt";
		BufferedOutputStream buff = null;
		StringBuffer write = new StringBuffer();
		String enter = "\r\n";
		OutputStream outSTr = null;
		try {
			outSTr = new FileOutputStream(fileName); // 建立
			buff = new BufferedOutputStream(outSTr);
			//把内容写入文件
			if(queryFinaSum.size()>0){
				write.append("数据总数："+"|"+queryFinaSum.size()+enter);
				write.append("支付机构标的ID"+"|");
				write.append("支付机构平台ID"+"|");
				write.append("放款客户号"+"|");
				write.append("客户姓名"+"|");
				write.append("客户身份证号"+"|");
				write.append("标的状态"+"|");
				write.append("发标日期"+"|");
				write.append("实际满标日期"+"|");
				write.append("应还款总本金"+"|");
				write.append("应还款总利息"+"|");
				write.append("最后还款日期"+"|");
				write.append("实际结清日期"+"|");
				write.append("至结清日累计已还款本金"+"|");
				write.append("至结清日累计已还款利息"+"|");
				write.append("本日还款本金"+"|");
				write.append("本日还款利息"+"|");
				write.append("截止当日累计还款本金"+"|");
				write.append("截止当日累计还款利息"+"|");
				write.append("已垫资总金额"+"|");
				write.append("剩余未偿垫资"+"|");
				write.append("放款金额"+"|");
				write.append("截止当日累计放款");
				write.append(enter);
				for (FssFinanceSumEntity info : queryFinaSum) {
					write.append(info.getOrgTargetId()+"|");
					write.append(info.getOrgTerraceId()+"|");
					write.append(info.getCustNo()+"|");
					write.append(info.getCustName()+"|");
					write.append(info.getCertNo()+"|");
					write.append(info.getTargetState()+"|");
					write.append(info.getTenderTime()+"|");
					write.append(info.getFullScaleTime()+"|");
					write.append(info.gettReCaptical().multiply(new BigDecimal(100))+"|");
					write.append(info.gettReInterest().multiply(new BigDecimal(100))+"|");
					write.append(info.getlReTime()+"|");
					write.append(info.getaSquareTime()+"|");
					write.append(info.getaReCaptical().multiply(new BigDecimal(100))+"|");
					write.append(info.getaReInterest().multiply(new BigDecimal(100))+"|");
					write.append(info.getTodayReCaptical().multiply(new BigDecimal(100))+"|");
					write.append(info.getTodayReInterest().multiply(new BigDecimal(100))+"|");
					write.append(info.geteReCaptical().multiply(new BigDecimal(100))+"|");
					write.append(info.geteReInterest().multiply(new BigDecimal(100))+"|");
					write.append(info.getPaidSum().multiply(new BigDecimal(100))+"|");
					write.append(info.getDebtSum().multiply(new BigDecimal(100))+"|");
					write.append(info.getCreditSum().multiply(new BigDecimal(100))+"|");
					write.append(info.gettCreditSum().multiply(new BigDecimal(100)));
					write.append(enter);
				}
			}
			buff.write(write.toString().getBytes("GBK"));
			buff.flush();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buff.close();
				outSTr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fileName;
	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月11日
	 * function：P2P个人平台开户文件 txt文件
	 * @throws FssException
	 */
	public String createAccountFileTXT(List<FssAccountFileEntity> queryAccountFiles) throws FssException{
		String path = getClassPath();
		File filepath  = new File(path+"/txt/reject");
		String fileName=filepath+"/"+"P2P_PW10_"+CommonUtil.dateTostring(new Date())+"_"+CommonUtil.timeToString(new Date())+".txt";
		BufferedOutputStream buff = null;
		StringBuffer write = new StringBuffer();
		String enter = "\r\n";
		OutputStream outSTr = null;
		try {
			outSTr = new FileOutputStream(fileName); // 建立
			buff = new BufferedOutputStream(outSTr);
			//把内容写入文件
			if(queryAccountFiles.size()>0){
//				write.append("商户号"+"|");
//				write.append("平台注册流水"+"|");
//				write.append("平台用户名"+"|");
//				write.append("JZH登陆用户名"+"|");
//				write.append("年龄"+"|");
//				write.append("用户名"+"|");
//				write.append("证件类型"+"|");
//				write.append("证件号"+"|");
//				write.append("性别"+"|");
//				write.append("银行预留手机号"+"|");
//				write.append("地址"+"|");
//				write.append("用户属性"+"|");
//				write.append("注册日期"+"|");
//				write.append("第三方支付公司ID"+"|");
//				write.append("操作类型"+"|");
//				write.append("备注"+"|");
//				write.append(enter);
				for (FssAccountFileEntity info: queryAccountFiles) {
					write.append(info.getMchn()+"|");
					write.append(info.getRegisteredSeqNo()+"|");
					write.append(info.getPlatformUsername()+"|");
					write.append(info.getLoginUsername()+"|");
					write.append(info.getAge()+"|");
					write.append(info.getAccName()+"|");
					write.append(info.getCertType()+"|");
					write.append(info.getCertNo()+"|");
					write.append(info.getSex()+"|");
					write.append(info.getMobile()+"|");
					write.append(info.getAddress()+"|");
					write.append(info.getUserProperties()+"|");
					write.append(info.getRegistrationDate()+"|");
					write.append(info.getThirdPartyPaymentId()+"|");
					write.append(info.getActionType()+"|");
					write.append(info.getRemark());
					write.append(enter);
				}
			}
			buff.write(write.toString().getBytes("GBK"));
			buff.flush();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buff.close();
				outSTr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fileName;
	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月11日
	 * function:P2P商户交易（包括冻结，动账，解冻）TXT文件
	 * @param queryBusinessTrade
	 * @throws FssException
	 */
	public String createCreditInfoTXT(List<FssBusinessTradeEntity> queryBusinessTrade) throws FssException{
		//文件名
		String path = getClassPath();
		File filepath  = new File(path+"/txt/reject");
		String fileName=filepath+"/"+"P2P_PWJY_"+CommonUtil.dateTostring(new Date())+"_"+CommonUtil.timeToString(new Date())+".txt";
		BufferedOutputStream buff = null;
		StringBuffer write = new StringBuffer();
		String enter = "\r\n";
		OutputStream outSTr = null;
		try {
			outSTr = new FileOutputStream(fileName); // 建立
			buff = new BufferedOutputStream(outSTr);
			//把内容写入文件
			if(queryBusinessTrade.size()>0){
//		write.append("商户号"+"|");
//		write.append("第三方支付公司ID"+"|");
//		write.append("交易日期"+"|");
//		write.append("金账户交易类型"+"|");
//		write.append("项目编号"+"|");
//		write.append("合同编号"+"|");
//		write.append("出账人富友用户名"+"|");
//		write.append("出账人平台用户名"+"|");
//		write.append("金额"+"|");
//		write.append("手续费"+"|");
//		write.append("该笔还款本金"+"|");
//		write.append("该笔还款利息"+"|");
//		write.append("入账人富友用户名"+"|");
//		write.append("入账人平台用户名"+"|");
//		write.append("借款人姓名"+"|");
//		write.append("借款人证件类型"+"|");
//		write.append("借款人证件号码"+"|");
//		write.append("投资人用户名"+"|");
//		write.append("投资人富友登陆用户名"+"|");
//		write.append("投资人姓名"+"|");
//		write.append("投资人证件类型"+"|");
//		write.append("投资人证件号码"+"|");
//		write.append("业务类型"+"|");
//		write.append(enter);
				for (FssBusinessTradeEntity info : queryBusinessTrade) {
					write.append(info.getMchn()+"|");
					write.append(info.getThirdPartyPaymentId()+"|");
					write.append(info.getTradeDate()+"|");
					write.append(info.getTradeType()+"|");
					write.append(info.getItemNo()+"|");
					write.append(info.getContractNo()+"|");
					write.append(info.getOutFuiouUsername()+"|");
					write.append(info.getOutPlatformUsername()+"|");
					write.append(info.getAmt().multiply(new BigDecimal(100))+"|");
					write.append(info.getCharge().multiply(new BigDecimal(100))+"|");
					write.append(info.getThisRepaymentPrincipal().multiply(new BigDecimal(100))+"|");
					write.append(info.getThisRepaymentInterest().multiply(new BigDecimal(100))+"|");
					write.append(info.getComeFuiouUsername()+"|");
					write.append(info.getComePlatformUsername()+"|");
					write.append(info.getLoanUsername()+"|");
					write.append(info.getLoanCertType()+"|");
					write.append(info.getLoanCertNo()+"|");
					write.append(info.getLendUsername()+"|");
					write.append(info.getLendFuiouUsername()+"|");
					write.append(info.getLendName()+"|");
					write.append(info.getLendCertType()+"|");
					write.append(info.getLendCertNo()+"|");
					write.append(info.getBusiType());
					write.append(enter);
				}
			}
			buff.write(write.toString().getBytes("GBK"));
			buff.flush();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buff.close();
				outSTr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fileName;
	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月24日
	 * function：得到本地路径
	 */
	public String getClassPath(){
		String path = this.getClass().getResource("").getPath();
		String className  = this.getClass().getName();
		String packge = className.substring(0,className.lastIndexOf(".")).replace(".","/");
		if(path.lastIndexOf(packge)>0){
			return path.substring(0,path.lastIndexOf(packge));
		}
		return path;
	}
}
