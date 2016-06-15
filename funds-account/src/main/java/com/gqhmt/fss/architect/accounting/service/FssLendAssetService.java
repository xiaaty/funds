package com.gqhmt.fss.architect.accounting.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.accounting.entity.FssLendAssetDetailEntity;
import com.gqhmt.fss.architect.accounting.entity.FssLendAssetEntity;
import com.gqhmt.fss.architect.accounting.mapper.read.FssLendAssetDetailReadMapper;
import com.gqhmt.fss.architect.accounting.mapper.read.FssLendAssetReadMapper;
import com.gqhmt.fss.architect.accounting.mapper.write.FssLendAssetDetailWriteMapper;
import com.gqhmt.fss.architect.accounting.mapper.write.FssLendAssetWriteMapper;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年4月28日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年4月28日  jhz      1.0     1.0 Version
 */
@Service
public class FssLendAssetService {
	
	@Resource
	private FssLendAssetReadMapper fssLendAssetReadMapper;
	@Resource
	private FssLendAssetWriteMapper fssLendAssetWriteMapper;
	
	@Resource
	private FssLendAssetDetailReadMapper fssLendAssetDetailReadMapper;
	
	@Resource
	private FssLendAssetDetailWriteMapper fssLendAssetDetailWriteMapper;
	
	/**
	 * author:jhz
	 * time:2016年4月28日
	 * function：添加出借资产主表信息
	 */
	public void insertLendAsset(FssLendAssetEntity fssLendAssetEntity)throws FssException{
		fssLendAssetWriteMapper.insertSelective(fssLendAssetEntity);
	}
	
	/**
	 * author:jhz
	 * time:2016年4月28日
	 * function：批量添加出借资产详情表信息
	 */
	public void insertLendAssetDetail(List<FssLendAssetDetailEntity> assetDetailEntities)throws FssException{
		fssLendAssetWriteMapper.insertList(assetDetailEntities);
	}
	
	/**
	 * author:jhz
	 * time:2016年4月28日
	 * function：通过parent_id得到详情表信息集合
	 */
	public List<FssLendAssetDetailEntity> getDetailByParentId(Long id) throws FssException{
		return fssLendAssetDetailReadMapper.getDetailByParentId(id);
	}
	
	/**
	 * author:jhz
	 * time:2016年4月28日
	 * function：通过查询条件得到得到借款资产表信息
	 */
	public List<FssLendAssetEntity> getLendAsset(Map<String,String> map) throws FssException{
		if(map!=null){
		map.put("startTime", map.get("startTime") != null ? map.get("startTime").replace("-", "") : null);
		map.put("endTime", map.get("endTime") != null ? map.get("endTime").replace("-", "") : null);
		}
		return fssLendAssetReadMapper.getLendAssets(map);
	}
	
	
}

