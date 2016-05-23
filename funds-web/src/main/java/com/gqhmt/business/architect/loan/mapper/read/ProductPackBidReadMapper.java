package com.gqhmt.business.architect.loan.mapper.read;

import com.gqhmt.business.architect.loan.entity.ProductPackBidMapEntity;
import com.gqhmt.business.architect.loan.entity.ProductSalesPackEntity;
import com.gqhmt.core.mybatis.ReadMapper;

public interface ProductPackBidReadMapper extends ReadMapper<ProductPackBidMapEntity> {

	/**
	 * 根据标的 的id 获取 packid
	 * @param bidId
	 * @return
	 */
	public Integer queryPackIdByBidId(Integer bidId);
	
	/**
	 * 通过packId查询
	 * @param packId
	 * @return
	 */
	public ProductSalesPackEntity queryPackById(Integer packId);
}
