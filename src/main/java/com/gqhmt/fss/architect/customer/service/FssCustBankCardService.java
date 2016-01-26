package com.gqhmt.fss.architect.customer.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.fss.architect.customer.entity.CustomerAndUser;
import com.gqhmt.fss.architect.customer.entity.FssCustBankCardEntity;
import com.gqhmt.fss.architect.customer.mapper.read.FssCustomerReadMapper;

/**
 * Filename:    com.gqhmt.fss.architect.customer.service.FssCustBankCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/4 17:27
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/4  于泳      1.0     1.0 Version
 */
@Service
public class FssCustBankCardService {
	@Resource
	private FssCustomerReadMapper fssCustomerReadMapper;
	public List<CustomerAndUser> findbankCardAll() {
		
		return fssCustomerReadMapper.findbankCardAll();
	}
}
