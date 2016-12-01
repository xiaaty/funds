package com.gqhmt.fss.architect.card.mapper.read;


import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.card.entiry.FssPosBackEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年11月27日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年11月19日  jhz      1.0     1.0 Version
 */
public interface FssPosBackReadMapper extends ReadMapper<FssPosBackEntity>{
    /**
     * jhz
     * 查询最近3天富友返回来的pos签约回调数据
     * @param startDate
     * @return
     */
   List<FssPosBackEntity> selectPosBacks(@Param("startDate") String startDate);

}
