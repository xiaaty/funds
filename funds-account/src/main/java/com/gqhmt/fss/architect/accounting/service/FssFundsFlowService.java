package com.gqhmt.fss.architect.accounting.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.accounting.entity.FssFundsFlowEntity;
import com.gqhmt.fss.architect.accounting.mapper.read.FssFundsFlowReadMapper;
import com.gqhmt.fss.architect.accounting.mapper.write.FssFundsFlowWriteMapper;

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
public class FssFundsFlowService {
	
	@Resource
	private FssFundsFlowReadMapper fssFundsFlowReadMapper;
	@Resource
	private FssFundsFlowWriteMapper fssFundsFlowWriteMapper;
	
	/**
	 * 
	 * author:jhz
	 * time:2016年5月3日
	 * function：添加资金流水
	 */
	public void insertFundsFlow(FssFundsFlowEntity fssFundsFlowEntity)throws FssException{
		fssFundsFlowWriteMapper.insertSelective(fssFundsFlowEntity);
	}
	
	/**
	 * author:jhz
	 * time:2016年5月3日
	 * function：批量添加出资金流水
	 */
	public void insertFundsFlow(List<FssFundsFlowEntity> fundsFlows)throws FssException{
		fssFundsFlowWriteMapper.insertList(fundsFlows);
	}
	
	/**
	 * 
	 * author:jhz
	 * time:2016年5月3日
	 * function：通过查询条件得到得到资金流水信息
	 */
	public List<FssFundsFlowEntity> getFundsFlow(Map<String,String> map) throws FssException{
		if(map!=null){
			map.put("startTime", map.get("startTime") != null ? map.get("startTime").replace("-", "") : null);
			map.put("endTime", map.get("endTime") != null ? map.get("endTime").replace("-", "") : null);
			}
		return fssFundsFlowReadMapper.getFundsFlow(map);
	}
	
	
}

