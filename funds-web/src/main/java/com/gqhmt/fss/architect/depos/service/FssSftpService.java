package com.gqhmt.fss.architect.depos.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.depos.entity.FssAccountFileEntity;
import com.gqhmt.fss.architect.depos.entity.FssBusinessTradeEntity;
import com.gqhmt.fss.architect.depos.entity.FssFinanceSumEntity;
import com.gqhmt.fss.architect.depos.entity.FssProjectInfoEntity;
import com.gqhmt.fss.architect.depos.entity.FssSftpRecordEntity;
import com.gqhmt.fss.architect.depos.txt.CreateTXT;
import com.gqhmt.fss.architect.depos.txt.ReadTXTFile;
import com.gqhmt.fss.architect.depos.utils.SFTPDownLoadutils;
import com.gqhmt.fss.architect.depos.utils.SFTPuploadUtils;
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
 * Create at:   2016年5月18日
 * Description:
 * <p>sftp上传下载
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月18日  jhz      1.0     1.0 Version
 */
@Service
public class FssSftpService {
	@Resource
	private SFTPuploadUtils sFTPuploadUtils;
	@Resource
	private CreateTXT createTXT;
	@Resource
	private SFTPDownLoadutils sftpDownLoadutils;
	@Resource
	private ReadTXTFile readTXTFile;
	@Resource
	private FssSftpRecordService fssSftpRecordService;
	@Resource
	private FssAccountFileService fssAccountFileService;
	@Resource
	private FssBusiTradeService fssBusiTradeService;
	@Resource
	private FssBidFinanceService fssBidFinanceService;
	@Resource
	private FssProjectInfoService fssProjectInfoService;
	/**
	 *
	 * author:jhz
	 * time:2016年5月16日
	 * function：1.P2P个人平台开户文件
	 */
	public  void createAccount() throws FssException {
		List<FssAccountFileEntity> queryAccountFiles =fssAccountFileService.queryByStatus("10110001");
		if(queryAccountFiles==null||queryAccountFiles.size()==0) throw new FssException("不存在未报备的个人开户文件");
		String createAccountFileTXT = createTXT.createAccountFileTXT(queryAccountFiles);
		try {
			sFTPuploadUtils.upLoadFile("/projectInfo/0001000F0279762/check", createAccountFileTXT);
			FssSftpRecordEntity insertSftpRecord = fssSftpRecordService.insertSftpRecord("P2P个人平台开户文件", queryAccountFiles.size(), "11120001");
			for (FssAccountFileEntity fssAccountFileEntity : queryAccountFiles) {
				fssAccountFileEntity.setParentId(insertSftpRecord.getId());
				fssAccountFileEntity.setStatus("10110002"); //10110001未报备,10110002已报备
				fssAccountFileService.updateAccountFile(fssAccountFileEntity);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.info(getClass(), e.getMessage());
			e.printStackTrace();
		}

	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月16日
	 * function：P2P商户交易（包括冻结，动账，解冻）TXT文件
	 */
	public  void createCreditInfoTxt() throws FssException {
		List<FssBusinessTradeEntity> queryByStatus = fssBusiTradeService.queryByStatus("10110001");
		if(queryByStatus==null||queryByStatus.size()==0) throw new FssException("商户交易不存在未报备的文件");
		String createCreditInfoCVS = createTXT.createCreditInfoTXT(queryByStatus);
		try {
			sFTPuploadUtils.upLoadFile("/projectInfo/0001000F0279762/check", createCreditInfoCVS);
			FssSftpRecordEntity insertSftpRecord = fssSftpRecordService.insertSftpRecord("商户交易", queryByStatus.size(), "11120003");
			for (FssBusinessTradeEntity fssBusinessTradeEntity : queryByStatus) {
				fssBusinessTradeEntity.setParentId(insertSftpRecord.getId());
				fssBusinessTradeEntity.setStatus("10110002"); //10110001未报备,10110002已报备
				fssBusiTradeService.updateBusitrade(fssBusinessTradeEntity);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.info(getClass(), e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月16日
	 * function：P2P标的财务汇总文件
	 */
	public  void createFinanceSumTXT() throws FssException {
		List<FssFinanceSumEntity> queryFinaSumByStatus = fssBidFinanceService.queryFinaSumByStatus("10110001");
		if(queryFinaSumByStatus==null||queryFinaSumByStatus.size()==0) throw new FssException("标的财务汇总不存在未报备文件");
		String createFinanceSumTXT = createTXT.createFinanceSumTXT(queryFinaSumByStatus);
		try {
			sFTPuploadUtils.upLoadFile("/check/"+CommonUtil.dateTostring(new Date())+"/", createFinanceSumTXT);
			FssSftpRecordEntity insertSftpRecord = fssSftpRecordService.insertSftpRecord("标的财务汇总", queryFinaSumByStatus.size(), "11120006");
			for (FssFinanceSumEntity fssFinanceSumEntity : queryFinaSumByStatus) {
				fssFinanceSumEntity.setParentId(insertSftpRecord.getId());
				fssFinanceSumEntity.setStatus("10110002"); //10110001未报备,10110002已报备
				fssBidFinanceService.updateFinanceSum(fssFinanceSumEntity);
			}
		} catch (Exception e) {
			LogUtil.info(getClass(), e.getMessage());
			e.printStackTrace();
		}

	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月16日
	 * function：P2P项目信息
	 */
	public  void createProjectInfoTXT() throws FssException {
		List<FssProjectInfoEntity> queryItemsInfosByStatus = fssProjectInfoService.queryItemsInfosByStatus("10110001");
		if(queryItemsInfosByStatus==null||queryItemsInfosByStatus.size()==0) throw new FssException("没找到未报备的项目信息文件");
		String createProjectInfoTXT = createTXT.createProjectInfoTXT(queryItemsInfosByStatus);
		try {
			sFTPuploadUtils.upLoadFile("/projectInfo/0001000F0279762/check/", createProjectInfoTXT);
			FssSftpRecordEntity insertSftpRecord = fssSftpRecordService.insertSftpRecord("标的财务汇总", queryItemsInfosByStatus.size(), "11120004");
			for (FssProjectInfoEntity fssProjectInfoEntity : queryItemsInfosByStatus) {
				fssProjectInfoEntity.setParentId(insertSftpRecord.getId());
				fssProjectInfoEntity.setStatus("10110002"); //10110001未报备,10110002已报备
				fssProjectInfoService.updateProjectInfo(fssProjectInfoEntity);
			}
		} catch (Exception e) {
			LogUtil.info(getClass(), e.getMessage());
			e.printStackTrace();
		}

	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月16日
	 * function：P2P项目信息回盘
	 * @throws Exception
	 */
	public  void downBidback() throws FssException,Exception {
		String path = getClassPath();
		File filepath  = new File(path+"/txt/callback/"+CommonUtil.dateTostring(new Date()));
		String fileName=filepath+"/"+"bidBack"+CommonUtil.dateTostring(new Date())+".txt";
		sftpDownLoadutils.downLoadFile("/projectInfo/0001000F0279762/backcheck/"+"P2P_PWXM_BACK_"+CommonUtil.dateTostring(new Date())+".txt",fileName );
		readTXTFile.insertProjectCallBacks(fileName);
	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月16日
	 * function：P2P标的财务汇总审核回盘文件（银行返回）
	 * @throws Exception
	 */
//		public  void downSum() throws Exception {
//			String	filePath="F:\\sumAudit"+CommonUtil.dateTostring(new Date())+".txt";
//			sftpDownLoadutils.downLoadFile("/overcheck/"+CommonUtil.dateTostring(new Date())+"/"+"sum.txt",filePath );
//			readTXTFile.creatSumAudits(filePath);
//
//		}
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
