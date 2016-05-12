package com.gqhmt.sftp.csv;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import com.gqhmt.core.FssException;
import com.gqhmt.sftp.entity.FssCreditInfoEntity;
import com.gqhmt.sftp.entity.FssFinanceSumEntity;
import com.gqhmt.sftp.entity.FssProjectInfoEntity;
import com.gqhmt.sftp.service.FssProjectService;
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
public class CreateCSV  {
	
	
	@Resource
	private FssProjectService fssProjectService;
	/**
	 * 
	 * author:jhz
	 * time:2016年5月11日
	 * function：生成项目信息csv文件
	 * @throws FssException 
	 */
	public void createProjectInfoCVS() throws FssException{
			
		  List<FssProjectInfoEntity> queryItemsInfos = fssProjectService.queryItemsInfos();
		  if(queryItemsInfos==null) throw new FssException("没找到项目信息文件");
		    List<Map> exportData = new ArrayList<Map>();
		    Map<String,Object> row1 = new LinkedHashMap<String, Object>();
		    for (FssProjectInfoEntity info : queryItemsInfos) {
		    		row1=new LinkedHashMap<String, Object>();
		    		row1.put("1", info.getMchn());
				    row1.put("2",info.getSeqNo());
				    row1.put("3", info.getItemNo());
				    row1.put("4", info.getLoanType());
				    row1.put("5", info.getLoanTittle());
				    row1.put("6", info.getOrganization());
				    row1.put("7", info.getDescription());
				    row1.put("8", info.getLoanAmt());
				    row1.put("9", info.getExpectedReturn());
				    row1.put("10", info.getProductName());
				    row1.put("11", info.getRepaymentType());
				    row1.put("12", info.getLoanTime());
				    row1.put("13", info.getStartDate());
				    row1.put("14", info.getEachBidAmount());
				    row1.put("15", info.getMinNum());
				    row1.put("16", info.getMaxAmount());
				    row1.put("17", info.getAccNo());
				    row1.put("18", info.getAccGoldNo());
				    row1.put("19", info.getLoanItemDescription());
				    row1.put("20", info.getFeeType());
				    row1.put("21", info.getStatus());
				    row1.put("22", info.getPeriod());
				    row1.put("23", info.getPrepareAmount());
				    row1.put("24", info.getPayChannel());
				    row1.put("25", info.getBidYearIrr());
				    row1.put("26", info.getCustName());
				    row1.put("27", info.getCertType());
				    row1.put("28", info.getCertNo());
		    	exportData.add(row1);
			}
		    LinkedHashMap<String,String> map = new LinkedHashMap<>();
		    map.put("1", "商户号");
		    map.put("2","平台流水号");
		    map.put("3", "项目编号");
		    map.put("4", "借款类型");
		    map.put("5", "借款标题");
		    map.put("6", "推荐机构");
		    map.put("7", "借款用途");
		    map.put("8", "借款金额");
		    map.put("9", "预期收益");
		    map.put("10", "产品名称");
		    map.put("11", "还款方式");
		    map.put("12", "借款期限");
		    map.put("13", "筹标起始日");
		    map.put("14", "每份投标金额");
		    map.put("15", "最低投标份数");
		    map.put("16", "最多投标金额");
		    map.put("17", "借款人平台用户");
		    map.put("18", "借款人金账户用户名");
		    map.put("19", "借款人项目概述");
		    map.put("20", "费用项");
		    map.put("21", "筹集情况");
		    map.put("22", "还款期数");
		    map.put("23", "备用金额");
		    map.put("24", "第三方支付公司ID");
		    map.put("25", "发标年化利率");
		    map.put("26", "借款人名称");
		    map.put("27", "借款人证件类型");
		    map.put("28", "借款人证件号码");
		 
		    String path = "F:/";
		    String fileName = "文件导出";
		    File file = CSVUtils.createCSVFile(exportData, map, path, fileName);
		    file.renameTo(new File("F:/P2P_PWXM_"+CommonUtil.dateTostring(new Date())+".csv"));
//		    String fileName2 = file.getName();
//		    System.out.println("文件名称：" + fileName2);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月11日
	 * function：标的财务汇总文件 csv文件
	 * @throws FssException 
	 */
	public void createFinanceSumCVS() throws FssException{
		 List<FssFinanceSumEntity> queryFinaSum = fssProjectService.queryFinaSum();
		if(queryFinaSum==null) throw new FssException("标的财务汇总文件不存在");
		List<Map> exportData = new ArrayList<Map>();
		Map<String,Object> row1 = new LinkedHashMap<String, Object>();
		row1=new LinkedHashMap<String, Object>();
		row1.put("1", "支付机构标的ID");
		row1.put("2","支付机构平台ID");
		row1.put("3", "放款客户号");
		row1.put("4", "客户姓名");
		row1.put("5", "客户身份证号");
		row1.put("6", "标的状态");
		row1.put("7","发标日期");
		row1.put("8", "实际满标日期");
		row1.put("9", "应还款总本金");
		row1.put("10", "应还款总利息");
		row1.put("11", "最后还款日期");
		row1.put("12", "实际结清日期");
		row1.put("13", "至结清日累计已还款本金");
		row1.put("14", "至结清日累计已还款利息");
		row1.put("15", "本日还款本金");
		row1.put("16", "本日还款利息");
		row1.put("17", "截止当日累计还款本金");
		row1.put("18", "截止当日累计还款利息");
		row1.put("19", "已垫资总金额");
		row1.put("20", "剩余未偿垫资");
		row1.put("21", "放款金额");
		row1.put("22", "截止当日累计放款");
		for (FssFinanceSumEntity info : queryFinaSum) {
			row1=new LinkedHashMap<String, Object>();
			row1.put("1", info.getOrgTargetId());
			row1.put("2",info.getOrgTerraceId());
			row1.put("3", info.getCustNo());
			row1.put("4", info.getCustName());
			row1.put("5", info.getCertNo());
			row1.put("6", info.getTargetState());
			row1.put("7", info.getTenderTime());
			row1.put("8", info.getFullScaleTime());
			row1.put("9", info.gettReCaptical());
			row1.put("10", info.gettReInterest());
			row1.put("11", info.getlReTime());
			row1.put("12", info.getaSquareTime());
			row1.put("13", info.getaReCaptical());
			row1.put("14", info.getaReInterest());
			row1.put("15", info.getTodayReCaptical());
			row1.put("16", info.getTodayReInterest());
			row1.put("17", info.geteReCaptical());
			row1.put("18", info.geteReInterest());
			row1.put("19", info.getPaidSum());
			row1.put("20", info.getDebtSum());
			row1.put("21", info.getCreditSum());
			row1.put("22", info.gettCreditSum());
			exportData.add(row1);
		}
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
		map.put("1", "总数据数");
		map.put("2",String.valueOf(queryFinaSum.size()));
		map.put("3", "");
		map.put("4", "");
		map.put("5", "");
		map.put("6", "");
		map.put("7", "");
		map.put("8", "");
		map.put("9", "");
		map.put("10", "");
		map.put("11", "");
		map.put("12", "");
		map.put("13", "");
		map.put("14", "");
		map.put("15", "");
		map.put("16", "");
		map.put("17", "");
		map.put("18", "");
		map.put("19", "");
		map.put("20", "");
		map.put("21", "");
		map.put("22", "");
		String path = "F:/";
		String fileName = "文件导出";
		File file = CSVUtils.createCSVFile(exportData, map, path, fileName);
		file.renameTo(new File("F:/P2P_PWXM_"+CommonUtil.dateTostring(new Date())+".csv"));
//		    String fileName2 = file.getName();
//		    System.out.println("文件名称：" + fileName2);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月11日
	 * function：标的放款明细 csv文件
	 * @throws FssException 
	 */
	public void createCreditInfoCVS() throws FssException{
		List<FssCreditInfoEntity> queryCreditInfo = fssProjectService.queryCreditInfo();
		if(queryCreditInfo==null) throw new FssException("标的放款明细文件不存在");
		List<Map> exportData = new ArrayList<Map>();
		Map<String,Object> row1 = new LinkedHashMap<String, Object>();
		row1=new LinkedHashMap<String, Object>();
		row1.put("1", "标的号(项目ID)");
		row1.put("2","交易日期");
		row1.put("3", "交易类型");
		row1.put("4", "投资人姓名");
		row1.put("5", "投资人证件类型");
		row1.put("6", "投资人证件号码");
		row1.put("7","付机构借款人ID");
		row1.put("8", "借款人姓名");
		row1.put("9", "借款人身份证号");
		row1.put("10", "该笔放款金额");
		row1.put("11", "该笔还款本金");
		row1.put("12", "该笔还款利息");
		for (FssCreditInfoEntity info : queryCreditInfo) {
			row1=new LinkedHashMap<String, Object>();
			row1.put("1", info.getTargetId());
			row1.put("2",info.getTradeTime());
			row1.put("3", info.getTradeType());
			row1.put("4", info.getCustName());
			row1.put("5", info.getCertType());
			row1.put("6", info.getCertNo());
			row1.put("7", info.getLoanId());
			row1.put("8", info.getLoanName());
			row1.put("9", info.getLoanCertNo());
			row1.put("10", info.getPayAmount());
			row1.put("11", info.getRepaymentCapital());
			row1.put("12", info.getRepaymentInterest());
			exportData.add(row1);
		}
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
		map.put("1", "总笔数");
		map.put("2",String.valueOf(queryCreditInfo.size()));
		map.put("3", "");
		map.put("4", "");
		map.put("5", "");
		map.put("6", "");
		map.put("7", "");
		map.put("8", "");
		map.put("9", "");
		map.put("10", "");
		map.put("11", "");
		map.put("12", "");
		String path = "F:/";
		String fileName = "文件导出";
		File file = CSVUtils.createCSVFile(exportData, map, path, fileName);
		file.renameTo(new File("F:/P2P_PWXM_"+CommonUtil.dateTostring(new Date())+".csv"));
//		    String fileName2 = file.getName();
//		    System.out.println("文件名称：" + fileName2);
	}
	
}
