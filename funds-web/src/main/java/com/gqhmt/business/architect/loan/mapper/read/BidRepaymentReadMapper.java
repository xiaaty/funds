package com.gqhmt.business.architect.loan.mapper.read;

import org.springframework.web.bind.annotation.PathVariable;

import com.gqhmt.business.architect.loan.entity.BidRepayment;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.mybatis.ReadMapper;
public interface BidRepaymentReadMapper extends ReadMapper<BidRepayment> {
	public BidRepayment getBidRepayBayId(@PathVariable Integer bidId)throws FssException;
}
