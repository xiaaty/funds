package com.gqhmt.fss.architect.accounting.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.accounting.entity.FssTransferCheckEntity;
import com.gqhmt.fss.architect.accounting.mapper.read.FssTransferCheckReadMapper;
import com.gqhmt.fss.architect.accounting.mapper.write.FssTransferCheckWritreMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
 * Create at:   2016年6月24日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年6月24日  jhz      1.0     1.0 Version
 */
@Service
public class FssTransferCheckService {
	
	@Resource
	private FssTransferCheckReadMapper fssTransferCheckReadMapper;
	@Resource
	private FssTransferCheckWritreMapper fssTransferCheckWritreMapper;


	/**
	 * jhz
	 * 添加
	 * @param fssTransferCheckEntity
	 * @throws FssException
     */
	public void insertTransferCheck(FssTransferCheckEntity fssTransferCheckEntity)throws FssException{
		fssTransferCheckEntity.setCreateTime(new Date());
		fssTransferCheckEntity.setModifyTime(new Date());
		fssTransferCheckWritreMapper.insertSelective(fssTransferCheckEntity);
	}

	/**
	 * jhz
	 * 修改
	 * @param fssTransferCheckEntity
	 * @throws FssException
     */
	public void updateTransferCheck(FssTransferCheckEntity fssTransferCheckEntity)throws FssException{
		fssTransferCheckEntity.setModifyTime(new Date());
		fssTransferCheckWritreMapper.updateByPrimaryKey(fssTransferCheckEntity);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年6月24日
	 * function：通过查询条件得到得到划拨对账表信息
	 */
	public List<FssTransferCheckEntity> queryTransferCheck(Map<String,String> map) throws FssException{
		if(map!=null){
			map.put("startTime", map.get("startTime") != null ? map.get("startTime").replace("-", "") : null);
			map.put("endTime", map.get("endTime") != null ? map.get("endTime").replace("-", "") : null);
			}
		return fssTransferCheckReadMapper.queryTransferCheck(map);
	}
}

