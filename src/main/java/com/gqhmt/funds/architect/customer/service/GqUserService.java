package com.gqhmt.funds.architect.customer.service;

import com.github.pagehelper.Page;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.customer.entity.FssChangeCardEntity;
import com.gqhmt.fss.architect.customer.mapper.read.FssChangeCardReadMapper;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.funds.architect.customer.bean.BankCardBean;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.BankEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.entity.UserEntity;
import com.gqhmt.funds.architect.customer.mapper.read.BankCardInfoReadMapper;
import com.gqhmt.funds.architect.customer.mapper.read.BankReadMapper;
import com.gqhmt.funds.architect.customer.mapper.read.CustomerInfoReadMapper;
import com.gqhmt.funds.architect.customer.mapper.read.GqUserReadMapper;
import com.gqhmt.funds.architect.customer.mapper.write.BankCardinfoWriteMapper;
import com.gqhmt.funds.architect.customer.mapper.write.BankWriteMapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gq.p2p.customer.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/16 16:36
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/16  于泳      1.0     1.0 Version
 */
@Service
public class GqUserService {


    @Resource
    private GqUserReadMapper gqUserReadMapper;

	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return
	 */
	public UserEntity queryUserByUserid(Integer id){
		UserEntity queryEntity = new UserEntity();
	    queryEntity.setCustId(id);
		return gqUserReadMapper.selectOne(queryEntity);
	}
	
}
