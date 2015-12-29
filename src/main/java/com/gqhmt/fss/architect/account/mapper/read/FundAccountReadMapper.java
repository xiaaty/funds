package com.gqhmt.fss.architect.account.mapper.read;

import java.util.List;

import com.github.pagehelper.Page;
import com.gqhmt.core.FssException;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.account.entity.FundAccountEntity;
import com.gqhmt.fss.architect.account.bean.FundsAccountBean;

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
	 * @param pageReq
	 * @return
	 * @throws AppException
	 */
	public Page queryFundsAccountList(FundsAccountBean fundsAcctBean) throws FssException;

    public List<FundAccountEntity> queryFundAccount(Integer cusID);

}
