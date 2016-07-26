package com.gqhmt.funds.architect.customer.mapper.read;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.funds.architect.customer.bean.BankCardBean;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;

/**
 * Filename:    com.gq.p2p.customer.dao
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/16 16:35
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/16  于泳      1.0     1.0 Version
 */
public interface BankCardInfoReadMapper extends ReadMapper<BankCardInfoEntity> {
	
	/**
	 * 根据条件查询返回银行卡列表
	 * @param bankDto
	 * @param pageReq
	 * @return
	 * @throws AppException
	 */
	public Page queryCardListByCustomer(BankCardBean bankDto) throws FssException;
	/**
	 * 查询该客户是否已配置银行卡信息
	 * @param bankCardId
	 * @param userId
	 * @return
	 */
	public Long queryBankCardByUserId(String bankCardId,Integer userId);
	
	/**
     * 查询该标的银行卡信息是否已存在
     * @param bankCardId
     * @param userId
     * @return
     */
    public BankCardInfoEntity queryBankCardByIdAndCardNo(String bankNo,Integer bankCardId);
    
    /**
     * 根据卡号查询银行卡信息
     * @param bankNo
     * @return
     */
    public BankCardInfoEntity queryBankCardNo(String bankNo);
    /**
     * 银行列表
     * @param bankDto
     * @param pageReq
     * @return
     * @throws AppException
     */
	public List<BankCardBean> queryBankList();
	
	
	/**
	 * 查询银行卡信息
	 * @param
	 * @return
	 */
	public List<BankCardInfoEntity> selectBankCardList(Map map);
	
	
	
	 public BankCardInfoEntity	queryBankCardByBankNo(@Param("bankNo") String bankNo);
		
		
	 public List<BankCardInfoEntity> findBankCardByCustNo(@Param("custNo") String custNo);

	public List<BankCardInfoEntity> queryBankCard(@Param("custNo") Integer custNo);
}
