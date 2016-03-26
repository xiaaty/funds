package com.gqhmt.extServInter.service.asset.impl;

import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.fund.BankDto;
import com.gqhmt.extServInter.dto.fund.BankResponse;
import com.gqhmt.extServInter.service.asset.IBankList;
import com.gqhmt.funds.architect.customer.entity.BankEntity;
import com.gqhmt.pay.service.account.IFundBank;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 银行列表接口
 * @author 柯禹来
 */
@Service
public class BankListImpl implements IBankList{
	@Resource
	private IFundBank fundBankImpl;
	
    @Override
    public Response execute(SuperDto dto) throws APIExcuteErrorException {
    	BankResponse response = new BankResponse();
    	try {
    		List<BankEntity> banklist=fundBankImpl.getBankInfo((BankDto)dto);
    		response.setBanklist(banklist);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
