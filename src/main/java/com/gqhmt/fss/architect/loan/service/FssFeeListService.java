package com.gqhmt.fss.architect.loan.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.fss.architect.loan.entity.FssFeeList;
import com.gqhmt.fss.architect.loan.mapper.read.FssLoanReadMapper;
import com.gqhmt.fss.architect.loan.mapper.write.FssFeeListWriteMapper;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月7日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月7日  jhz      1.0     1.0 Version
 */
@Service
public class FssFeeListService {
	
	 @Resource
	    private FssFeeListWriteMapper fssFeeListWriteMapper;
	 @Resource
	 private FssLoanReadMapper fssLoanReadMapper;
	 /**
	  * 
	  * author:jhz
	  * time:2016年3月7日
	  * function：添加
	  */
	 public void insert(FssFeeList feeList){
		 fssFeeListWriteMapper.insert(feeList);
	 }
	 /**
		 * 
		 * author:jhz
		 * time:2016年3月7日
		 * function：通过id得到收费列表
		 */
		public List<FssFeeList> getFeeList(Long id) {
		return fssLoanReadMapper.getFeeList(id);
	}
	 
	    
	    
}
