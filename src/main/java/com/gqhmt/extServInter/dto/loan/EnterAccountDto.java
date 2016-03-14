package com.gqhmt.extServInter.dto.loan;
import java.util.List;

import com.gqhmt.extServInter.dto.SuperDto;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月7日
 * Description:	入账接口（批量）
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月7日  jhz      1.0     1.0 Version
 */
public class EnterAccountDto extends SuperDto {

	private List<EnterAccount> enterAccounts;

	public List<EnterAccount> getEnterAccounts() {
		return enterAccounts;
	}

	public void setEnterAccounts(List<EnterAccount> enterAccounts) {
		this.enterAccounts = enterAccounts;
	}
	
    
	}

	
    
