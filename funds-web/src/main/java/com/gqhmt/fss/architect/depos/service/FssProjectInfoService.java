package com.gqhmt.fss.architect.depos.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.gqhmt.core.util.CommonUtil;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.fss.architect.depos.bean.FssProjectInfoBean;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import org.springframework.stereotype.Service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.depos.entity.FssProjectInfoEntity;
import com.gqhmt.fss.architect.depos.mapper.read.FssProjectInfoReadMapper;
import com.gqhmt.fss.architect.depos.mapper.write.FssProjectInfoWriteMapper;

@Service
public class FssProjectInfoService {

	@Resource
	private FssProjectInfoReadMapper fssProjectInfoReadMapper;
	@Resource
	private FssProjectInfoWriteMapper fssProjectInfoWriteMapper;
	@Resource
	private FundAccountService fundAccountService;

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
	 * function：添加
	 */
	public void updateProjectInfo(FssProjectInfoEntity projectInfo) throws FssException{
		fssProjectInfoWriteMapper.updateByPrimaryKey(projectInfo);
	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月18日
	 * function：创建项目信息并添加进数据库
	 */
	public void createProjectInfo(String seqNo,String itemNo,String loanType,
								  String loanTittle,String organization,String description,
								  Long loanAmt,Long expectedReturn,String productName,
								  String repaymentType,String loanTime,String startDate,
								  Long eachBidAmount,Integer minNum,Long maxAmount,String accNo,
								  String accGoldNo,String loanItemDescription,Long feeType,
								  String status,Integer period,Long prepareAmount,String payChannel,
								  String bidYearIrr,String borrowType,String licenseNo,
								  String custName,String certType,String certNo)throws FssException{
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
		projectInfo.setBorrowType(borrowType);
		projectInfo.setLicenseNo(licenseNo);
		projectInfo.setCustName(custName);
		projectInfo.setCertType(certType);
		projectInfo.setCertNo(certNo);
		projectInfo.setCreateTime(new Date());
		projectInfo.setModifyTime(new Date());
		projectInfo.setCertNo(certNo);
		this.insertProjectInfo(projectInfo);
	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月6日
	 * function：查找所有项目信息
	 */
	public List<FssProjectInfoEntity> queryItemsInfos()throws FssException {
		return fssProjectInfoReadMapper.selectAll();
	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月24日
	 * function：根据状态查询项目信息列表
	 */
	public List<FssProjectInfoEntity> queryItemsInfosByStatus(String status)throws FssException {
		return fssProjectInfoReadMapper.queryItemsInfosByStatus(status);
	}

	/**
	 * jhz
	 * 添加项目信息
	 * @param
     */
	public void insertProjectInfo(String tradeType,String orderNo,String mchnNo,String loanType,
								  String loanTittle,String organization,String description,
								  Long loanAmt,Long expectedReturn,String productName,
								  String repaymentType,String loanTime,String startDate,
								  Long eachBidAmount,Integer minNum,Long maxAmount,
								  String loanItemDescription,Long feeType,
								  String tradeStatus,Integer period,Long prepareAmount,String payChannel,
								  String bidYearIrr,String borrowType,String licenseNo,
								  String custName,String certType,String certNo,String filePath,Integer custId,String busiNo,
								  String contractNO,Long bidInterest) throws  FssException{
		FssProjectInfoBean	fssProjectInfoBean=new FssProjectInfoBean();
		fssProjectInfoBean.setTradeType(tradeType);
		fssProjectInfoBean.setOrderNo(orderNo);
		fssProjectInfoBean.setMchnNo(mchnNo);
		fssProjectInfoBean.setLoanType(loanType);
		fssProjectInfoBean.setLoanTittle(loanTittle);
		fssProjectInfoBean.setOrganization(organization);
		fssProjectInfoBean.setDescription(description);
		fssProjectInfoBean.setLoanAmt(loanAmt);
		fssProjectInfoBean.setExpectedReturn(expectedReturn);
		fssProjectInfoBean.setProductName(productName);
		fssProjectInfoBean.setRepaymentType(repaymentType);
		fssProjectInfoBean.setLoanTime(loanTime);
		fssProjectInfoBean.setStartDate(startDate);
		fssProjectInfoBean.setEachBidAmount(eachBidAmount);
		fssProjectInfoBean.setMinNum(minNum);
		fssProjectInfoBean.setMaxAmount(maxAmount);
		fssProjectInfoBean.setLoanItemDescription(loanItemDescription);
		fssProjectInfoBean.setFeeType(feeType);
		fssProjectInfoBean.setTradeStatus(tradeStatus);
		fssProjectInfoBean.setPeriod(period);
		fssProjectInfoBean.setPrepareAmount(prepareAmount);
		fssProjectInfoBean.setPayChannel(payChannel);
		fssProjectInfoBean.setBidYearIrr(bidYearIrr);
		fssProjectInfoBean.setBorrowType(borrowType);
		fssProjectInfoBean.setLicenseNo(licenseNo);
		fssProjectInfoBean.setCustName(custName);
		fssProjectInfoBean.setCertType(certType);
		fssProjectInfoBean.setCertNo(certNo);
		fssProjectInfoBean.setFilePath(filePath);
		fssProjectInfoBean.setSeqNo(CommonUtil.getSeqNo());
		fssProjectInfoBean.setBusiNo(busiNo);
		fssProjectInfoBean.setContractNo(contractNO);
		fssProjectInfoBean.setBidInterest(bidInterest);
		fssProjectInfoBean.setItemNo(this.getItemNo());
		FundAccountEntity fundAccountEntity=fundAccountService.getFundsAccount(Long.valueOf(custId), GlobalConstants.ACCOUNT_TYPE_LOAN);
		fssProjectInfoBean.setAccNo(fundAccountEntity.getUserName());
		fssProjectInfoBean.setAccGoldNo(fundAccountEntity.getUserName());
		fssProjectInfoBean.setMchn("0001000F0279762");
		fssProjectInfoBean.setStatus("10110001");//10110001未报备，10110002已报备
		fssProjectInfoBean.setCreateTime(new Date());
		fssProjectInfoBean.setModifyTime(new Date());
		fssProjectInfoWriteMapper.insertProjectInfo(fssProjectInfoBean);
	}

	/**
	 * jhz
	 * 得到项目编号
	 * @return
     */
	public String getItemNo(){
		String itemNo=CommonUtil.getItemNo();
		int count=this.getCountByItemNo(itemNo);
		if(count>0){
			this.getItemNo();
		}
		return itemNo;
	}

	/**
	 * jhz
	 * 查询项目编号是否唯一
	 * @param itemNo
	 * @return
     */
	public int getCountByItemNo(String itemNo){
		return  fssProjectInfoReadMapper.getCountByItemNo(itemNo);
	}

}
