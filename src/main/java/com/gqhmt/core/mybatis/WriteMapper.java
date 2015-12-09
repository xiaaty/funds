package com.gqhmt.core.mybatis;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.base.BaseDeleteMapper;
import tk.mybatis.mapper.common.base.BaseInsertMapper;
import tk.mybatis.mapper.common.base.BaseUpdateMapper;

/**
 * Filename:    com.gqhmt.core.mybatis.WriteMapper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/10/28 11:32
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/6/2  于泳      1.0     1.0 Version
 */
public interface WriteMapper<T> extends BaseInsertMapper<T>, BaseUpdateMapper<T>, BaseDeleteMapper<T>,Marker {
}
