package com.gqhmt.fss.architect.trade.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.trade.entity.FssTradeInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/9/6.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/9/6.  xdw         1.0     1.0 Version
 */
public interface FssTradeInfoReadMapper extends ReadMapper<FssTradeInfoEntity>{

    public List<FssTradeInfoEntity> listTradeInfo(Map<String, String> map);
}
