package com.gqhmt.funds.architect.account.mapper.read;

import com.github.pagehelper.Page;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.funds.architect.account.bean.FundAccountCustomerBean;
import com.gqhmt.funds.architect.account.bean.FundsAccountBean;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gq.p2p.account.dao
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/15 16:03
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/15  于泳      1.0     1.0 Version
 */
public interface FundsAccountReadMapper extends ReadMapper<FundAccountEntity> {
	
	/**
	 * 获取所有账户列表
	 * @param fundAccountEntity
	 * @return
	 * @throws FssException
	 */
	public List<FundAccountEntity> queryFundsAccountList(FundAccountEntity fundAccountEntity) throws FssException;

    /**
     * 获取所有账户列表
     * @param fundsAcctBean
     * @return
     * @throws FssException
     */
    public Page queryBusinessFundsAccountList(FundsAccountBean fundsAcctBean);
    
    /**
     * 获取所有借款客户账户列表
     * @param fundsAcctBean
     * @return
     * @throws FssException
     */
    public Page queryLoanFundsAccountList(FundsAccountBean fundsAcctBean);

    public FundAccountEntity queryFundAccountByCutId(@Param("cusID") Long cusID, @Param("type")int type);
	/**
	 * jhz
	 * 获取客户的所有账户信息
	 * @param custId
	 * @return
	 */
    public List<FundAccountEntity> queryFundAccountsByCutId(@Param("custId") Long custId);

    public FundAccountEntity getAccountBanlance(@Param("cust_no") Long cust_no, @Param("busi_type")int busi_type);
    
    public FundAccountEntity queryFundAccountByUserName(@Param("userName") String userName,@Param("type") int type);

    public List<FundAccountEntity> queryFundAccount(Integer cusID);
    
    
    /**
	 * 获取旧账户列表
	 * @param fundsAcctBean
	 * @return
	 * @throws FssException
	 */
	public List<FundAccountEntity> findOldFundsAccountList(FundAccountEntity fundsAcctBean) throws FssException;
	 /**
	    * 
	    * author:jhz
	    * time:2016年2月16日
	    * function：funds账号管理
	    */
	public List<FundAccountCustomerBean> findAcountList(Map accMap);
	  /**
		  * 
		  * author:jhz
		  * time:2016年2月18日
		  * function：找到指定的客户
		  */
	public FundAccountCustomerBean fundAccountCustomerById(Integer withHoldId);
	

	public FundAccountEntity selectFundAccountEntity(@Param("accNo") String accNo);

	public List<FundAccountEntity> getFundsAccountByBusiType(@Param("busi_type") String busi_type);

	public List<FundAccountEntity> getRedAccountList(List list);

}
