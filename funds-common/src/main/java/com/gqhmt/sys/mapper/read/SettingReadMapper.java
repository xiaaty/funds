package com.gqhmt.sys.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.sys.entity.Settings;

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
 * 2016/02/14  jhz      1.0     1.0 Version
 */
public interface SettingReadMapper extends ReadMapper<Settings>{
    public List<Settings> findSettings(Settings setting);

    
}
