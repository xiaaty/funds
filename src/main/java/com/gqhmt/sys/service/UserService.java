package com.gqhmt.sys.service;

import com.gqhmt.sys.entity.User;
import com.gqhmt.sys.mapper.read.UserReadMapper;
import com.gqhmt.util.StringUtils;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     
    
}
