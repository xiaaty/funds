package com.gqhmt.fss.architect.accounting.mapper.read;/**
 * Created by yuyonf on 15/11/30.
 */

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.accounting.entity.FssCheckAccountingEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年4月1日
 * Description:
 * <p>出借系统映射地区表mapper
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年4月1日  jhz      1.0     1.0 Version
 */
public interface FssCheckAccountingReadMapper extends ReadMapper<FssCheckAccountingEntity> {

    /**
     * jhz
     * 查询列表
     * @param map
     * @return
     */
    public List<FssCheckAccountingEntity> queryList(Map map);

}
