package com.gqhmt.sys.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.sys.entity.DictOrderEntity;
import java.util.List;

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
public interface DictOrderReadMapper extends ReadMapper<DictOrderEntity> {

    public List<DictOrderEntity> selectDictOrder(DictOrderEntity dictorder);
    
    public DictOrderEntity getDictOrderById(Long id);
}
