package com.gqhmt.fss.architect.bonus.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.bonus.entity.FssBonusParentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Filename:    com.gqhmt.fss.architect.trade.mapper.read.FssTradeRecordWriteMapper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
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
public interface FssBonusParentReadMapper extends ReadMapper<FssBonusParentEntity>{

   /**
    * jhz
    * @param mchn
    * @param seqNo
    * @return
     */
   public FssBonusParentEntity queryBonusParent(@Param("mchn") String mchn,@Param("seqNo") String seqNo);

}
