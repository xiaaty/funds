package com.gqhmt.fss.architect.trade.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;

import com.gqhmt.fss.architect.trade.entity.TradeProcessEntity;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.funds.architect.record.mapper.read.TradeProcessReadMapper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/12/27 11:49
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/12/27  于泳      1.0     1.0 Version
 */
public interface TradeProcessReadMapper extends ReadMapper<TradeProcessEntity> {
    TradeProcessEntity getTradeProcessByOrderNo(@Param("orderNo") String orderNo);

    List<TradeProcessEntity> listTradeProcess(Map<String, String> map);

    /**
     * 根据actionType 和 ParentId 查询子交易
     * @param actionType
     * @param parentId
     * @return
     */
    List<TradeProcessEntity> findByParentIdAndActionType(@Param("actionType") String actionType, @Param("parentId") String parentId);

    /**
     * 查询提现手续费
      * @param parentId
     * @return
     */
    public BigDecimal getChargeAmount(@Param("parentId") Long parentId);

    /**
     * jhz
     * 查询所有未进行提现交易的数据
     * @return
     */
    public List<TradeProcessEntity> getWithDrawProcess();
 /**
     * jhz
     * 查询所有未进行提现交易的数据
     * @return
     */
    public List<TradeProcessEntity> getFailWithDrawProcess();

}
