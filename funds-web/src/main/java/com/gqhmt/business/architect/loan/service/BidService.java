package com.gqhmt.business.architect.loan.service;

import com.github.pagehelper.Page;
import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.mapper.read.BidReadMapper;
import com.gqhmt.business.architect.loan.mapper.read.TenderReadMapper;
import com.gqhmt.business.architect.loan.mapper.write.BidWriteMapper;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标的管理
 * 
 * @author yuankang
 */
@Service
public class BidService {

	@Autowired
	private BidReadMapper bidReadMapper;
	
	@Autowired
	private BidWriteMapper bidWriteMapper;
	
	@Autowired
	private TenderReadMapper tenderReadMapper;

	/**
	 * 标的列表
	 * 
	 * @param id
	 * @param id
	 * @return
	 */

	public Bid findById(Integer id) {
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

    /**
     * 根据标的Id查询产品名称
     * @param bidId
     * @return
     */
    public String getProductName(Integer bidId){
    	String title=tenderReadMapper.getProductName(bidId);
    	return title;
    }
    
    /**
     * 修改标的状态
     * @param bid
     */
    public void updateBid(Bid bid){
    	bidWriteMapper.updateByPrimaryKeySelective(bid);
    }

	public int queryBidByCustId(Integer custId){
		Bid bid=new Bid();
		bid.setCustomerId(custId);
		int res = bidReadMapper.selectCount(bid);
		return res;
	}

	public List<Bid> queryBidByDate(String date){
		return bidReadMapper.queryBidByDate(date);
	}
    
    
	/**
	 * 根据合同号查询标的
	 * @param contractNo
	 * @return
	 */
    public Bid getBidByContractNo(String contractNo)  {
		Bid bid =  bidReadMapper.getBidByContractNo(contractNo);
		if(bid == null ) {
			LogUtil.error(this.getClass(),90002045);
		}
		return bid;
	}
}
