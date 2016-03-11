package com.gqhmt.business.architect.loan.mapper.read;

import java.util.List;

import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.core.mybatis.ReadMapper;

/**
 * @author fanfever
 * @date 2015年1月15日
 * @time 下午3:50:22
 * 
 * @description
 */
public interface TenderReadMapper extends ReadMapper<Tender> {
	public List<Tender> getTenderByBidId(Long bidId);
	/**
	 * 
	 * author:jhz
	 * time:2016年3月10日
	 * function：通过标的ID得到产品名称
	 */
	public String getProductName(Integer bidId);
}
