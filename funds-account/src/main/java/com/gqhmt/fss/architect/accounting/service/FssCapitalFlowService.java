package com.gqhmt.fss.architect.accounting.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.accounting.entity.FssAccountCapitalFlow;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingFreeze;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingFreezeDetail;
import com.gqhmt.fss.architect.accounting.mapper.read.FssAccountCapitalFlowReadMapper;
import com.gqhmt.fss.architect.accounting.mapper.read.FssFreezeDetailReadMapper;
import com.gqhmt.fss.architect.accounting.mapper.read.FssFreezeReadMapper;
import com.gqhmt.fss.architect.accounting.mapper.write.FssAccountCapitalFlowWriteMapper;
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
 * Create at:   2016年6月29日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年6月29日  jhz      1.0     1.0 Version
 */
@Service
public class FssCapitalFlowService {
	
	@Resource
	private FssAccountCapitalFlowReadMapper fssAccountCapitalFlowReadMapper;
	@Resource
	private FssAccountCapitalFlowWriteMapper fssAccountCapitalFlowWriteMapper;

	/**
	 *jhz
	 * 添加
	 * @param fssAccountCapitalFlow
	 * @throws FssException
     */
	public void insertCapitalFlow(FssAccountCapitalFlow fssAccountCapitalFlow)throws FssException{
		fssAccountCapitalFlowWriteMapper.insertSelective(fssAccountCapitalFlow);
	}
	
	/**
	 *jhz
	 *通过查询条件得到得到列表
	 * @param map
	 * @return
	 * @throws FssException
     */
	public List<FssAccountCapitalFlow> getCapitalFlow(Map<String,String> map) throws FssException{
		if(map!=null){
			map.put("startTime", map.get("startTime") != null ? map.get("startTime").replace("-", "") : null);
			map.put("endTime", map.get("endTime") != null ? map.get("endTime").replace("-", "") : null);
			}
		return fssAccountCapitalFlowReadMapper.getCapitalFlows(map);
	}
	
	
}

