package com.gqhmt.business.architect.loan.service;

import com.gqhmt.business.architect.invest.entity.ReceivedPayment;
import com.gqhmt.business.architect.invest.mapper.read.ReceivedPaymentReadMapper;
import com.gqhmt.business.architect.loan.bean.RepaymentBean;
import com.gqhmt.business.architect.loan.entity.BidRepayment;
import com.gqhmt.business.architect.loan.mapper.read.BidRepaymentReadMapper;
import com.gqhmt.core.FssException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "oldBidRepaymentService")
public class BidRepaymentService {

	@Autowired
	private BidRepaymentReadMapper bidRepaymentReadMapper;
	
	@Autowired
	private ReceivedPaymentReadMapper receivedPaymentReadMapper;

	
	
	
	public List<BidRepayment> queryRepaymentDetail(BidRepayment bidRepayment) {
		return bidRepaymentReadMapper.select(bidRepayment);
	}
	
	/**
	 * 根据id查找BidRepayment
	 */
	public BidRepayment getRepayMentById(Long id) throws FssException{
		BidRepayment bidRepayment=bidRepaymentReadMapper.selectByPrimaryKey(id);
		return bidRepayment;
	}
	
	/**
	 * 根据BID查找还款计划
	 */
	public BidRepayment getRepayMentByBid(Integer bidId) throws FssException{
		 BidRepayment bidRepayment=bidRepaymentReadMapper.getBidRepayBayId(bidId);
		 return bidRepayment;
	}
	
	public List<RepaymentBean> queryReceivedPaymentByRepaymentIdAndPeriod(Integer bidRepaymentId) {
		BidRepayment bidRepayment = bidRepaymentReadMapper.selectByPrimaryKey(bidRepaymentId);
		List<ReceivedPayment> receivedPaymentList = receivedPaymentReadMapper.queryReceivedPaymentByRepaymentIdAndPeriod(bidRepaymentId, bidRepayment.getPeriod());
		List<RepaymentBean> repaymentBeanList = new ArrayList<RepaymentBean>();
		for (ReceivedPayment receivedPayment : receivedPaymentList) {
			RepaymentBean repaymentBean = new RepaymentBean();
			BeanUtils.copyProperties(receivedPayment, repaymentBean);
			repaymentBeanList.add(repaymentBean);
		}
		return repaymentBeanList;
	}
	
}
