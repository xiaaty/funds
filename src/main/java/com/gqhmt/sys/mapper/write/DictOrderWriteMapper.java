package com.gqhmt.sys.mapper.write;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.sys.entity.DictOrderEntity;

/**
 * Filename:    com.gqhmt.sys.mapper.read.DictOrderReadMapper
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/2/15 9:44
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/12/18  柯禹来      1.0     1.0 Version
 */
public interface DictOrderWriteMapper extends ReadAndWriteMapper<DictOrderEntity> {

	  public void insertDictOrder(DictOrderEntity dictorder);
}
