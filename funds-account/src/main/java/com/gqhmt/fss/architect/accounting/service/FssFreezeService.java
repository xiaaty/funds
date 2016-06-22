package com.gqhmt.fss.architect.accounting.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingFreeze;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingFreezeDetail;
import com.gqhmt.fss.architect.accounting.mapper.read.FssFreezeDetailReadMapper;
import com.gqhmt.fss.architect.accounting.mapper.read.FssFreezeReadMapper;
import com.gqhmt.fss.architect.accounting.mapper.write.FssFreezeDetailWriteMapper;
import com.gqhmt.fss.architect.accounting.mapper.write.FssFreezeWriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
public class FssFreezeService {
	
	@Resource
	private FssFreezeReadMapper fssFreezeReadMapper;
	@Resource
	private FssFreezeWriteMapper fssFreezeWriteMapper;
	
	@Resource
	private FssFreezeDetailReadMapper fssFreezeDetailReadMapper;
	
	@Resource
	private FssFreezeDetailWriteMapper freezeDetailWriteMapper;
	
	/**
	 * 
	 * author:jhz
	 * time:2016年5月3日
	 * function：添加资金冻结信息
	 */
	public void insertFreeze(FssAccountingFreeze fssAccountingFreeze)throws FssException{
		fssFreezeWriteMapper.insertSelective(fssAccountingFreeze);
	}
	
	/**
	 * author:jhz
	 * time:2016年4月28日
	 * function：批量添加出借资产详情表信息
	 */
	/**
	 * 
	 * author:jhz
	 * time:2016年5月3日
	 * function：批量添加资金冻结详情表信息
	 */
	public void insertFreezeDetail(List<FssAccountingFreezeDetail> freezeDetails)throws FssException{
		freezeDetailWriteMapper.insertList(freezeDetails);
	}
	
	/**
	 * 
	 * author:jhz
	 * time:2016年5月3日
	 * function：通过parent_id得到详情表信息集合
	 */
	public List<FssAccountingFreezeDetail> getDetailByParentId(Long parentId) throws FssException{
		return fssFreezeDetailReadMapper.getFreezeDetail(parentId);
	}
	
	/**
	 * 
	 * author:jhz
	 * time:2016年5月3日
	 * function：通过查询条件得到得到资金冻结表信息
	 */
	public List<FssAccountingFreeze> getLendAsset(Map<String,String> map) throws FssException{
		if(map!=null){
			map.put("startTime", map.get("startTime") != null ? map.get("startTime").replace("-", "") : null);
			map.put("endTime", map.get("endTime") != null ? map.get("endTime").replace("-", "") : null);
			}
		return fssFreezeReadMapper.getFreezes(map);
	}
	
	
}

