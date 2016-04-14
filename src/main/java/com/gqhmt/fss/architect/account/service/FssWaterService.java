package com.gqhmt.fss.architect.account.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.account.entity.FssWaterEntity;
import com.gqhmt.fss.architect.account.mapper.read.FssWaterReadMapper;
import com.gqhmt.funds.architect.account.bean.FundsAccountBean;

/**
 * Filename:    com.gqhmt.fss.architect.account.service.FssWaterService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:23
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
@Service
public class FssWaterService {
	@Resource
	private FssWaterReadMapper fssWaterReadMapper;
	  /**
     * 
     * author:jhz
     * time:2016年1月26日
     * function：查询流水详情列表
	 * @param endDate 
	 * @param startDate 
     */
	public List<FssWaterEntity> queryWaterDetail(Long id, String startDate, String endDate)throws FssException {
		Map searchWater=new HashMap();
		if(startDate!=null&&!startDate.equals("")){
			 startDate=startDate+" 00:00:00";
		}
		if(endDate!=null&&!endDate.equals("")){
			endDate=endDate+" 23:59:59";
		}
		searchWater.put("id",id);
		searchWater.put("startDate",startDate);
		searchWater.put("endDate",endDate);
		// TODO Auto-generated method stub
		return fssWaterReadMapper.queryWaterDetail(searchWater);
	}
	
}
