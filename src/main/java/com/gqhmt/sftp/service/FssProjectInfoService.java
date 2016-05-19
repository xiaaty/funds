package com.gqhmt.sftp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.sftp.entity.FssProjectInfoEntity;
import com.gqhmt.sftp.mapper.read.FssProjectInfoReadMapper;
import com.gqhmt.sftp.mapper.write.FssProjectInfoWriteMapper;

@Service
public class FssProjectInfoService {
	
	@Resource
	private FssProjectInfoReadMapper fssProjectInfoReadMapper;
	@Resource
	private FssProjectInfoWriteMapper fssProjectInfoWriteMapper;
	
	/**
	 * 项目信息列表
	 * @param map
	 * @return
	 */
	public List<FssProjectInfoEntity> queryFssProjectList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			map2.put("mchn",map.get("mchn"));
			map2.put("seqNo", map.get("seqNo"));
			map2.put("itemNo", map.get("itemNo"));
		}
		return fssProjectInfoReadMapper.queryFssProjectInfoList(map2);
	}
	/**
	 * 批量插入项目信息
	 * @param projectlist
	 * @throws FssException
	 */
	public void createProjectInfo(List<FssProjectInfoEntity> projectlist) throws FssException{
		fssProjectInfoWriteMapper.insertList(projectlist);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月18日
	 * function：添加
	 */
	public void insertProjectInfo(FssProjectInfoEntity projectInfo) throws FssException{
		fssProjectInfoWriteMapper.insertSelective(projectInfo);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月18日
	 * function：创建项目信息并添加进数据库
	 */
	public void createProjectInfo(String seqNo,String itemNo,String loanType,String loanTittle,String organization,String description,
			Long loanAmt,Long expectedReturn,String productName,String repaymentType,String loanTime,String startDate,Long eachBidAmount,Integer minNum,
			Long maxAmount,String accNo,String accGoldNo,String loanItemDescription,Long feeType,String status,Integer period,Long prepareAmount,String payChannel,String bidYearIrr,String custName,String certType,String certNo)throws FssException{
		FssProjectInfoEntity projectInfo=new FssProjectInfoEntity();
		projectInfo.setMchn("0001000F0279762");
		projectInfo.setSeqNo(seqNo);
		projectInfo.setItemNo(itemNo);
		projectInfo.setLoanType(loanType);
		projectInfo.setLoanTittle(loanTittle);
		projectInfo.setOrganization(organization);
		projectInfo.setDescription(description);
		projectInfo.setLoanAmt(loanAmt);
		projectInfo.setExpectedReturn(expectedReturn);
		projectInfo.setProductName(productName);
		projectInfo.setRepaymentType(repaymentType);
		projectInfo.setLoanTime(loanTime);
		projectInfo.setStartDate(startDate);
		projectInfo.setEachBidAmount(eachBidAmount);
		projectInfo.setMinNum(minNum);
		projectInfo.setMaxAmount(maxAmount);
		projectInfo.setAccNo(accNo);
		projectInfo.setAccGoldNo(accGoldNo);
		projectInfo.setLoanItemDescription(loanItemDescription);
		projectInfo.setFeeType(feeType);
		projectInfo.setStatus(status);
		projectInfo.setPeriod(period);
		projectInfo.setPrepareAmount(prepareAmount);
		projectInfo.setPayChannel(payChannel);
		projectInfo.setBidYearIrr(bidYearIrr);
		projectInfo.setCustName(custName);
		projectInfo.setCertType(certType);
		projectInfo.setCertNo(certNo);
		this.insertProjectInfo(projectInfo);
	}
}
