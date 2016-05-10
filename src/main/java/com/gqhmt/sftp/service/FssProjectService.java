package com.gqhmt.sftp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.sftp.entity.FssProjectCallbackEntity;
import com.gqhmt.sftp.entity.FssProjectInfoEntity;
import com.gqhmt.sftp.mapper.read.FssProjectInfoReadMapper;
import com.gqhmt.sftp.mapper.write.FssItemsCallbackWriteMapper;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年5月6日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月6日  jhz      1.0     1.0 Version
 */
@Service
public class FssProjectService {
	@Resource
	private FssProjectInfoReadMapper fssItemInfoReadMapper;
	@Resource
	private FssItemsCallbackWriteMapper fssFssItemsCallbackWriteMapper;
	
	/**
	 * 
	 * author:jhz
	 * time:2016年5月6日
	 * function：查找所有项目信息
	 */
	public List<FssProjectInfoEntity> queryItemsInfos()throws FssException {
		return fssItemInfoReadMapper.selectAll();
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月6日
	 * function：把回盘信息添加进项目信息回盘表
	 */
	public void insetItemCallback(List<FssProjectCallbackEntity> itemsCallbacks){
		fssFssItemsCallbackWriteMapper.insertList(itemsCallbacks);
	}
	
}
