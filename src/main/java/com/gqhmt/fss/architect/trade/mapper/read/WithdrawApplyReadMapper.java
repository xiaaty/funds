package com.gqhmt.fss.architect.trade.mapper.read;

import java.util.List;

import com.github.pagehelper.Page;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.trade.bean.WithdrawApplyBean;
import com.gqhmt.fss.architect.trade.entity.WithdrawApplyEntity;

/**
 * Filename:    com.gq.p2p.account.dao
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/16 22:29
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/16  于泳      1.0     1.0 Version
 */
public interface WithdrawApplyReadMapper extends ReadMapper<WithdrawApplyEntity>{
	
	/**
	 * 根据条件对象查询并返回对应的提现信息列表
	 * @param pageReq
	 * @return
	 * @throws AppException
	 */
	public Page queryWithdrawByConditionList(WithdrawApplyBean withDrawBean);
		
	/**
     * 查询提现申请
     * @param debtId
     * @return
     */
    public WithdrawApplyEntity queryByDebtId(Integer debtId);

    /**
     * 查询未审批的数据
     * @return
     */
	public List<WithdrawApplyEntity> selectWithdrawApplyByStatus();
	//" select * from t_gq_withdraw_apply where  apply_status = 1 "
}
