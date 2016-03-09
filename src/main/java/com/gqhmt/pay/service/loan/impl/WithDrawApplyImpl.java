package com.gqhmt.pay.service.loan.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.loan.LoanWithDrawApplyDto;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.pay.service.loan.IWithDrawApply;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Filename:    com.gqhmt.pay.service.loan.impl.LoanImpl
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/3/6 22:52
 * Description:借款人提现
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/3/6  于泳      1.0     1.0 Version
 */
@Service
public class WithDrawApplyImpl implements IWithDrawApply {

	@Resource
	private FundAccountService fundAccountService;
	@Resource
	private FssTradeApplyService fssTradeApplyService;
	
	/**
	 * 借款人提现
	 */
	@Override
	public void createWithDrawApply(LoanWithDrawApplyDto wthDrawApplyDto) throws FssException {
		FssTradeApplyEntity fssTradeApplyEntity=null;
		//1.根据acc_no查询借款人账户信息
	 	FssAccountEntity fssAccountEntity= fundAccountService.getFssFundAccountInfo(wthDrawApplyDto.getAcc_no());
	 	if(fssAccountEntity==null){
	 		throw new FssException("90004006");
	 	}else{//账户余额小于提现金额
	 		if(wthDrawApplyDto.getPay_amt().compareTo(fssAccountEntity.getAccBalance())<0){
	 			throw new FssException("90004007");
	 		}else{
	 			//创建提现申请信息
	 			fssTradeApplyEntity.setApplyNo(fundAccountService.getAccountNo());//时间+随机数
	 			fssTradeApplyEntity.setApplyType(2);
	 			fssTradeApplyEntity.setCustNo(fssAccountEntity.getCustNo());
	 			fssTradeApplyEntity.setUserNo(fssAccountEntity.getUserNo());
	 			fssTradeApplyEntity.setBusinessNo(fssAccountEntity.getBusiNo());
	 			fssTradeApplyEntity.setBusiype(0);
	 			fssTradeApplyEntity.setAccNo(wthDrawApplyDto.getAcc_no());
	 			fssTradeApplyEntity.setTradeAmount(wthDrawApplyDto.getPay_amt());
	 			fssTradeApplyEntity.setRealTradeAmount(wthDrawApplyDto.getPay_amt());
	 			fssTradeApplyEntity.setTradeChargeAmount(BigDecimal.ZERO);
	 			fssTradeApplyEntity.setTradetate(Integer.parseInt(wthDrawApplyDto.getTrade_type()));
	 			fssTradeApplyEntity.setApplyState("0");
	 			fssTradeApplyEntity.setMchnChild(wthDrawApplyDto.getMchn());
	 			fssTradeApplyEntity.setCreateTime((new Timestamp(new Date().getTime())));
	 			fssTradeApplyEntity.setModifyTime((new Timestamp(new Date().getTime())));
	 			fssTradeApplyEntity.setSeqNo(wthDrawApplyDto.getSeq_no());
	 			try {
					fssTradeApplyService.createTradeApply(fssTradeApplyEntity);
				} catch (FssException e) {
					throw new FssException("90099005");
				}
	 		}
	 	}
	}
	
	/**
	 * 完成抵押标借款人提现后，通知借款系统
	 */
	@Override
	public Response withDrasApplyCallBack(String seqNo, String mchn) throws FssException {
		Response response = new Response();
		FssTradeApplyEntity fssTradeApplyEntity=fssTradeApplyService.getTradeApplyByParam(seqNo,mchn);
		if(fssTradeApplyEntity!=null){
			response.setResp_code("0000");
			response.setSeq_no(fssTradeApplyEntity.getSeqNo());
			response.setMchn(fssTradeApplyEntity.getMchnChild());
		}else{
			throw new FssException("90004002");
		}
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
