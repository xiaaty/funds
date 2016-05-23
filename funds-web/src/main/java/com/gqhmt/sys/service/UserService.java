package com.gqhmt.sys.service;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.entity.UserEntity;
import com.gqhmt.sys.entity.User;
import com.gqhmt.sys.mapper.read.UserReadMapper;
import com.gqhmt.util.StringUtils;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Filename:    com.gqhmt.sys.service.UserService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/12/27 15:17
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/12/27  于泳      1.0     1.0 Version
 */
@Service
public class UserService {

    @Resource
    private UserReadMapper readMapper;

    public List<User> selectUsers(User user){
        return readMapper.selectAll();
    }
    
    
    public User getUserById(String loginName,String loginPwd){
    	Map map=new HashMap();
    	if(StringUtils.isNotEmptyString(loginName) && StringUtils.isNotEmptyString(loginPwd)){
    		map.put("loginName", loginName);
    		map.put("loginPwd", loginPwd);
    	}
    	User user=readMapper.getUser(map);
    	return user;
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
	/**
	 * 
	 * author:jhz
	 * time:2016年3月28日
	 * function：根据id查询user对象信息
	 */
    public User getUserById(Long id){
		return readMapper.selectByPrimaryKey(id);
    	
    }
    
    
}
