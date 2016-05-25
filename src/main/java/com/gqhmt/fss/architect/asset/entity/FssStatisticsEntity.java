package com.gqhmt.fss.architect.asset.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Filename:    com.gqhmt.fss.architect.asset.entity.FssAssetEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/5/16 21:51
 * Description:
 * <p>统计当月充值、提现总额
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  柯禹来     1.0     1.0 Version
 */
public class FssStatisticsEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal rechargeTotal; //充值总额        
    private BigDecimal withdrawTotal; //提现总额  
	public BigDecimal getRechargeTotal() {
		return rechargeTotal;
	}
	public void setRechargeTotal(BigDecimal rechargeTotal) {
		this.rechargeTotal = rechargeTotal;
	}
	public BigDecimal getWithdrawTotal() {
		return withdrawTotal;
	}
	public void setWithdrawTotal(BigDecimal withdrawTotal) {
		this.withdrawTotal = withdrawTotal;
	}
	
}
