package com.gqhmt.business.architect.loan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.mapper.read.BidReadMapper;

/**
 * 标的管理
 * 
 * @author yuankang
 */
@Service
public class BidService {

	@Autowired
	private BidReadMapper bidReadMapper;

	/**
	 * 标的列表
	 * 
	 * @param id
	 * @param id
	 * @return
	 */

	public Bid findById(Long id) {
		Bid bid = bidReadMapper.selectByPrimaryKey(id);
		return bid;
	}
	
	public Page querylist(Bid bid) {
		return bidReadMapper.querylist(bid);
	}

    /**
     * 查询合同编号是否存在
     * 
     * @param customerId
     * @param bidId
     */
    public Long queryUserBidInfo(String customerId, String bidId, String mortgageNumber, String loanType) {
    	return bidReadMapper.queryUserBidInfo(customerId, bidId, mortgageNumber, loanType);
    }

}
