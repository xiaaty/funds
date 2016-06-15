package com.gqhmt.fss.architect.depos.txt;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.depos.entity.FssProjectCallbackEntity;
import com.gqhmt.fss.architect.depos.entity.FssSftpRecordEntity;
import com.gqhmt.fss.architect.depos.entity.FssSumAuditEntity;
import com.gqhmt.fss.architect.depos.service.FssProjectInfoCallBackService;
import com.gqhmt.fss.architect.depos.service.FssDeposRecordService;
import com.gqhmt.fss.architect.depos.service.FssSumAuditService;
@Service
public class ReadTXTFile {

	private FssProjectInfoCallBackService fssProjectInfoCallBackService;
	@Resource
	private FssSumAuditService fssSumAuditService;
	@Resource
	private FssDeposRecordService fssDeposRecordService;

	/**
	 *
	 * author:jhz
	 * time:2016年5月16日
	 * function：P2P项目信息回盘(银行返回)
	 */
	public  void insertProjectCallBacks(String filePath) throws Exception {
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), "GBK");
			BufferedReader br = new BufferedReader(isr);
			String r= br.readLine();
			FssProjectCallbackEntity projectCallback=null;
			List<FssProjectCallbackEntity> list=new ArrayList<FssProjectCallbackEntity>();
			while(r!=null){
				projectCallback=new FssProjectCallbackEntity();
				String str[]=r.split("\\|");
				projectCallback.setItemNo(str[0]);
				projectCallback.setItemName(str[1]);
				projectCallback.setPayChannel(str[2]);
				projectCallback.setStatus(str[3]);
				projectCallback.setRespCode(str[4]);
				projectCallback.setRespMsg(str[5]);
				projectCallback.setBidId(str[6]);
				projectCallback.setFailedMsg(str[7]);
				list.add(projectCallback);
				System.out.println(r);
				r=br.readLine();
			}
//    		System.out.println("总共有"+list.size()+"条数据!");
			for (FssProjectCallbackEntity fssProjectCallbackEntity : list) {
//				System.out.println(fssProjectCallbackEntity.getItemName()+"--------------");
			}
			fssProjectInfoCallBackService.createProjectInfo(list);
			//创建sftp下载记录
			FssSftpRecordEntity insertSftpRecord = fssDeposRecordService.insertSftpRecord("项目信息回盘", list.size(), "11120005");
			for (FssProjectCallbackEntity fssProjectCallbackEntity : list) {
				fssProjectCallbackEntity.setParentId(insertSftpRecord.getId());
				fssProjectInfoCallBackService.update(fssProjectCallbackEntity);
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
	 * function：P2P标的财务汇总审核回盘文件（银行返回）
	 */
	public  void creatSumAudits(String filePath) throws Exception {
		try {
			BufferedReader br=new BufferedReader(new FileReader(filePath));
			String r= br.readLine();
			int count=0;
			FssSumAuditEntity sumAudit=null;
			List<FssSumAuditEntity> list=new ArrayList<FssSumAuditEntity>();
			while(r!=null){
				sumAudit=new FssSumAuditEntity();
				count++;
				if(count != 1 && count != 2){
					String str[]=r.split("|");
					sumAudit.setOrgTargetId(str[0]);
					sumAudit.setOrgTerraceId(str[1]);
					sumAudit.setCustNo(str[2]);
					sumAudit.setCertType(str[3]);
					sumAudit.setCustName(str[4]);
					sumAudit.setCertNo(str[5]);
					sumAudit.setTargetState(str[6]);
					sumAudit.setTenderTime(str[7]);
					sumAudit.setFullScaleTime(str[8]);
					sumAudit.settReCaptical(new BigDecimal(str[9]));
					sumAudit.settReInterest(new BigDecimal(str[10]));
					sumAudit.setlRepaymentTime(str[11]);
					sumAudit.setaSquareTime(str[12]);
					sumAudit.setaReCaptical(new BigDecimal(str[13]));
					sumAudit.setaReInterest(new BigDecimal(str[14]));
					sumAudit.setTodayReCaptical(new BigDecimal(str[15]));
					sumAudit.setTodayReInterest(new BigDecimal(str[16]));
					sumAudit.seteReCaptical(new BigDecimal(str[17]));
					sumAudit.seteReInterest(new BigDecimal(str[18]));
					sumAudit.setPaidSum(new BigDecimal(str[19]));
					sumAudit.setDebtSum(new BigDecimal(str[20]));
					sumAudit.setCreditSum(new BigDecimal(str[21]));
					sumAudit.settCreditSum(new BigDecimal(str[22]));
					sumAudit.setAuditState(str[23]);
					sumAudit.setFreezeSum(new BigDecimal(str[24]));
					list.add(sumAudit);
				}
				System.out.println(r);
				r=br.readLine();
			}
			System.out.println("总共有"+list.size()+"条数据!");
			for (FssSumAuditEntity sum : list) {
				System.out.println(sum.getaSquareTime()+"--------------");
			}
			fssSumAuditService.createSumAudit(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
