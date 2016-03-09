package com.gqhmt.pay.service.loan.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.loan.RepaymentDto;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentEntity;
import com.gqhmt.fss.architect.trade.service.FssRepaymentService;
import com.gqhmt.pay.service.loan.IRePayment;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/3/6  于泳      1.0     1.0 Version
 */
@Service
public class RePaymentImpl implements IRePayment {

	@Resource
	private FssRepaymentService fssRepaymentService;
    
	/**
	 * 还款划扣
	 */
    @Override
    public boolean createRefundDraw(List<RepaymentDto> repaymentlist) throws FssException {
    	List<FssRepaymentEntity> fssRepaymentlist=new ArrayList<FssRepaymentEntity>();
    	
    	if(repaymentlist==null || repaymentlist.size()==0){
    		throw new FssException("还款信息为空！");
    	}else{
	    	for(RepaymentDto dto:repaymentlist){
	    		FssRepaymentEntity repaymentEntity = new FssRepaymentEntity();
	    		repaymentEntity.setAccNo(dto.getAcc_no());
	    		repaymentEntity.setTradeType(dto.getTrade_type());
	    		repaymentEntity.setSignature(dto.getSignature());
	    		repaymentEntity.setCreateTime((new Timestamp(new Date().getTime())));
	    		repaymentEntity.setCreateUserId(0);
	    		repaymentEntity.setModifyTime((new Timestamp(new Date().getTime())));
	    		repaymentEntity.setMotifyUserId(0);
	    		repaymentEntity.setAmt(dto.getAmt());
	    		repaymentEntity.setState("0");
	    		repaymentEntity.setSeqNo(dto.getSeq_no());
	    		repaymentEntity.setSerialNumber(dto.getSerial_number());
	    		repaymentEntity.setContractId(dto.getContract_id());
	    		repaymentEntity.setMchnParent("");
	    		repaymentEntity.setMchnChild(dto.getMchn());
	    		repaymentEntity.setRemark(dto.getRemark());
	    		repaymentEntity.setFailReson("");
	    		fssRepaymentlist.add(repaymentEntity);
	    	}
    	}
    	try {
			fssRepaymentService.createRepayments(fssRepaymentlist);
		} catch (FssException e) {
			throw new FssException("还款划扣失败！");
		}
    	return true;
    }
    
    /**
	 * 还款划扣通知
	 */
    public List<FssRepaymentEntity> rePaymentCallBack(String seqNo,String mchn) throws FssException{
    	List<FssRepaymentEntity> repaymentlist=null;
    	repaymentlist=fssRepaymentService.searRepaymentByparam(seqNo,mchn);
    	if(repaymentlist==null){
    		throw new FssException("还款划扣失败");
    	}
    	return repaymentlist;
    }
    
}
