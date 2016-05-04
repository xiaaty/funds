package com.gqhmt.fss.architect.loan.mapper.read;

import java.util.List;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.loan.entity.FssEnterAccountEntity;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月15日
 * Description:	入账子表mapper
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月15日  jhz      1.0     1.0 Version
 */
public interface FssEnterAccountReadMapper extends ReadMapper<FssEnterAccountEntity> {

		/**
		 * author:jhz
		 * time:2016年3月9日
		 * function：入账回盘
		 */
		List<FssEnterAccountEntity> getEnterAccounts(Long parentId);
		/**
		 * 
		 * author:jhz
		 * time:2016年4月7日
		 * function：通过父id得到该批次成功条数
		 */
		int getSuccessCount(Long parentId);
	
}
