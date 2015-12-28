package com.gqhmt.business.architect.loan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gqhmt.business.architect.loan.entity.BidRepayment;
import com.gqhmt.business.architect.loan.mapper.read.BidRepaymentReadMapper;

@Service
public class BidRepaymentService {

	@Autowired
	private BidRepaymentReadMapper bidRepaymentReadMapper;

	public List<BidRepayment> queryRepaymentDetail(BidRepayment bidRepayment) {
		return bidRepaymentReadMapper.select(bidRepayment);
	}
}
