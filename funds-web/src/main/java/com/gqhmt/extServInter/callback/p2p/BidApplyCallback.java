package com.gqhmt.extServInter.callback.p2p;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.callback.loan.GetCallBack;
import com.gqhmt.extServInter.dto.p2p.BidApplyResponse;
import com.gqhmt.fss.architect.loan.service.FssLoanService;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年4月25日
 * Description:冠e通回盘
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年4月25日  jhz      1.0     1.0 Version
 */
@Service
public class BidApplyCallback implements GetCallBack{
	@Resource
	private FssLoanService fssLoanService;
	/**
	 * 
	 * author:jhz
	 * time:2016年4月25日
	 * function：得到流标回调对象
	 */
	public BidApplyResponse getCallBack(String mchn,String seqNo){
		
		BidApplyResponse bidApplyResponse =null;
			 try {
				 bidApplyResponse=fssLoanService.getBidApplyResponse(mchn, seqNo);
			} catch (FssException e) {
				LogUtil.info(this.getClass(), e.getMessage());
				bidApplyResponse.setResp_code(e.getMessage());
			}
		
		 return bidApplyResponse;
	}
	
}
