package com.gqhmt.sftp.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.sftp.entity.FssFinanceSumEntity;
import com.gqhmt.sftp.mapper.read.FssFinanceSumReadMapper;
import com.gqhmt.sftp.mapper.write.FssBidFinanceWriteMapper;

@Service
public class FssBidFinanceService {

	@Resource
	private FssFinanceSumReadMapper fssFinanceReadMapper;
	@Resource
	private FssBidFinanceWriteMapper fssBidFinanceWriteMapper;

	public List<FssFinanceSumEntity> queryBidFinanceList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			map2.put("custNo",map.get("custNo"));
			map2.put("custName", map.get("custName"));
			map2.put("certNo", map.get("certNo"));
		}
		return fssFinanceReadMapper.queryFssFinanceSumList(map2);
	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月19日
	 * function：添加
	 */
	public void insertFinanceSum(FssFinanceSumEntity fssFinanceSumEntity){
		fssBidFinanceWriteMapper.insertSelective(fssFinanceSumEntity);
	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月19日
	 * function：修改
	 */
	public void updateFinanceSum(FssFinanceSumEntity fssFinanceSumEntity){
		fssBidFinanceWriteMapper.updateByPrimaryKey(fssFinanceSumEntity);
	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月18日
	 * function：创建标的财务汇总文件对象并添加金数据库
	 */
	public void creatAccountFile(String orgTargetId,String orgTerraceId,
								 String custNo,String custName,String certType,String certNo,String targetState,
								 String tenderTime,String fullScaleTime,BigDecimal tReCaptical,BigDecimal tReInterest,String lReTime,
								 String aSquareTime ,BigDecimal aReCaptical,BigDecimal aReInterest,BigDecimal todayReCaptical,BigDecimal todayReInterest,
								 BigDecimal eReCaptical,BigDecimal eReInterest,BigDecimal paidSum,BigDecimal debtSum,BigDecimal tCreditSum) throws FssException{
		FssFinanceSumEntity financeSum=new FssFinanceSumEntity();
		financeSum.setOrgTargetId(orgTargetId);
		financeSum.setOrgTerraceId(orgTerraceId);
		financeSum.setCustNo(custNo);
		financeSum.setCustName(custName);
		financeSum.setCertType(certType);
		financeSum.setCertNo(certNo);
		financeSum.setTargetState(targetState);
		financeSum.setTenderTime(tenderTime);
		financeSum.setFullScaleTime(fullScaleTime);
		financeSum.settReCaptical(tReCaptical);
		financeSum.settReInterest(tReInterest);
		financeSum.setlReTime(lReTime);
		financeSum.setaSquareTime(aSquareTime);
		financeSum.setaReCaptical(aReCaptical);
		financeSum.setaReInterest(aReInterest);
		financeSum.setTodayReCaptical(todayReCaptical);
		financeSum.setTodayReInterest(todayReInterest);
		financeSum.seteReCaptical(eReCaptical);
		financeSum.seteReInterest(eReInterest);
		financeSum.setPaidSum(paidSum);
		financeSum.setDebtSum(debtSum);
		financeSum.settCreditSum(tCreditSum);
		this.insertFinanceSum(financeSum);
	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月11日
	 * function：标的财务汇总文件表
	 */
	public List<FssFinanceSumEntity> queryFinaSum()throws FssException {
		return fssFinanceReadMapper.selectAll();
	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月24日
	 * function：根据交易状态查询文件列表
	 */
	public List<FssFinanceSumEntity> queryFinaSumByStatus(String status)throws FssException {
		return fssFinanceReadMapper.queryFinaSumByStatus(status);
	}
}
