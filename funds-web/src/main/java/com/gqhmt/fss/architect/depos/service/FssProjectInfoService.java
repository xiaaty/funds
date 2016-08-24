package com.gqhmt.fss.architect.depos.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.Resource;

import com.gqhmt.core.util.CommonUtil;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.depos.bean.FssProjectInfoBean;
import com.gqhmt.fss.architect.depos.entity.FssProjectCallbackEntity;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.core.configer.Config;
import com.gqhmt.pay.core.factory.ConfigFactory;
import com.gqhmt.util.DateUtil;
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
	@Resource
	private FssProjectInfoCallBackService fssProjectInfoCallBackService;

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
		projectInfo.setStatus("10110001");//10110001未报备，10110002已报备
		projectInfo.setCreateTime(new Date());
		projectInfo.setModifyTime(new Date());
		fssProjectInfoWriteMapper.insertProjectInfo(projectInfo);
	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月18日
	 * function：修改
	 */
	public void updateProjectInfo(FssProjectInfoEntity projectInfo) throws FssException{
		projectInfo.setStatus("10110001");//10110001未报备，10110002已报备
		projectInfo.setModifyTime(new Date());
		projectInfo.setAttachStatus("N");
		fssProjectInfoWriteMapper.updateByPrimaryKey(projectInfo);
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
								  String contractNO,Long bidInterest) throws  FssException {
		String itemNo = CommonUtil.getItemNo(contractNO);
		FssProjectInfoEntity fssProjectInfoBean = this.getByItemNo(itemNo);
		if (fssProjectInfoBean == null) {
			fssProjectInfoBean = new FssProjectInfoEntity();
		}
		Config config = ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU);
		String mchn = (String) config.getValue("public.mchnt_cd.value");
		String loanTimes = (String) config.getValue("sftp.loanTime.value");
		String payChannels = (String) config.getValue("sftp.payChannel.value");
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
		fssProjectInfoBean.setStartDate(startDate);

		if (startDate == null || startDate.equals("")) {
			throw new FssException("投标起始日期为空");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(startDate);
		} catch (ParseException e) {
			LogUtil.debug(e.getClass(), e.getMessage());
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, inputDayOfYear + Integer.parseInt(loanTimes));
		String pioDate = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
		fssProjectInfoBean.setLoanTime(pioDate);

		fssProjectInfoBean.setEachBidAmount(eachBidAmount);
		fssProjectInfoBean.setMinNum(minNum);
		fssProjectInfoBean.setMaxAmount(maxAmount);
		fssProjectInfoBean.setLoanItemDescription(loanItemDescription);
		fssProjectInfoBean.setFeeType(feeType);
		fssProjectInfoBean.setTradeStatus(tradeStatus);
		fssProjectInfoBean.setPeriod(period);
		fssProjectInfoBean.setPrepareAmount(prepareAmount);
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
		FundAccountEntity fundAccountEntity = fundAccountService.getFundsAccount(Long.valueOf(custId), GlobalConstants.ACCOUNT_TYPE_LOAN);
		fssProjectInfoBean.setAccNo(fundAccountEntity.getUserName());
		fssProjectInfoBean.setAccGoldNo(fundAccountEntity.getUserName());


		if (fssProjectInfoBean.getId() != null) {
			FssProjectCallbackEntity fssProjectCallbackEntity = fssProjectInfoCallBackService.getByItemNo(fssProjectInfoBean.getItemNo());
			if (fssProjectCallbackEntity == null) throw new FssException("90004011");
			if ("R".equals(fssProjectCallbackEntity.getStatus())) {
				this.updateProjectInfo(fssProjectInfoBean);
			} else {
				throw new FssException("90004011");
			}
		} else {
			fssProjectInfoBean.setItemNo(itemNo);
			this.insertProjectInfo(fssProjectInfoBean);
		}
	}



	/**
	 * jhz
	 * 查询项目编号是否唯一
	 * @param itemNo
	 * @return
     */
	public FssProjectInfoEntity getByItemNo(String itemNo)throws  FssException{
		return  fssProjectInfoReadMapper.getByItemNo(itemNo);
	}

}
