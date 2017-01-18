package com.gqhmt.funds.architect.account.mapper.read;

import com.github.pagehelper.Page;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.asset.entity.FssStatisticsEntity;
import com.gqhmt.fss.architect.trade.bean.FundFlowBean;
import com.gqhmt.funds.architect.account.bean.FundAccountSequenceBean;
import com.gqhmt.funds.architect.account.bean.FundsAccountBean;
import com.gqhmt.funds.architect.account.entity.FundSequenceEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * Filename:    com.gq.p2p.account.Bean
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/16 14:06
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/16  于泳      1.0     1.0 Version
 */
public interface FundSequenceReadMapper extends ReadMapper<FundSequenceEntity> {

    public BigDecimal getSumAmount(long id);

    /**
     * 根据主账户及客户实体查询并返回对应的交易流水信息
     * @param accountBean
     * @return
     */
    public Page queryFundSequenceList(FundsAccountBean accountBean);

    public int getSizeByOrderNo(String orderNo);
	/**
	 * jhz
	 * 通过s_order_no获取订单入账数量
	 * @param sOrderNo
	 * @return
	 */
    public int getSizeBySOrderNo(@Param("sOrderNo") String sOrderNo);

    public BigDecimal getSumByOrderNo(String orderNo);

    public int querySequence(long accountId);
    /**
     * 
     * author:jhz
     * time:2016年2月17日
     * function：查询流水列表
     */
	public List<FundAccountSequenceBean> selectAccountSequenceList(Map fasMap);	
	
	/**
	 * 账户资金流水
	 * @param map
	 * @return
	 */
	public List<FundFlowBean> getFundSequence(Map map);
	
	
	public List<FundSequenceEntity> getSumByDay();
	
	public  List<FundFlowBean> selectAllFundFlow(FundFlowBean fundflow);
	
	public FssStatisticsEntity queryMonthTotal(@Param("custId") String custId);

	/**
	 * jhz
	 * 通过订单号查询流水列表
	 * @param orderNo
	 * @return
     */
	public List<FundSequenceEntity> queryByOrderNo(@Param("orderNo") String orderNo);
	/**
	 * jhz
	 * 通过s_order_no查询流水列表
	 * @param sOrderNo
	 * @return
     */
	public List<FundSequenceEntity> queryBySOrderNo(@Param("sOrderNo") String sOrderNo);

	/**
	 * jhz
	 * 查询提现次数接口
	 * @return
     */
	public Integer queryWithDrawCount(@Param("accountId")Long accountId);
	/**
	 * jhz
	 * 查询充值次数接口
	 * @return
	 */
	Integer queryWithHoldCount(@Param("accountId")Long accountId);

}
