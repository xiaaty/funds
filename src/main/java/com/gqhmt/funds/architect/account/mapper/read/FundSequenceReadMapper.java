package com.gqhmt.funds.architect.account.mapper.read;

import com.github.pagehelper.Page;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.funds.architect.account.bean.FundAccountSequenceBean;
import com.gqhmt.funds.architect.account.bean.FundsAccountBean;
import com.gqhmt.funds.architect.account.entity.FundSequenceEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
	public FundAccountSequenceBean queryFundTradeFlow(Map map);
}
