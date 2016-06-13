package com.gqhmt.funds.architect.customer.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.entity.UserEntity;
import com.gqhmt.funds.architect.customer.mapper.read.GqUserReadMapper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

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
	
	
	
	/**
	 * 创建用户
	 * @param loanAccountDto
	 * @return
	 */
	public UserEntity createUser(CreateLoanAccountDto loanAccountDto,CustomerInfoEntity customer) throws FssException{
		UserEntity userEntity=new UserEntity();
		userEntity.setUserUuid(this.getUUID());
		userEntity.setUserName(loanAccountDto.getName());
		userEntity.setMobilePhone(loanAccountDto.getMobile());
		userEntity.setCreateTime((new Timestamp(new Date().getTime())));
		userEntity.setModifyTime((new Timestamp(new Date().getTime())));
		userEntity.setIntegral(0);
		userEntity.setCreditLevel(0);
		userEntity.setCustId(customer.getId().intValue());
		userEntity.setUserFrom(0);
		userEntity.setIsFirstDebt(0);
		userEntity.setUserType(1);
		userEntity.setIsVerify(0);
		return userEntity;
	}
	
	/**
	 * 生成UUid
	 * @return
	 */
	 public String getUUID() {  
		  String str=UUID.randomUUID().toString();
	      String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);  
	      return temp;  
	    } 
}
