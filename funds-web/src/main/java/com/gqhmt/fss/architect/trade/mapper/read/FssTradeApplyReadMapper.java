package com.gqhmt.fss.architect.trade.mapper.read;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.extServInter.dto.p2p.WithHoldApplyResponse;
import com.gqhmt.fss.architect.trade.bean.FssTradeApplyBean;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;

/**
 * Filename:    com.gqhmt.fss.architect.trade.mapper.read.FssTradeApplyReadMapper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:27
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
public interface FssTradeApplyReadMapper extends ReadMapper<FssTradeApplyEntity> {
	/**
	 * 
	 * author:jhz
	 * time:2016年3月11日
	 * function：借款人提现
	 */
	public List<FssTradeApplyEntity> getBorrowWithDraw(Map map);
	/**
	 *
	 * author:jhz
	 * time:2016年3月11日
	 * function：借款人提现
	 */
	public FssTradeApplyEntity queryForFromId(@Param("fromId") Long fromId,@Param("busiType") String busiType);

	/**
	 * 抵押权人代扣
	 * @param map
	 * @return
	 */
	public List<FssTradeApplyBean> queryFssTradeApplyList(Map map);
	/**
	 * 抵押权人代扣
	 * @param map
	 * @return
	 */
	public FssTradeApplyBean queryFssTradeApply(@Param("applyNo")String applyNo);
	
	/**
	 * 根据申请单号获取FssTradeApplyEntity
	 */
	public FssTradeApplyEntity selectFssTradeApplyEntityByApplyNo(@Param("applyNo")String applyNo);
	
	/**
	 * 
	 * author:jhz
	 * time:2016年3月29日
	 * function：根据申请编号查询申请对象信息
	 */
	public FssTradeApplyEntity selectByApplyNo(@Param("applyNo")String applyNo);
	/**
	 * 
	 * author:jhz
	 * time:2016年3月29日
	 * function：根据申请编号查询申请对象信息
	 */
	public List<FssTradeApplyEntity> selectByTradeState(@Param("tradeState")String tradeState);
	/**
	 * 
	 * author:jhz
	 * time:2016年4月12日
	 * function：根据商户号流水号查询
	 */
	public FssTradeApplyEntity selectBySeqNoAndMchn(@Param("seqNo")String seqNo,@Param("mchn")String mchn);
	
	/**
	 * 
	 * author:柯禹来
	 * time:2016年5月19日
	 * function：查询冠E通代扣申请回盘数据
	 */
	public WithHoldApplyResponse selectTradeApplyData(@Param("mchn")String mchn,@Param("seqNo")String seqNo);
	
	
}
