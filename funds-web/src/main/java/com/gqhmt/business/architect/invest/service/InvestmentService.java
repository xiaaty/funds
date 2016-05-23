package com.gqhmt.business.architect.invest.service;

import com.gqhmt.business.architect.invest.entity.InvestmentInfo;
import com.gqhmt.business.architect.invest.mapper.read.InvestmentReadMapper;
import com.gqhmt.business.architect.invest.mapper.write.InvestmentWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * com.gq.p2p
 * @className InvestmentService<br/> 
 * @author 
 * @createDate 2015-01-14 上午10:42:21<br/>
 * @version 1.0 <br/>
 */
@Service
public class InvestmentService {
	
	@Autowired
	private InvestmentReadMapper investmentReadMapper;
	@Autowired
	private InvestmentWriteMapper investmentWriteMapper;
	
	public InvestmentInfo select(InvestmentInfo investmentInfo) {
		return investmentReadMapper.selectOne(investmentInfo);
	}
	public void update(InvestmentInfo investmentInfo) {
		investmentWriteMapper.updateByPrimaryKeySelective(investmentInfo);
	}
	public void delete(Long id) {
		investmentWriteMapper.deleteByPrimaryKey(id);
	}
	
	public int queryByCustId(Integer custId){
		InvestmentInfo investmentInfo=new InvestmentInfo();
		investmentInfo.setCustId(custId);
		int res = investmentReadMapper.selectCount(investmentInfo);
		return res;
	}
	
	
}
