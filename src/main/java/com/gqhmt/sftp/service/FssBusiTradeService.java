package com.gqhmt.sftp.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.core.FssException;
import com.gqhmt.sftp.entity.FssBusinessTradeEntity;
import com.gqhmt.sftp.mapper.read.FssBusiTradeReadMapper;
import com.gqhmt.sftp.mapper.write.FssBusiTradeWriteMapper;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年5月12日
 * Description:
 * <p>p2p商户交易
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月12日 柯禹来      1.0     1.0 Version
 */
@Service
public class FssBusiTradeService {
	@Resource
	private FssBusiTradeReadMapper fssBusiTradeReadMapper;
	@Resource
	private FssBusiTradeWriteMapper fssBusiTradeWriteMapper;
	
	public List<FssBusinessTradeEntity> queryBusinessTrade(Map<String,String> map)throws FssException {
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			map2.put("mchn",map.get("mchn"));
			map2.put("itemNo", map.get("itemNo"));
		}
		return fssBusiTradeReadMapper.queryBusiTradeList(map);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月13日
	 * function：查询所有商户交易对象
	 */
	public List<FssBusinessTradeEntity> queryBusinessTrade()throws FssException {
		return fssBusiTradeReadMapper.selectAll();
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月18日
	 * function：添加商户交易对象
	 */
	public void insertBusitrade(FssBusinessTradeEntity fssBusinessTradeEntity){
		fssBusiTradeWriteMapper.insertSelective(fssBusinessTradeEntity);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月18日
	 * function：创建商户交易对象并添加进数据库
	 */
	public void creatBusiTrade(String thirdPartyPaymentId,String tradeDate,String tradeType,String itemNo,String contractNo,String outFuiouUsername,String outPlatformUsername,BigDecimal amt,BigDecimal charge,BigDecimal thisRepaymentPrincipal,BigDecimal thisRepaymentInterest,
			String comeFuiouUsername,String comePlatformUsername,String loanUsername,String loanCertType,String loanCertNo,String lendUsername,String lendFuiouUsername,String lendName,String lendCertType,String lendCertNo,String busiType){
		FssBusinessTradeEntity busiTrade=new FssBusinessTradeEntity();
		busiTrade.setMchn("0001000F0279762");
		busiTrade.setThirdPartyPaymentId(thirdPartyPaymentId);
		busiTrade.setTradeDate(tradeDate);
		busiTrade.setTradeType(tradeType);
		busiTrade.setItemNo(itemNo);
		busiTrade.setContractNo(contractNo);
		busiTrade.setOutFuiouUsername(outFuiouUsername);
		busiTrade.setOutPlatformUsername(outPlatformUsername);
		busiTrade.setAmt(amt);
		busiTrade.setCharge(charge);
		busiTrade.setThisRepaymentPrincipal(thisRepaymentPrincipal);
		busiTrade.setThisRepaymentInterest(thisRepaymentInterest);
		busiTrade.setComeFuiouUsername(comeFuiouUsername);
		busiTrade.setComePlatformUsername(comePlatformUsername);
		busiTrade.setLoanUsername(loanUsername);
		busiTrade.setLoanCertType(loanCertType);
		busiTrade.setLoanCertNo(loanCertNo);
		busiTrade.setLendUsername(lendUsername);
		busiTrade.setLendFuiouUsername(lendFuiouUsername);
		busiTrade.setLendName(lendName);
		busiTrade.setLendCertType(lendCertType);
		busiTrade.setLendCertNo(lendCertNo);
		busiTrade.setBusiType(busiType);
		this.insertBusitrade(busiTrade);
	}
}
