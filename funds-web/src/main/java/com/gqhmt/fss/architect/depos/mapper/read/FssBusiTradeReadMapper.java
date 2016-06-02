package com.gqhmt.fss.architect.depos.mapper.read;

import java.util.List;
import java.util.Map;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.depos.entity.FssBusinessTradeEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年5月12日
 * Description:P2P商户交易
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月6日  柯禹来      1.0     1.0 Version
 */
public interface FssBusiTradeReadMapper extends ReadMapper<FssBusinessTradeEntity> {
	
	public List<FssBusinessTradeEntity> queryBusiTradeList(Map map);
	/**
	 *
	 * author:jhz
	 * time:2016年5月24日
	 * function：根据状态查询文件列表
	 */
	public List<FssBusinessTradeEntity> queryByStatus(@Param("status")String status);
}
