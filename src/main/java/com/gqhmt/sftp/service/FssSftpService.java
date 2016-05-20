package com.gqhmt.sftp.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.sftp.csv.CreateTXT;
import com.gqhmt.sftp.csv.ReadTXTFile;
import com.gqhmt.sftp.utils.SFTPDownLoadutils;
import com.gqhmt.sftp.utils.SFTPuploadUtils;
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
	
	/**
	 * 
	 * author:jhz
	 * time:2016年5月16日
	 * function：1.P2P个人平台开户文件
	 */
	    public  void createAccount() throws FssException {
			String createAccountFileTXT = createTXT.createAccountFileTXT();
			try {
				sFTPuploadUtils.upLoadFile("/projectInfo/0001000F0279762/check", createAccountFileTXT);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage()+"-----");
			}
			
	 }
		/**
		 * 
		 * author:jhz
		 * time:2016年5月16日
		 * function：P2P商户交易（包括冻结，动账，解冻）TXT文件
		 */
		public  void createCreditInfoTxt() throws FssException {
			String createCreditInfoCVS = createTXT.createCreditInfoTXT();
			try {
				sFTPuploadUtils.upLoadFile("/projectInfo/0001000F0279762/check", createCreditInfoCVS);
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
			 String createFinanceSumTXT = createTXT.createFinanceSumTXT();
			try {
				sFTPuploadUtils.upLoadFile("/check/"+CommonUtil.dateTostring(new Date())+"/", createFinanceSumTXT);
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
			String createProjectInfoTXT = createTXT.createProjectInfoTXT();
			try {
				sFTPuploadUtils.upLoadFile("/projectInfo/0001000F0279762/check/", createProjectInfoTXT);
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
		public  void downBidback() throws Exception {
		String	filePath="F:\\bidBack"+CommonUtil.dateTostring(new Date())+".txt";
			sftpDownLoadutils.downLoadFile("/projectInfo/0001000F0279762/backcheck/"+"P2P_PWXM_BACK_"+CommonUtil.dateTostring(new Date())+".txt",filePath );
			readTXTFile.insertProjectCallBacks(filePath);
			
		}
		/**
		 * 
		 * author:jhz
		 * time:2016年5月16日
		 * function：P2P标的财务汇总审核回盘文件（银行返回）
		 * @throws Exception 
		 */
		public  void downSum() throws Exception {
			String	filePath="F:\\sumAudit"+CommonUtil.dateTostring(new Date())+".txt";
			sftpDownLoadutils.downLoadFile("/overcheck/"+CommonUtil.dateTostring(new Date())+"/"+"sum.txt",filePath );
			readTXTFile.creatSumAudits(filePath);
			
		}
	
}
