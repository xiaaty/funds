package com.gqhmt.business.architect.loan.mapper.read;

import java.util.List;

import com.gqhmt.business.architect.loan.entity.ReceivedPayment;
import com.gqhmt.core.mybatis.ReadMapper;

public interface ReceivedPaymentReadMapper extends ReadMapper<ReceivedPayment> {

	public List<ReceivedPayment> queryReceivedPaymentByRepaymentIdAndPeriod(int repaymentId, int period);
	
	public List<ReceivedPayment> queryReceivedPaymentByRepaymentIdAndPeriodAndInvestmentId(int repaymentId, int period, int investmentId);
	
	public List<ReceivedPayment> queryReceivedPaymentByInvestIdAndPeriod (int investId, int period);
	
}
