package com.gqhmt.sys.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.sys.entity.User;

import java.util.List;

/**
 * Filename:    com.gqhmt.sys.mapper.read.UserReadMapper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/12/27 15:23
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/12/27  于泳      1.0     1.0 Version
 */
public interface UserReadMapper extends ReadMapper<User>{
    public List<User> findUser(User user);
}
