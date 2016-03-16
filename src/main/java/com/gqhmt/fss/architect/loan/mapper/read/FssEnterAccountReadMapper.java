package com.gqhmt.fss.architect.loan.mapper.read;

import java.util.List;
import java.util.Map;

import com.gqhmt.core.FssException;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.extServInter.dto.loan.EnterAccount;
import com.gqhmt.fss.architect.loan.bean.EnterAccountBean;
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
 * Description:	入账mapper
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
		List<EnterAccount> getEnterAccount(Map<String, String> map)throws FssException;
		/**
		 * 
		 * author:jhz
		 * time:2016年3月15日
		 * function：得到入账表
		 */
		List<EnterAccountBean> getEnterAccountEntities(Map map);
		/**
		 * 
		 * author:jhz
		 * time:2016年3月15日
		 * function：根据流水号得到相应的每一批的交易成功数量
		 */
		int getIsTrue(String seqNo);
	
}
