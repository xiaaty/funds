package com.gqhmt.extServInter.callback.loan;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.loan.ChangeCardResponse;
import com.gqhmt.pay.service.account.IFundsAccount;
/**
 * Filename:    com.gqhmt.extServInter.callback.loan.ChangeBankCallback
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/3/6 22:32
 * Description:银行卡变更完成通知回调
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/3/6  于泳      1.0     1.0 Version
 */
@Service
public class ChangeBankCallback{
//	银行卡变更完成，通知变更发起方（借款系统）返回 seqNo 流水号 和 mchn  商户号 
	@Resource
	private IFundsAccount fundsAccountImpl;
    
    public ChangeCardResponse bankCardChangeCallBack(String seqNo,String mchn) throws FssException{
    	ChangeCardResponse response = new ChangeCardResponse();
    	try {
    		response = fundsAccountImpl.bankCardChangeCallBack(seqNo,mchn);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
			response.setResp_code(e.getMessage());
		}
    	return response;
    }
    
    
}
