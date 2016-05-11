package com.gqhmt.sftp.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.sftp.entity.FssCreditInfoEntity;
import com.gqhmt.sftp.entity.FssFinanceSumEntity;
import com.gqhmt.sftp.entity.FssProjectCallbackEntity;
import com.gqhmt.sftp.entity.FssProjectInfoEntity;
import com.gqhmt.sftp.entity.FssSftpRecordEntity;
import com.gqhmt.sftp.entity.FssSumAuditEntity;
import com.gqhmt.sftp.mapper.read.FssCreditInfoReadMapper;
import com.gqhmt.sftp.mapper.read.FssFinanceSumAuditReadMapper;
import com.gqhmt.sftp.mapper.read.FssFinanceSumReadMapper;
import com.gqhmt.sftp.mapper.read.FssProjectCallbackReadMapper;
import com.gqhmt.sftp.mapper.read.FssProjectInfoReadMapper;
import com.gqhmt.sftp.mapper.read.FssSftpRecordReadMapper;
import com.gqhmt.sftp.mapper.write.FssItemsCallbackWriteMapper;
import com.gqhmt.sftp.mapper.write.FssSftpRecordWriteMapper;

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
	private FssProjectInfoReadMapper fssProjectInfoReadMapper;
	@Resource
	private FssItemsCallbackWriteMapper fssItemsCallbackWriteMapper;
	@Resource
	private FssFinanceSumAuditReadMapper fssFinanceSumAuditReadMapper;
	@Resource
	private FssFinanceSumReadMapper fssFinanceSumReadMapper;
	@Resource
	private FssProjectCallbackReadMapper fssProjectCallbackReadMapper;
	@Resource
	private FssSftpRecordReadMapper fssSftpRecordReadMapper;
	
	@Resource
	private FssCreditInfoReadMapper fssCreditInfoReadMapper;
	@Resource
	private FssSftpRecordWriteMapper fssSftpRecordWriteMapper;
	
	/**
	 * 
	 * author:jhz
	 * time:2016年5月6日
	 * function：查找所有项目信息
	 */
	public List<FssProjectInfoEntity> queryItemsInfos()throws FssException {
		return fssProjectInfoReadMapper.selectAll();
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月11日
	 * function：标的财务汇总审核回盘文件 
	 */
	public List<FssSumAuditEntity> querySumAudit()throws FssException {
		return fssFinanceSumAuditReadMapper.selectAll();
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月11日
	 * function：标的财务汇总文件表
	 */
	public List<FssFinanceSumEntity> queryFinaSum()throws FssException {
		return fssFinanceSumReadMapper.selectAll();
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月11日
	 * function：标的放款明细文件 
	 */
	public List<FssCreditInfoEntity> queryCreditInfo()throws FssException {
		return fssCreditInfoReadMapper.selectAll();
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月6日
	 * function：把回盘信息添加进项目信息回盘表
	 */
	public void insetItemCallback(List<FssProjectCallbackEntity> itemsCallbacks){
		fssItemsCallbackWriteMapper.insertList(itemsCallbacks);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月11日
	 * function：添加记录表信息
	 */
	public void insertSftpRecord(String title,int count,String type,String status){
		FssSftpRecordEntity record=new FssSftpRecordEntity();
		record.setCount(count);
		record.setCreateTime(new Date());
		record.setModifyTime(new Date());
		record.setStatus(status);
		record.setTitle(title);
		record.setType(type);
		fssSftpRecordWriteMapper.insertSelective(record);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月11日
	 * function：修改记录表信息
	 */
	public void updateSftpRecord(String title,int count,String type,String status){
		FssSftpRecordEntity record=new FssSftpRecordEntity();
		record.setCount(count);
		record.setCreateTime(new Date());
		record.setModifyTime(new Date());
		record.setStatus(status);
		record.setTitle(title);
		record.setType(type);
		fssSftpRecordWriteMapper.insertSelective(record);
	}
	
}
