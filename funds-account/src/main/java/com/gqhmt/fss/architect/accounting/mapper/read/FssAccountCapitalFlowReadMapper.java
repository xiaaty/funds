package com.gqhmt.fss.architect.accounting.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.accounting.entity.FssAccountCapitalFlow;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.fss.architect.trade.service.FssTradeApplyService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/29
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/29  jhz     1.0     1.0 Version
 */
public interface FssAccountCapitalFlowReadMapper extends ReadMapper<FssAccountCapitalFlow> {

    public List<FssAccountCapitalFlow> getCapitalFlows(Map map);
}
