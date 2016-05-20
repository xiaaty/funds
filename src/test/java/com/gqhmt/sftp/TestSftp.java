package com.gqhmt.sftp;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gqhmt.TestService;
import com.gqhmt.core.FssException;
import com.gqhmt.sftp.csv.CreateTXT;
import com.gqhmt.sftp.csv.ReadTXTFile;
import com.gqhmt.sftp.utils.SFTPDownLoadutils;
import com.gqhmt.sftp.utils.SFTPuploadUtils;
import com.gqhmt.util.CommonUtil;

public class TestSftp extends TestService{
	@Resource
	private SFTPuploadUtils sFTPuploadUtils;
	@Resource
	private CreateTXT createTXT;
	@Resource
	private SFTPDownLoadutils sftpDownLoadutils;
	@Resource
	private ReadTXTFile readTXTFile;
	
	private static int num=1000;
	/**
	 * 
	 * author:jhz
	 * time:2016年5月16日
	 * function：1.P2P个人平台开户文件
	 */
		@Test
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
		@Test
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
		@Test
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
		@Test
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
		@Test
		public  void downBidback() throws Exception {
		String	filePath="F:\\bidBack"+CommonUtil.dateTostring(new Date())+".txt";
			sftpDownLoadutils.downLoadFile("/projectInfo/0001000F0279762/backcheck/"+"P2P_PWXM_BACK_"+CommonUtil.dateTostring(new Date())+".txt",filePath );
			readTXTFile.insertProjectCallBacks(filePath);
			
		}
		/**
		 * 
		 * author:jhz
		 * time:2016年5月16日
		 * function：7.P2P标的财务汇总审核回盘文件（银行返回）
		 * @throws Exception 
		 */
		@Test
		public  void downSum() throws Exception {
			String	filePath="F:\\sumAudit"+CommonUtil.dateTostring(new Date())+".txt";
			sftpDownLoadutils.downLoadFile("/overcheck/"+CommonUtil.dateTostring(new Date())+"/"+"sum.txt",filePath );
			readTXTFile.creatSumAudits(filePath);
			
		}
		public String process(MultipartFile file, HttpServletRequest req, HttpServletResponse res) {
			try {
				String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
				String path = req.getRealPath("/") + File.separator + "upload" + File.separator + UUID.randomUUID() + fileSuffix;
				file.transferTo(new File(path));
				MultipartHttpServletRequest multiPartReq = (MultipartHttpServletRequest) req;
				return path;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}

}
