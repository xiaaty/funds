package com.gqhmt.sys.mapper.write;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.sys.entity.MenuEntity;

/**
 * Filename:    com.gqhmt.sys.mapper.write.MenuWriteMapper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/12/18 23:35
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/12/18  于泳      1.0     1.0 Version
 */
public interface MenuWriteMapper extends ReadAndWriteMapper<MenuEntity> {
	/**
	 * 
	 * author:jhz
	 * time:2016年1月29日
	 * function：添加菜单
	 */
	public void addMenu(MenuEntity menu);
}
