package com.gqhmt.fss.architect.customer.mapper.read;/**
 * Created by yuyonf on 15/11/30.
 */

import java.util.List;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.customer.entity.ChangeCardEntity;

/**
 * Filename:    com.gq.funds.dao.ChangeCardDao
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/11/30 16:45
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/11/30  于泳      1.0     1.0 Version
 */
public interface ChangeCardReadMapper extends ReadMapper<ChangeCardEntity> {


    public List<ChangeCardEntity> queryNotPass();

}
