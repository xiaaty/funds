package com.gqhmt.fss.architect.trade.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.trade.entity.FssBondTransferEntity;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.fss.architect.trade.mapper.read.FssTradeRecordWriteMapper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/29
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/29  柯禹来      1.0     1.0 Version
 */
public interface FssBondTransferReadMapper extends ReadMapper<FssBondTransferEntity>{
   public List<FssBondTransferEntity> queryBondTransferList(Map map);

}
