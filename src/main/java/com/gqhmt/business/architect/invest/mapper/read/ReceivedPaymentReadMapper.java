package com.gqhmt.business.architect.invest.mapper.read;

import com.gqhmt.business.architect.invest.entity.ReceivedPayment;
import com.gqhmt.core.mybatis.ReadMapper;

import java.util.List;

public interface ReceivedPaymentReadMapper extends ReadMapper<ReceivedPayment> {

	public List<ReceivedPayment> queryReceivedPaymentByRepaymentIdAndPeriod(int repaymentId, int period);
	
	public List<ReceivedPayment> queryReceivedPaymentByRepaymentIdAndPeriodAndInvestmentId(int repaymentId, int period, int investmentId);
	
	public List<ReceivedPayment> queryReceivedPaymentByInvestIdAndPeriod(int investId, int period);
	
}
