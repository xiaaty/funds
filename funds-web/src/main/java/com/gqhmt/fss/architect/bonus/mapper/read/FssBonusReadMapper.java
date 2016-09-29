package com.gqhmt.fss.architect.bonus.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.bonus.entity.FssBonusEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
public interface FssBonusReadMapper extends ReadMapper<FssBonusEntity>{
   public List<FssBonusEntity> queryBondTransferList(Map map);

   /**
    * jhz
    * 查询所有处于待执行状态的红包列表
    * @return
     */
   public List<FssBonusEntity> queryBonusList();
   /**
    * jhz
    * @return
     */
   public List<FssBonusEntity> selectByParentId(@Param("parentId") Long parentId);
   /**
    * jhz
    * @return
    */
   public int selectCountBySeqNo(@Param("seqNo") String seqNo);

}
