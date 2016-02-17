package com.gqhmt.funds.architect.account.mapper.read;

import com.github.pagehelper.Page;
import com.gqhmt.core.FssException;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.account.bean.BussAndAccountBean;
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
public interface FundAccountReadMapper extends ReadMapper<FundAccountEntity> {
	
	/**
	 * 获取所有账户列表
	 * @param fundsAcctBean
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

    public FundAccountEntity queryFundAccountByCutId(@Param("cusID") Integer cusID, @Param("type")int type);
    
    public FundAccountEntity queryFundAccountByUserName(String userName,int type);

    public List<FundAccountEntity> queryFundAccount(Integer cusID);
    
    
    /**
	 * 获取旧账户列表
	 * @param fundsAcctBean
	 * @return
	 * @throws FssException
	 */
	public List<FundAccountEntity> findOldFundsAccountList(FundAccountEntity fundsAcctBean) throws FssException;
	
	
	
}
