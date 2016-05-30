package com.gqhmt.fss.architect.card.mapper.read;


import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.card.entiry.FssCardBinEntity;
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
 * Create at:   2016年5月27日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月19日  jhz      1.0     1.0 Version
 */
public interface FssCardBinReadMapper extends ReadMapper<FssCardBinEntity>{

    public List<FssCardBinEntity> findCardBins(Map map);
	
}
