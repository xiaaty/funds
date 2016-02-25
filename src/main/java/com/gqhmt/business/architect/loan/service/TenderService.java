package com.gqhmt.business.architect.loan.service;

import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.business.architect.loan.mapper.read.TenderReadMapper;
import com.gqhmt.business.architect.loan.mapper.write.TenderWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fanfever
 * @date 2015年1月15日
 * @time 下午3:50:32
 */
@Service
public class TenderService {
	
	@Autowired
	private TenderReadMapper tenderReadMapper;
	@Autowired
	private TenderWriteMapper tenderWriteMapper;
	
	public Tender selectOne(Tender tender) {
		return tenderReadMapper.selectOne(tender);
	}
	
	public List<Tender> select(Tender tender) {
		return tenderReadMapper.select(tender);
	}
	
	public void update(Tender tender) {
		tenderWriteMapper.updateByPrimaryKeySelective(tender);
	}

	public Tender findById(long id) {
		Tender tender = tenderReadMapper.selectByPrimaryKey(id);
		return tender;
	}
	
	public List<Tender> queryTenderByBidId(Long bidId){
		return tenderReadMapper.getTenderByBidId(bidId);
	}
	
	
	
	
	
	
	
}
